package demo;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

// 在反应式类型上执行逻辑操作
public class ReactorLogicOpTest {

	/**
	 * all操作: 是否所有数据都满足条件
	 */
	@Test
	public void all() {
	    Flux<String> animalFlux = Flux.just(
	        "aardvark", "elephant", "koala", "eagle", "kangaroo");
	    
	    Mono<Boolean> hasAMono = animalFlux.all(a -> a.contains("a"));
	    StepVerifier.create(hasAMono)
	        .expectNext(true)
	        .verifyComplete();
	    
	    Mono<Boolean> hasKMono = animalFlux.all(a -> a.contains("k"));
	    StepVerifier.create(hasKMono)
	        .expectNext(false)
	        .verifyComplete();
	}	
	
	/**
	 * any()操作: 是否某些数据满足了条件
	 */
	@Test
	public void any() {
	    Flux<String> animalFlux = Flux.just(
	        "aardvark", "elephant", "koala", "eagle", "kangaroo");
	    
	    Mono<Boolean> hasAMono = animalFlux.any(a -> a.contains("t"));
	    StepVerifier.create(hasAMono)
	        .expectNext(true)
	        .verifyComplete();
	    
	    Mono<Boolean> hasZMono = animalFlux.any(a -> a.contains("z"));
	    StepVerifier.create(hasZMono)
	        .expectNext(false)
	        .verifyComplete();
	}	
}
