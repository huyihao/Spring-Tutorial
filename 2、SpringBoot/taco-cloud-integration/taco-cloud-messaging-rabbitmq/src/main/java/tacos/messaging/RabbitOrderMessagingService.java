package tacos.messaging;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.Order;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

	private RabbitTemplate rabbit;
	
	@Autowired
	public RabbitOrderMessagingService(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
	}
	
	@Override
	public void sendOrder(Order order) {
		// 1、显式使用消息转换器
		/*MessageConverter converter = rabbit.getMessageConverter();
		MessageProperties props = new MessageProperties();
		props.setHeader("X_ORDER_SOURCE", "WEB");
		Message message = converter.toMessage(order, props);
		rabbit.send("tacocloud.order", message);*/
		
		// 2、使用convertAndSend屏蔽消息转换细节
		//rabbit.convertAndSend("tacocloud.order", order);		
		
		// 3、使用convertAndSend时要设置消息属性只能通过MessagePostProcessor来处理消息细节
		rabbit.convertAndSend("tacocloud.order", order, new MessagePostProcessor() {		
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				MessageProperties props = message.getMessageProperties();
				props.setHeader("X_ORDER_SOURCE", "WEB");
				return message;
			}
		});
	}

}
