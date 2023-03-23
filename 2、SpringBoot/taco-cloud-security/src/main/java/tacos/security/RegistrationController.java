package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.jpa.data.UserRepository;
import tacos.jpa.domain.User;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {		
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	// 用户注册页
	@GetMapping
	public String registerForm() {
		return "registration";
	}	
	
	// 接收表单上送的数据，注册用户
	@PostMapping
	public String processRegistration(RegistrationForm form) {
		User user = form.toUser(passwordEncoder);
		userRepo.save(user);
		return "redirect:/login";
	}	
}
