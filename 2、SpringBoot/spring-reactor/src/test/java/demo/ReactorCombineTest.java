package demo;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

// 组合反应式类型
public class ReactorCombineTest {

	/**
	 * 合并反应式类型
	 * 
	 * delayElements(Duration.ofMillis(500)): 每500ms发布一个条目
	 * delaySubscription(Duration.ofMillis(250)): 在订阅后再经过250ms后才开始发布数据
	 */
	@Test
	public void mergeFluxes() {
	    Flux<String> characterFlux = Flux
	        .just("Garfield", "Kojak", "Barbossa")
	        .delayElements(Duration.ofMillis(500)); 

	    Flux<String> foodFlux = Flux
	        .just("Lasagna", "Lollipops", "Apples")
	        .delaySubscription(Duration.ofMillis(250))
	        .delayElements(Duration.ofMillis(500));

	    Flux<String> mergedFlux = characterFlux.mergeWith(foodFlux);

	    mergedFlux.subscribe(s -> System.out.println("mergeFlux: " + s));
	    
	    // 订阅反应式类型，在数据流过时对应用数据断言，并在最后验证反应式流是否按预期完成
	    StepVerifier.create(mergedFlux)
	        .expectNext("Garfield")
	        .expectNext("Lasagna")
	        .expectNext("Kojak")
	        .expectNext("Lollipops")
	        .expectNext("Barbossa")
	        .expectNext("Apples")
	        .verifyComplete();
	}
	
	// 将两个Flux压缩在一起，产生一个元组Flux
	@Test
	public void zipFluxes() {
	    Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa");
	    Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples");

	    Flux<Tuple2<String, String>> zippedFlux = Flux.zip(characterFlux, foodFlux);
	    zippedFlux.subscribe(s -> System.out.println("zippedFlux: " + s));
	    
	    StepVerifier.create(zippedFlux)
	        .expectNextMatches(p ->
	            p.getT1().equals("Garfield") &&
	            p.getT2().equals("Lasagna"))
	        .expectNextMatches(p ->
	            p.getT1().equals("Kojak") &&
	            p.getT2().equals("Lollipops"))
	        .expectNextMatches(p ->
	            p.getT1().equals("Barbossa") &&
	            p.getT2().equals("Apples"))
	        .verifyComplete();
	}	
	
	// 压缩两个Flux，默认产生一个元组Flux，将元组再转换
	@Test
	public void zipFluxesToObject() {
	    Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa");
	    Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples");

	    Flux<String> zippedFlux = Flux.zip(characterFlux, foodFlux,
	                                   (c, f) -> c + " eats " + f);
	    zippedFlux.subscribe(s -> System.out.println("zippedFlux(no tuple): " + s));
	    StepVerifier.create(zippedFlux)
	        .expectNext("Garfield eats Lasagna")
	        .expectNext("Kojak eats Lollipops")
	        .expectNext("Barbossa eats Apples")
	        .verifyComplete();
	}	
	
	// 选择第一个流出数据的反应式类型进行发布
	@Test
	public void firstFlux() {
	    Flux<String> slowFlux = Flux.just("tortoise", "snail", "sloth")
	        .delaySubscription(Duration.ofMillis(100));
	    Flux<String> fastFlux = Flux.just("hare", "cheetah", "squirrel");

	    // reactor 3.5: Flux<String> firstFlux = Flux.firstWithSignal(slowFlux, fastFlux);
	    Flux<String> firstFlux = Flux.first(slowFlux, fastFlux);
	    firstFlux.subscribe(s -> System.out.println("firstFlux: " + s));
	    
	    StepVerifier.create(firstFlux)
	        .expectNext("hare")
	        .expectNext("cheetah")
	        .expectNext("squirrel")
	        .verifyComplete();
	}		
}
