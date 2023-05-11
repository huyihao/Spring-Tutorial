package tacos;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ingredient")
public class Ingredient {

	@Id
	private String id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Type type;

	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	
	private Ingredient() {}

	public Ingredient(String id, String name, Type type) {		
		this.id = id;
		this.name = name;
		this.type = type;
	}

}
