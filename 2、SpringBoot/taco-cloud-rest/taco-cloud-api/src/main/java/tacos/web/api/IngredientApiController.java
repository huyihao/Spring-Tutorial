package tacos.web.api;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

@Slf4j
@RestController
@RequestMapping(path = "/ingredients", 
                produces="application/json")
@CrossOrigin(origins = "*")
public class IngredientApiController {
	
	private IngredientRepository repo;

	@Autowired
	public IngredientApiController(IngredientRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	public Iterable<Ingredient> allIngredients() {
		Iterable<Ingredient> ingredients = repo.findAll();		
		log.info("All ingredients: " + IteratorUtils.toList(ingredients.iterator()).toString());		
		return repo.findAll();
	}
	
}
