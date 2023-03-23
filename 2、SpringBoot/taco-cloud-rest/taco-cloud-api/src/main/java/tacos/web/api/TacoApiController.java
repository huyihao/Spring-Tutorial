package tacos.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.data.TacoRepository;
import tacos.Taco;

/**
 * 设计Tacos的控制器
 */
@Slf4j
@RestController							// 控制器中所有处理器方法的返回值都要直接写入响应体中，而不是将值放到模型中并传递一个视图以便进行渲染，相当于@Controller + @ResponseBody
@RequestMapping(path = "/design", 		// 处理针对"/design"的请求，所有处理器方法只会处理Accept头信息包含"application/json"的请求
				produces = "application/json")   // 限制API只会生成JSON，并允许其他控制器处理具有相同路径的请求
@CrossOrigin(origins = "*")				// 允许来自任何域的客户端消费该API
public class TacoApiController {
	
	private TacoRepository tacoRepo;
	
	@Autowired
	public TacoApiController(TacoRepository tacoRepo) {		
		this.tacoRepo = tacoRepo;
	}
	
	// 获取最近设计的所有taco
	@GetMapping("/recent")
	public Iterable<Taco> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return tacoRepo.findAll(page).getContent();
	}
	
	// 根据taco id获取对应的taco
	// 不管对应id的taco是否存在，客户端均返回200的响应码
	/*@GetMapping("/{id}")
	public Taco tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepo.findById(id);
		if (optTaco.isPresent()) {
			return optTaco.get();
		}
		return null;
	}*/
	
	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepo.findById(id);
		if (optTaco.isPresent()) {
			log.info("Query taco succ: " + optTaco.get());
			return new ResponseEntity<Taco>(optTaco.get(), HttpStatus.OK);			
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}	
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
	    return tacoRepo.save(taco);
	}
}
