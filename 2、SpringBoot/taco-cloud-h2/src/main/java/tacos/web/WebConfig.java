package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 声明视图控制器
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 为URL "/" 指定视图home
		// 即 http://localhost:8080/ 访问的是home.html
		registry.addViewController("/").setViewName("home");
		// 不设置视图名，则默认跟路径名相同，即http://localhost:8080/login 访问的是login.html
		registry.addViewController("/login");		
	}
}
