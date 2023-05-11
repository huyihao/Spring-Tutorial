package tacos.messaging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.alibaba.fastjson.JSON;

//@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConfig {

    @Autowired
    private KafkaProperties properties;	
    
    @Value("${spring.kafka.bootstrap-servers}")
    private List<String> myServers;    
	
    //获得创建消费者工厂
    public ConsumerFactory<Object, Object> consumerFactory() {
        KafkaProperties myKafkaProperties = JSON.parseObject(JSON.toJSONString(this.properties), KafkaProperties.class);
        //对模板 properties 进行定制化
        //....
        //例如：定制servers
        myKafkaProperties.setBootstrapServers(myServers);
        return new DefaultKafkaConsumerFactory<>(myKafkaProperties.buildConsumerProperties());
    }    
}
