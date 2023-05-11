package tacos.messaging;

import javax.jms.JMSException;

import org.springframework.jms.support.converter.MessageConversionException;

import tacos.Order;

public interface OrderReceiver {
	public Order receiveOrder() throws MessageConversionException, JMSException ;
}
