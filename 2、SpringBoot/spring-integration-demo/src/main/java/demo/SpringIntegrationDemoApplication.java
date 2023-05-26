package demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("integration.xml")
public class SpringIntegrationDemoApplication {

	public static void main(String[] args) throws IOException {
		//SpringApplication.run(SpringIntegrationDemoApplication.class, args);
	    ConfigurableApplicationContext ctx = new SpringApplication(SpringIntegrationDemoApplication.class).run(args);
	    System.out.println("Hit Enter to terminate");
	    System.in.read();
	    ctx.close();		
	}

}
