package tacos.web.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tacos.Taco;
import tacos.data.TacoRepository;

@RepositoryRestController
public class ApiRecentTacosController {

	private TacoRepository tacoRepo;
	
	@Autowired
	public ApiRecentTacosController(TacoRepository tacoRepo) {		
		this.tacoRepo = tacoRepo;
	}	
	
	@Value("${spring.data.rest.base-path}")
	private String apiBasePath;
	
	@GetMapping(path = "/tacos/{tacoId}", produces = "application/hal+json")
	public ResponseEntity<tacos.web.api.Taco> getTacoById(@PathVariable Long tacoId) {
		Optional<Taco> taco = tacoRepo.findById(tacoId);
		tacos.web.api.Taco tacoModel = new tacos.web.api.Taco(taco.get());
	    Link link = WebMvcLinkBuilder.linkTo(ApiRecentTacosController.class)	    							 
	            					 .slash(apiBasePath + "/tacos/" + tacoId)
	            					 .withRel("self");
	    tacoModel.add(link);
	    return new ResponseEntity<>(tacoModel, HttpStatus.OK);
	}
	
	@GetMapping(path = "/tacos/recent", produces = "application/hal+json")
	public ResponseEntity<CollectionModel<tacos.web.api.Taco>> getRecentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		List<tacos.web.api.Taco> tacosList = new ArrayList<>();
		for (Taco taco : tacos) {
		    Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ApiRecentTacosController.class).getTacoById(taco.getId())).withRel("self");		    
		    tacos.web.api.Taco apiTaco = new tacos.web.api.Taco(taco);
		    apiTaco.add(link);
		    tacosList.add(apiTaco);
		}
		return new ResponseEntity<>(CollectionModel.of(tacosList), HttpStatus.OK);
	}	
}
