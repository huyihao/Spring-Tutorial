package tacos.messaging;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.apache.kafka.common.serialization.Deserializer;

import com.alibaba.fastjson.JSON;

import tacos.Order;

public class OrderDeserializer implements Deserializer<Order> {

	@Override
	public Order deserialize(String topic, byte[] data) {
        if (Objects.isNull(data)) {
            return null;
        }

        String orderStr = new String(data, StandardCharsets.UTF_8);
        return JSON.parseObject(orderStr, Order.class);
	}

}
