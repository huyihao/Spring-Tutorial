package tacos.messaging;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.Order;

public class OrderSerializer implements  Serializer<Order> {

	@Override
	public byte[] serialize(String topic, Order data) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString(data).getBytes();
			System.out.println("Using OrderSerializer!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

}
