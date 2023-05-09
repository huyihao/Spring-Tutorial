package tacos.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Component
@Slf4j
public class OrderListener {

	@RabbitListener(queues = {"tacocloud"})
	public void receiveOrder(Order order) {
		log.info("----------------------- LISTENER RECEIVE ORDER -------------------------");
		log.info("Reveive a order: " + order);
	}
	
}
