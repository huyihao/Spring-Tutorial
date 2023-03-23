package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
//@PropertySources(value = {@PropertySource("classpath:application.properties")})
public class TacoCloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudConfigApplication.class, args);
	}

	@Bean
	@Profile("prod")
	public ProdProfile profile() {
		return new ProdProfile("env");
	}
}
