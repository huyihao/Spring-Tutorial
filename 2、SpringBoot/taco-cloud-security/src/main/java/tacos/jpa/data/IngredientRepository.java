package tacos.jpa.data;

import org.springframework.data.repository.CrudRepository;

import tacos.jpa.domain.Ingredient;

/**
 * CrudRepository<T, ID>:
 * (1) T: repository要持久化的实体类型
 * (2) ID: 实体ID属性的类型
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
