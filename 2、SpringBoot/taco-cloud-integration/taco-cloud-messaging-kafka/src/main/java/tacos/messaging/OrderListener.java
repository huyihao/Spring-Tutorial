package tacos.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Component
@Slf4j
public class OrderListener {

	@KafkaListener(topics = {"tacocloud.orders.topic"})
	public void receiveOrder(Order order) {
		log.info("----------------------- LISTENER RECEIVE ORDER -------------------------");
		log.info("Reveive a order: " + order);
	}
	
}
