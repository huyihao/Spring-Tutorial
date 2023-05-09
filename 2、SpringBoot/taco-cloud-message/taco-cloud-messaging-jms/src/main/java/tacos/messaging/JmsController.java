package tacos.messaging;

import java.util.Date;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

//@RestController
@RequestMapping("/jms")
@Slf4j
public class JmsController {
	
	@Autowired
	JmsOrderMessagingService jmsService;
	
	@Autowired
	JmsOrderReceiver jmsReceiver;

	@GetMapping("/convertAndSend/order")
	public String convertAndSendOrder() {
		log.info("----------------------- SEND ORDER -------------------------");
		Order order = buildOrder();
		log.info("Ready to send order: " + order);
		jmsService.sendOrder(order);
		log.info("Send order success!");
		return "Convert and send order";
	}

	@GetMapping("/receiveAndConvert/order")
	public Order receiveAndConvertOrder() {
		log.info("----------------------- RECEIVE ORDER -------------------------");
		Order order = null;
		try {
			order = jmsReceiver.receiveOrder();
		} catch (MessageConversionException e) {
			log.error("Convert message exception", e);
			e.printStackTrace();
		} catch (JMSException e) {
			log.error("Receive message exception", e);
			e.printStackTrace();
		}
		log.info("Send order success!");
		return order;
	}
	
	private Order buildOrder() {
		Order order = new Order();
		order.setId(1L);
		order.setUserId(1L);
		order.setDeliveryName("huyihao");
		order.setDeliveryState("广东省");			
		order.setDeliveryCity("广州市");
		order.setDeliveryStreet("荔湾区石围塘街道");
		order.setDeliveryZip("515100");
		order.setCcNumber("6226890243871067");
		order.setCcExpiration("12/23");
		order.setCcCvv("282");
		order.setPlacedAt(new Date());
		
		return order;
	}
}
