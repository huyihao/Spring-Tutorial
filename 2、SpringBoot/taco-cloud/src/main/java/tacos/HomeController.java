package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 被视图控制器替代
//@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
}
