package tacos.messaging;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

//@Service
@Slf4j
public class JmsOrderReceiver implements OrderReceiver {

	private JmsTemplate jms;
	private MessageConverter converter;
	
	public JmsOrderReceiver(JmsTemplate jms, MessageConverter converter) {		
		this.jms = jms;
		this.converter = converter;
	}

	@Override
	public Order receiveOrder() throws MessageConversionException, JMSException {
		Message message = jms.receive("tacocloud.order.queue");
		String source = message.getStringProperty("X_ORDER_SOURCE");
		log.info("This order is from " + source);
		return (Order) converter.fromMessage(message);
	}

}
