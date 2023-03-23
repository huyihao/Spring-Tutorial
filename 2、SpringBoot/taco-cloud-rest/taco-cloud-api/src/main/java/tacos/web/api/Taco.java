package tacos.web.api;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class Taco extends RepresentationModel<Taco> {
	
	  private Long id;	 
	  private String name;	 
	  private Date createdAt;
	  
	  public Taco(tacos.Taco taco) {
		  this.id = taco.getId();
		  this.name = taco.getName();
		  this.createdAt = taco.getCreatedAt();
	  }
}
