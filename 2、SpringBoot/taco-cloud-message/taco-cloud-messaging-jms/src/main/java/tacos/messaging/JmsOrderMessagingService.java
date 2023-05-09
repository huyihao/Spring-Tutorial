package tacos.messaging;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import tacos.Order;

//@Service
public class JmsOrderMessagingService implements OrderMessagingService {

	private JmsTemplate jms;
	private Destination orderQueue;
	
	@Autowired
	public JmsOrderMessagingService(JmsTemplate jms, Destination orderQueue) {		
		this.jms = jms;
		this.orderQueue = orderQueue;
	}

	@Override
	public void sendOrder(Order order) {
		/**
		 * 使用 send()
		 */
		//useSend(order);		
				
		/**
		 * 使用 convertAndSend
		 */
		//useConvertAndSend(order);
		
		jms.convertAndSend("tacocloud.order.queue", order, this::addOrderSource);	
	}
	
	private void useSend(Order order) {
		// 写法1：使用MessageCreator的匿名内部类写法
		/*jms.send(new MessageCreator() {			
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(order);
			}
		});*/
		
		// 写法2：函数式接口匿名内部类的简化写法，lambda表达式，默认为 spring.jms.template.default-destination 参数设置的队列
		jms.send(session -> session.createObjectMessage(order));
		
		// 写法3：指定队列（使用Destination、直接使用字符串）
		jms.send(orderQueue,
				 session -> session.createObjectMessage(order));
		jms.send("tacocloud.order.queue",
				 session -> session.createObjectMessage(order));	
		
		jms.send("tacocloud.order.queue", new MessageCreator() {			
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createObjectMessage(order);
				message.setStringProperty("X_ORDER_SOURCE", "WEB");
				return message;
			}
		});	
		jms.send("tacocloud.order.queue", session -> {
				Message message = session.createObjectMessage(order);
				message.setStringProperty("X_ORDER_SOURCE", "WEB");
				return message;			
		});		
					
	}
	
	private void useConvertAndSend(Order order) {
		jms.convertAndSend(order);
		jms.convertAndSend(orderQueue, order);
		jms.convertAndSend("tacocloud.order.queue", order);			
		// 在发送数据之前修改底层的Message对象
		jms.convertAndSend("tacocloud.order.queue", order, new MessagePostProcessor() {			
			@Override
			public Message postProcessMessage(Message message) throws JMSException {
				message.setStringProperty("X_ORDER_SOURCE", "WEB");
				return message;
			}
		});
		
		// lambda
		jms.convertAndSend("tacocloud.order.queue", order, message -> {
				message.setStringProperty("X_ORDER_SOURCE", "WEB");
				return message;			
		});		
		
		// 方法引用
		jms.convertAndSend("tacocloud.order.queue", order, this::addOrderSource);			
	}
	
	private Message addOrderSource(Message message) throws JMSException {
		message.setStringProperty("X_ORDER_SOURCE", "WEB");
		return message;				
	}

}
