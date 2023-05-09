package tacos.restclient;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Order;
import tacos.Taco;

@Service
@Slf4j
public class TacoCloudClient {

	private RestTemplate rest;
	private Traverson traverson;

	public TacoCloudClient(RestTemplate rest, Traverson traverson) {		
		this.rest = rest;
		this.traverson = traverson;
	}
	
	
	/**
	 * Get Example
	 */
	
	// 将参数指定为可变参数
	public Ingredient getIngredientById(String ingredientId) {
		/**
		 * "http://localhost:8080/ingredients/{id}": 要被调用的接口
		 * Ingredient.class: 调用接口返回JSON要被反序列化为的对象类型
		 * ingredientId: 传入接口的参数
		 */
		try {
		    return rest.getForObject("http://localhost:8080/api/ingredients/{id}",
   				 Ingredient.class, 
   				 ingredientId);		
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 404) {
				log.info("there not exists ingredient(" + ingredientId + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return null;
	}
	
	// 使用Map传参
	public Ingredient getIngredientById2(String ingredientId) {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", ingredientId);
		return rest.getForObject("http://localhost:8080/api/ingredients/{id}",
   				 				 Ingredient.class, 
   				 				 urlVariables);
	}	
	
	// 使用URL参数、Map传参
	public Ingredient getIngredientById3(String ingredientId) {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", ingredientId);
		URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/ingredients/{id}")
				                      .build(urlVariables);
		return rest.getForObject(url, Ingredient.class);
	}
	
	// 使用getForEntity返回的是一个ResponseEntity对象，可以获取响应细节比如响应头信息
	public Ingredient getIngredientById4(String ingredientId) {
		ResponseEntity<Ingredient> responseEntity = rest.getForEntity("http://localhost:8080/api/ingredients/{id}",
				 													  Ingredient.class, 
				 													  ingredientId);
				
		log.info("Fetched time: " + responseEntity.getHeaders().getDate());
		log.info("Response header: " + responseEntity.getHeaders());
		log.info("Response status: " + responseEntity.getStatusCode());
		
		return responseEntity.getBody();
	}	
	
	public Ingredient getIngredientById5(String ingredientId) {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", ingredientId);
		URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/ingredients/{id}")
				                      .build(urlVariables);
		ResponseEntity<Ingredient> responseEntity = rest.getForEntity(url, Ingredient.class);
		
		log.info("Fetched time: " + responseEntity.getHeaders().getDate());
		log.info("Response header: " + responseEntity.getHeaders());
		log.info("Response status: " + responseEntity.getStatusCode());
		
		return responseEntity.getBody();
	}		
	
	public List<Ingredient> getAllIngredients() {
		return rest.exchange("http://localhost:8080/ingredients", 
				             HttpMethod.GET, 
				             null,
							 new ParameterizedTypeReference<List<Ingredient>>() {}).getBody();
	}
	
	public Order getOrderById(int orderId) {
	    return rest.getForObject("http://localhost:8080/api/orders/{id}",
                				 Order.class, 
                				 orderId);		
	}	
	
	public void updateOrder(Order order) {
		rest.put("http://localhost:8080/api/orders/{id}", 
				  order, 
			      order.getId());			
	}
	
	/**
	 * Put Example
	 */
	
	public void updateIngredient(Ingredient ingredient) {		
		rest.put("http://localhost:8080/api/ingredients/{id}", 
				 ingredient, 
				 ingredient.getId());		
	}
	
	public void updateIngredient2(Ingredient ingredient) {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", ingredient.getId());
		rest.put("http://localhost:8080/api/ingredients/{id}", 
				 ingredient, 
				 urlVariables);
	}	
	
	public void updateIngredient3(Ingredient ingredient) {   
        HttpEntity<Ingredient> entity = new HttpEntity<Ingredient>(ingredient);
		rest.exchange("http://localhost:8080/api/ingredients/{id}", 
					  HttpMethod.PUT, 
					  entity, 
					  Ingredient.class,
					  ingredient.getId());
	}		
	
	
	// Delete Example
	public void deleteIngredient(Ingredient ingredient) {
		rest.delete("http://localhost:8080/api/ingredients/{id}", 
					ingredient.getId());
	}
	
	
	// Post Example
	public Ingredient createIngredient(Ingredient ingredient) {
		return rest.postForObject("http://localhost:8080/api/ingredients", 
								  ingredient, 
								  Ingredient.class);
	}
	// 返回的URI是从响应的Location头信息派生出来的
	public URI createIngredient2(Ingredient ingredient) {
		return rest.postForLocation("http://localhost:8080/api/ingredients", 
								  ingredient, 
								  Ingredient.class);
	}	
	
	public Ingredient createIngredient3(Ingredient ingredient) {
		ResponseEntity<Ingredient> responseEntity = rest.postForEntity("http://localhost:8080/api/ingredients",
																	   ingredient, 
																	   Ingredient.class);		
		log.info("New resource created at " + responseEntity.getHeaders().getLocation());		
		return responseEntity.getBody();
	}	
	
	
	public void retrieveHeaders() {
		HttpHeaders httpHeaders = rest.headForHeaders("http://localhost:8080/api/ingredients");
		log.info("Headers : " + httpHeaders);
	}
	
	//
	// Traverson with RestTemplate examples
	//
	public Iterable<Ingredient> getAllIngredientsWithTraverson() {		
		ParameterizedTypeReference<CollectionModel<Ingredient>> ingredientType = new ParameterizedTypeReference<CollectionModel<Ingredient>>() {
		};

		CollectionModel<Ingredient> ingredientRes = traverson.follow("ingredients").toObject(ingredientType);
		//Resources<Ingredient> ingredientRes = traverson.follow("ingredients").toObject(ingredientType);

		Collection<Ingredient> ingredients = ingredientRes.getContent();

		return ingredients;
	}
	
	public Ingredient addIngredient(Ingredient ingredient) {
		String ingredientsUrl = traverson.follow("ingredients").asLink().getHref();
		return rest.postForObject(ingredientsUrl, ingredient, Ingredient.class);
	}
	
	public Iterable<Taco> getRecentTacosWithTraverson() {
		ParameterizedTypeReference<CollectionModel<Taco>> tacoType = new ParameterizedTypeReference<CollectionModel<Taco>>() {
		};

		CollectionModel<Taco> tacoRes = traverson.follow("tacos").follow("recent").toObject(tacoType);

		return tacoRes.getContent();
	}
	
}
