package tacos.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {

	private Long id;
	private Date createdAt;
	
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;	
	
	@NotNull(message = "You must choose at least 2 ingredient")
	@Size(min = 2, message = "You must choose at least 2 ingredient")
	private List<Ingredient> ingredients;	
	
}
