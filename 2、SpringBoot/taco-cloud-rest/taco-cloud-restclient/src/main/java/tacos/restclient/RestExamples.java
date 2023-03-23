package tacos.restclient;

import java.net.URI;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Order;

@SpringBootConfiguration
@ComponentScan
@Slf4j
public class RestExamples {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}	
	
	public static void main(String[] args) {
		SpringApplication.run(RestExamples.class, args);
	}

	// 根据id获取单个配料，获取所有的配料列表
	//@Bean
	public CommandLineRunner fetchIngredients(TacoCloudClient tacoCloudClient) {
		return args -> {
			log.info("----------------------- GET -------------------------");
			log.info("GETTING INGREDIENT BY IDE");
			log.info("Ingredient:  " + tacoCloudClient.getIngredientById("CHED"));
			log.info("GETTING ALL INGREDIENTS");
			List<Ingredient> ingredients = tacoCloudClient.getAllIngredients();
			log.info("All ingredients:");
			for (Ingredient ingredient : ingredients) {
				log.info("   - " + ingredient);
			}
		};
	}
	
	// 获取并更新一条配料数据
	//@Bean
	public CommandLineRunner putAnIngredient(TacoCloudClient tacoCloudClient) {
		return args -> {
			log.info("----------------------- PUT -------------------------");
			Ingredient before = tacoCloudClient.getIngredientById("LETC");
			log.info("BEFORE:  " + before);
			tacoCloudClient.updateIngredient(new Ingredient("LETC", "VEGGIES", Ingredient.Type.VEGGIES));
			Ingredient after = tacoCloudClient.getIngredientById("LETC");
			log.info("AFTER:  " + after);			
		};
	}
	
	// 添加一条配料数据
	//@Bean
	public CommandLineRunner addAnIngredient(TacoCloudClient tacoCloudClient) {
		return args -> {
			log.info("----------------------- POST -------------------------");
			Ingredient chix = new Ingredient("CHIX", "Shredded Chicken", Ingredient.Type.PROTEIN);
			Ingredient chixAfter = tacoCloudClient.createIngredient(chix);
			log.info("AFTER=1:  " + chixAfter);	
		};
	}
	
	// 删除一条配料数据
	//@Bean
	public CommandLineRunner deleteAnIngredient(TacoCloudClient tacoCloudClient) {
		return args -> {
			log.info("----------------------- DELETE -------------------------");
			// start by adding a few ingredients so that we can delete them later...
			Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
			tacoCloudClient.createIngredient(beefFajita);
			Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
			tacoCloudClient.createIngredient(shrimp);

			Ingredient before = tacoCloudClient.getIngredientById("CHIX");
			log.info("BEFORE:  " + before);
			before.setId("CHIX");
			tacoCloudClient.deleteIngredient(before);			
			Ingredient after = tacoCloudClient.getIngredientById("CHIX");
			log.info("AFTER:  " + after);
			
			before = tacoCloudClient.getIngredientById("BFFJ");
			log.info("BEFORE:  " + before);
			before.setId("BFFJ");
			tacoCloudClient.deleteIngredient(before);
			after = tacoCloudClient.getIngredientById("BFFJ");
			log.info("AFTER:  " + after);
			
			before = tacoCloudClient.getIngredientById("SHMP");
			log.info("BEFORE:  " + before);
			before.setId("SHMP");
			tacoCloudClient.deleteIngredient(before);
			after = tacoCloudClient.getIngredientById("SHMP");
			log.info("AFTER:  " + after);
		};
	}
	
	//@Bean
	public CommandLineRunner putOrder(TacoCloudClient tacoCloudClient) {
		return args -> {
			log.info("----------------------- PUT -------------------------");
			// 因为hateoas返回单个数据对象时不带id，所以下面要重新set一下
			Order before = tacoCloudClient.getOrderById(1);
			log.info("BEFORE:  " + before);
			before.setDeliveryName("huyihao");
			before.setId(1L);
			tacoCloudClient.updateOrder(before);
			Order after = tacoCloudClient.getOrderById(1);
			log.info("AFTER:  " + after);
		};
	}
	
	/**
	 * 参数1：指定基础URL
	 * 参数2：指定API生成JSON格式的响应，并且具有HAL风格的超链接
	 */
	@Bean
	public Traverson traverson() {
		Traverson traverson = new Traverson(URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON);
		return traverson;
	}
	
//	@Bean
//	public CommandLineRunner traversonGetIngredients(TacoCloudClient tacoCloudClient) {
//		return args -> {
//			Iterable<Ingredient> ingredients = tacoCloudClient.getAllIngredientsWithTraverson();
//			log.info("----------------------- GET INGREDIENTS WITH TRAVERSON -------------------------");
//			for (Ingredient ingredient : ingredients) {
//				log.info("   -  " + ingredient);
//			}
//		};
//	}
	
	@Bean
	public CommandLineRunner traversonSaveIngredient(TacoCloudClient tacoCloudClient) {
		return args -> {
			Ingredient pico = tacoCloudClient
					.addIngredient(new Ingredient("PICO", "Pico de Gallo", Ingredient.Type.SAUCE));
			List<Ingredient> allIngredients = tacoCloudClient.getAllIngredients();
			log.info("----------------------- ALL INGREDIENTS AFTER SAVING PICO -------------------------");
			for (Ingredient ingredient : allIngredients) {
				log.info("   -  " + ingredient);
			}
			tacoCloudClient.deleteIngredient(pico);
		};
	}

//	@Bean
//	public CommandLineRunner traversonRecentTacos(TacoCloudClient tacoCloudClient) {
//		return args -> {
//			Iterable<Taco> recentTacos = tacoCloudClient.getRecentTacosWithTraverson();
//			log.info("----------------------- GET RECENT TACOS WITH TRAVERSON -------------------------");
//			for (Taco taco : recentTacos) {
//				log.info("   -  " + taco);
//			}
//		};
//	}
	
}
