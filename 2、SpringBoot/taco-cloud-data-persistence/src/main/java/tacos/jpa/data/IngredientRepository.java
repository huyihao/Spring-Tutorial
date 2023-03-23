package tacos.jpa.data;

import org.springframework.data.repository.CrudRepository;

import tacos.jpa.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
