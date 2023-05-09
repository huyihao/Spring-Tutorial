package tacos.messaging;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;


// 没办法用RestExamples的方式测试，因为不会自动生成JmsTemplate的bean
//@SpringBootConfiguration
//@ComponentScan
@Slf4j
public class JmsExamples {

	public static void main(String[] args) {
		SpringApplication.run(JmsExamples.class, args);
	}

	@Bean
	public CommandLineRunner sendOrder(JmsOrderMessagingService service) {
		return args -> {
			log.info("----------------------- SEND ORDER -------------------------");
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
			
			log.info("Ready to send order: " + order);
			service.sendOrder(order);
			log.info("Send order success!");
		};
	}
	
}
