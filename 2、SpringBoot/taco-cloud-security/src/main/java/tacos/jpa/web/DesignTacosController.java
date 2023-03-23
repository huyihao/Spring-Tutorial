package tacos.jpa.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.jpa.data.IngredientRepository;
import tacos.jpa.data.TacoRepository;
import tacos.jpa.domain.Ingredient;
import tacos.jpa.domain.Ingredient.Type;
import tacos.jpa.domain.Order;
import tacos.jpa.domain.Taco;


@Slf4j						// 相当于 private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacosController.class); 
@Controller("jpa-designTacosController")
@RequestMapping("/jpa-design")
@SessionAttributes("order")
public class DesignTacosController {

	private IngredientRepository ingredientRepo;
	private TacoRepository tacoRepo;
	
	@Autowired
	public DesignTacosController(IngredientRepository ingredientRepo, 
								 TacoRepository tacoRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
	}
	
	// 相当于每次访问都将菜单放到model中返回
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		// 从数据库中查询到所有的配料数据
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}	
	
	@GetMapping
	public String showDesignForm(Model model) {
		//model.addAttribute("design", new Taco());
		return "jpa-design";
	}	
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}		
	
	@ModelAttribute(name = "design")
	public Taco taco() {
		return new Taco();
	}
	
	// 这里的 @ModelAttribute("design") 对应的就是 showDesignFrom() 方法里的model
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, 
								Errors errors,
								@ModelAttribute Order order) {
		if (errors.hasErrors()) {
			return "jpa-design";
		}
		log.info("Processing design: " + design);
		
		Taco saved = tacoRepo.save(design);
		order.addDesign(saved);
		
		return "redirect:/jpa-orders/current";
	}
	
	// 根据指定配料种类筛选
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream()
				          .filter(x -> x.getType().equals(type))
				          .collect(Collectors.toList());
	}
}
