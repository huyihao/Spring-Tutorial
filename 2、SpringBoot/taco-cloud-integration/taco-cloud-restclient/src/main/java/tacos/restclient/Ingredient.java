package tacos.restclient;

import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Ingredient extends RepresentationModel<Ingredient> {

	@Id
	private final String id;
	private final String name;
	private final String type;

	public Ingredient(tacos.Ingredient ingredient) {
		this.id = ingredient.getId();
		this.name = ingredient.getName();
		this.type = ingredient.getType().toString();
	}
	
}
