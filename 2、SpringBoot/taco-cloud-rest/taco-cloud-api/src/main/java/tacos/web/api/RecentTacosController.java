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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tacos.Ingredient;
import tacos.Taco;
import tacos.data.TacoRepository;

/**
 * 
 * @RepositoryRestController和
 * @RequestMapping不能同时使用
 *
 */
//@RepositoryRestController
@RestController
@RequestMapping("/taco")
public class RecentTacosController {

	private TacoRepository tacoRepo;
	
	@Autowired
	public RecentTacosController(TacoRepository tacoRepo) {		
		this.tacoRepo = tacoRepo;
	}	
	
	/**
	 * 1、首先创建一个TacoModel，要继承RepresentationModel
	 * 2、往model对象中添加link对象，这是返回报文中带链接的关键
	 * 	  linkTo : 表示包含的映射类
	 *	  slash：追加在URL路径后的值
	 *    withRel：元数据对象的 key
	 * 3、返回model对象
	 */
	@GetMapping("/{tacoId}")
	public TacoModel getTacoById(@PathVariable Long tacoId) {
		Optional<Taco> taco = tacoRepo.findById(tacoId);
		TacoModel tacoModel = new TacoModel(taco.get());
	    Link link = WebMvcLinkBuilder.linkTo(RecentTacosController.class)
	            					 .slash(tacoModel.getTaco().getId())
	            					 .withRel("self");
	    tacoModel.add(link);
	    return tacoModel;
	}
	
	@GetMapping("/{tacoId}/ingredient")
	public tacos.web.api.Taco getTacoIngredientById(@PathVariable Long tacoId) {
		Optional<Taco> taco = tacoRepo.findById(tacoId);
		tacos.web.api.Taco tacoModel = new tacos.web.api.Taco(taco.get());		
	    Link link = WebMvcLinkBuilder.linkTo(RecentTacosController.class)
	            					 .slash(tacoModel.getId())
	            					 .withRel("self");
	    for (Ingredient ingredient : taco.get().getIngredients()) {
	    	Link ingredientLink = WebMvcLinkBuilder.linkTo(IngredientApiController.class)
	    			                               .slash(ingredient.getId())
	    			                               .withRel("allIngredients");
	    	tacoModel.add(ingredientLink);
	    }
	    
	    tacoModel.add(link);
	    return tacoModel;
	}
	
	@GetMapping("/recent")
	public List<Taco> getRecentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		return tacos;
	} 	
	
	@GetMapping("/recent1")
	public CollectionModel<TacoModel> getRecentTacos1() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		List<TacoModel> tacoModels = new ArrayList<>();
		for (Taco taco : tacos) {
		    Link link = WebMvcLinkBuilder.linkTo(RecentTacosController.class)
					 					 .slash(taco.getId())
					 					 .withRel("self");
		    TacoModel tacoModel = new TacoModel(taco);
		    tacoModel.add(link);
			tacoModels.add(tacoModel);
		}	
		return CollectionModel.of(tacoModels);
	} 
	
	@GetMapping("/id/{tacoId}")
	public tacos.web.api.Taco getTacoById2(@PathVariable Long tacoId) {
		Optional<Taco> taco = tacoRepo.findById(tacoId);
		tacos.web.api.Taco tacoModel = new tacos.web.api.Taco(taco.get());
	    Link link = WebMvcLinkBuilder.linkTo(RecentTacosController.class)
	            					 .slash(tacoModel.getId())
	            					 .withRel("self");
	    tacoModel.add(link);
	    return tacoModel;
	}	
	
	@GetMapping("/recent2")
	public CollectionModel<tacos.web.api.Taco> getRecentTacos2() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		List<tacos.web.api.Taco> tacosList = new ArrayList<>();
		for (Taco taco : tacos) {
		    Link link = WebMvcLinkBuilder.linkTo(RecentTacosController.class)
										 .slash(taco.getId())
										 .withRel("self");
		    tacos.web.api.Taco apiTaco = new tacos.web.api.Taco(taco);
		    apiTaco.add(link);
		    tacosList.add(apiTaco);
		}
		return CollectionModel.of(tacosList);
	}
	
	@Value("${spring.data.rest.base-path}")
	private String apiBasePath;
	
	//@GetMapping(path = "/tacos/{tacoId}", produces = "application/hal+json")
	public ResponseEntity<tacos.web.api.Taco> getTacoById3(@PathVariable Long tacoId) {
		Optional<Taco> taco = tacoRepo.findById(tacoId);
		tacos.web.api.Taco tacoModel = new tacos.web.api.Taco(taco.get());
	    Link link = WebMvcLinkBuilder.linkTo(RecentTacosController.class)	    							 
	            					 .slash(apiBasePath + "/tacos/" + tacoId)
	            					 .withRel("self");
	    tacoModel.add(link);
	    return new ResponseEntity<>(tacoModel, HttpStatus.OK);
	}
	
	//@GetMapping(path = "/tacos/recent", produces = "application/hal+json")
	public ResponseEntity<CollectionModel<tacos.web.api.Taco>> getRecentTacos3() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		List<tacos.web.api.Taco> tacosList = new ArrayList<>();
		for (Taco taco : tacos) {
		    Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RecentTacosController.class).getTacoById3(taco.getId())).withRel("self");		    
		    tacos.web.api.Taco apiTaco = new tacos.web.api.Taco(taco);
		    apiTaco.add(link);
		    tacosList.add(apiTaco);
		}
		return new ResponseEntity<>(CollectionModel.of(tacosList), HttpStatus.OK);
	}	
	
	
}
