package tacos.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Service
@Slf4j
public class KafkaOrderMessagingService implements OrderMessagingService {

	private KafkaTemplate<String, Order> kafkaTemplate;	
	
	@Autowired
	public KafkaOrderMessagingService(KafkaTemplate<String, Order> kafkaTemplate) {		
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void sendOrder(Order order) {
		log.info("Ready to send message!");
		kafkaTemplate.send("tacocloud.orders.topic", order);
		// 不指定主题使用yml配置的默认主题
		//kafkaTemplate.sendDefault(order);
		log.info("Ready to send message!");
	}
	
	public Order receiveOrder() {
		log.info("Ready to receive message!");		
		ConsumerRecord<String, Order> record = kafkaTemplate.receive("tacocloud.orders.topic", 0, 0);
		Order order = record.value();
		log.info("Receive message: " + order);
		return order;
	}

}
