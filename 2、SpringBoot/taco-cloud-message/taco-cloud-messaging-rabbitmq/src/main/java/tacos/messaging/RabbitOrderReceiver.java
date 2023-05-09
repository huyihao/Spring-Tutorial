package tacos.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Component
@Slf4j
public class RabbitOrderReceiver {

	private RabbitTemplate rabbit;
	private MessageConverter converter;
	
	@Autowired
	public RabbitOrderReceiver(RabbitTemplate rabbit, MessageConverter converter) {		
		this.rabbit = rabbit;
		this.converter = converter;
	}
	
	public Order receiveOrder() {
		//Message message = rabbit.receive("order");
		
		// 带超时时间的消息接收
		/*Message message = rabbit.receive("order", 30000);
		Order order = null;
		if (message != null) {
			order = (Order) converter.fromMessage(message);
		}*/
		
		//Order order = (Order) rabbit.receiveAndConvert("order", 30000, new ParameterizedTypeReference<Order>() {});
		
		log.info("Start to receive order!");
		Order order = (Order) rabbit.receiveAndConvert("order", 30000);
		log.info("Receive order end, succ? " + (order != null));
		return order;
	}
	
}
