package tacos.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Taco;
import tacos.data.TacoRepository;
import tacos.Order;

@Slf4j
@Controller("web-designTacosController")
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacosController {
	
	private TacoRepository tacoRepo;
	
	public DesignTacosController(TacoRepository tacoRepo) {		
		this.tacoRepo = tacoRepo;
	}

	@GetMapping
	public String showDesignForm(Model model) {		
		return "design";
	}		
	
	@ModelAttribute(name = "design")
	public Taco taco() {
		return new Taco();
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	// 从网页表单提交上来的请求会在这里处理
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, 
								Errors errors,
								@ModelAttribute Order order) {
		if (errors.hasErrors()) {
			return "design";
		}
		log.info("Processing design: " + design);
		
		Taco saved = tacoRepo.save(design);
		order.addDesign(saved);
		
		return "redirect:/orders/current";
	}	

}
