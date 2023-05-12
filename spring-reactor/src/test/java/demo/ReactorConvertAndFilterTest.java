package demo;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

// 转换和过滤反应式流
public class ReactorConvertAndFilterTest {

	/**
	 * skip操作
	 * 
	 * 跳过前3个数据
	 */
	@Test
	public void skipAFew() {
	    Flux<String> skipFlux = Flux.just(
	        "one", "two", "skip a few", "ninety nine", "one hundred")
	        .skip(3);
	    
	    skipFlux.subscribe(s -> System.out.println("skipFlux: " + s));

	    StepVerifier.create(skipFlux)
	        .expectNext("ninety nine", "one hundred")
	        .verifyComplete();
	}	

	/**
	 * skip操作
	 * 
	 * 跳过前4秒流出的数据，因为数据是每隔1秒流出一个，所以前4个数据会被跳过
	 */
	@Test
	public void skipAFewSeconds() {
	    Flux<String> skipFlux = Flux.just(
	        "one", "two", "skip a few", "ninety nine", "one hundred")
	        .delayElements(Duration.ofSeconds(1))
	        .skip(Duration.ofSeconds(4));

	    skipFlux.subscribe(s -> System.out.println("skipFlux: " + s));
	    
	    StepVerifier.create(skipFlux)
	        .expectNext("ninety nine", "one hundred")
	        .verifyComplete();
	}	
	
	/**
	 * take操作
	 * 
	 * 只获取前3个数据
	 */
	@Test
	public void take() {
	    Flux<String> nationalParkFlux = Flux.just(
	        "Yellowstone", "Yosemite", "Grand Canyon","Zion", "Grand Teton")
	        .take(3);
	    
	    nationalParkFlux.subscribe(s -> System.out.println("takeFlux: " + s));

	    StepVerifier.create(nationalParkFlux)
	        .expectNext("Yellowstone", "Yosemite", "Grand Canyon")
	        .verifyComplete();
	}	
	
	/**
	 * take操作
	 * 
	 * 只获取前3.5秒流出的数据
	 */	
	@Test
	public void takeAFewSeconds() {
	    Flux<String> nationalParkFlux = Flux.just(
	        "Yellowstone", "Yosemite", "Grand Canyon","Zion", "Grand Teton")
	        .delayElements(Duration.ofSeconds(1))
	        .take(Duration.ofMillis(3500));

	    nationalParkFlux.subscribe(s -> System.out.println("takeFlux: " + s));
	    
	    StepVerifier.create(nationalParkFlux)
	        .expectNext("Yellowstone", "Yosemite", "Grand Canyon")
	        .verifyComplete();
	}	
	
	/**
	 * filter操作
	 * 
	 * 过滤掉不满足要求的数据
	 */
	@Test
	public void filter() {
	    Flux<String> nationalParkFlux = Flux.just(
	        "Yellowstone", "Yosemite", "Grand Canyon","Zion", "Grand Teton")
	        .filter(np -> !np.contains(" "));

	    nationalParkFlux.subscribe(s -> System.out.println("filterFlux: " + s));
	    
	    StepVerifier.create(nationalParkFlux)
	        .expectNext("Yellowstone", "Yosemite", "Zion")
	        .verifyComplete();
	}	
	
	/**
	 * distinct操作
	 * 
	 * 过滤掉重复的数据
	 */
	@Test
	public void distinct() {
	    Flux<String> animalFlux = Flux.just(
	        "dog", "cat", "bird", "dog", "bird", "anteater")
	        .distinct();

	    animalFlux.subscribe(s -> System.out.println("distinctFlux: " + s));
	    
	    StepVerifier.create(animalFlux)
	        .expectNext("dog", "cat", "bird", "anteater")
	        .verifyComplete();
	}	
	
	/**
	 * 
	 * 映射反应式数据
	 * 
	 */
	/**
	 * Map操作
	 */
	@Test
	public void map() {
		Flux<Player> playerFlux = Flux.just("Michael Jordan", "Scottie Pippen", "Steve Kerr")
									  .map(n -> { String[] split = n.split("\\s"); return new Player(split[0], split[1]); });
		
		playerFlux.subscribe(s -> System.out.println("playerFlux: " + s));
		
		StepVerifier.create(playerFlux)
			        .expectNext(new Player("Michael", "Jordan"))
			        .expectNext(new Player("Scottie", "Pippen"))
			        .expectNext(new Player("Steve", "Kerr"))
			        .verifyComplete();		
	}
	
	/**
	 * flatMap操作，把数据拍扁
	 * 
	 * Schedulers支持的并发模型：
	 * 
	 * 	immediate - 在当前的线程中执行订阅
	 *  single - 在一个单一的、可重用的线程中执行订阅。对所有的调用者重用相同的线程
	 *  newSingle - 针对每个调用，使用专用的线程执行订阅
	 *  elastic - 在从无界弹性线程池中拉取的工作者线程中执行订阅。根据需要创建新的工作线程，并销毁空闲的工作者线程。
	 * 	parallel - 从一个固定大小的线程池中拉取的工作者线程中执行订阅，该线程池的大小和CPU的核心数一致
	 */
	@Test
	public void flatMap() {
	    Flux<Player> playerFlux = Flux
	        .just("Michael Jordan", "Scottie Pippen", "Steve Kerr")
	        .flatMap(n -> Mono.just(n).map(p -> {
	            String[] split = p.split("\\s");
	            return new Player(split[0], split[1]);
	        })
	        .subscribeOn(Schedulers.parallel())
	        );

	    playerFlux.subscribe(s -> System.out.println("playerFlux: " + s));
	    
	    List<Player> playerList = Arrays.asList(
			        new Player("Michael", "Jordan"),
			        new Player("Scottie", "Pippen"),
			        new Player("Steve", "Kerr"));

	    StepVerifier.create(playerFlux)
	        .expectNextMatches(p -> playerList.contains(p))
	        .expectNextMatches(p -> playerList.contains(p))
	        .expectNextMatches(p -> playerList.contains(p))
	        .verifyComplete();
	}	
	
	/**
	 * buffer操作
	 */
	@Test
	public void buffer() {
	    Flux<String> fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry");

	    // 限定每个集合最多只有3个数据
	    Flux<List<String>> bufferedFlux = fruitFlux.buffer(3);

	    bufferedFlux.subscribe(s -> System.out.println("bufferedFlux: " + s));
	    
	    StepVerifier
	        .create(bufferedFlux)
	        .expectNext(Arrays.asList("apple", "orange", "banana"))
	        .expectNext(Arrays.asList("kiwi", "strawberry"))
	        .verifyComplete();
	    
	    System.out.println();
	    
	    Flux.just("apple", "orange", "banana", "kiwi", "strawberry")
	    .buffer(3)
	    .flatMap(x -> 
	         Flux.fromIterable(x)
	             .map(y -> y.toUpperCase())
	             .subscribeOn(Schedulers.parallel())
	             .log()
	    ).subscribe();	    
	}	
	
	/**
	 * collectList操作: 将所有的数据都收集到一个List中
	 */
	@Test
	public void collectList() {
	    Flux<String> fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry");
	    Mono<List<String>> fruitListMono = fruitFlux.collectList();

	    StepVerifier
	        .create(fruitListMono)
	        .expectNext(Arrays.asList(
	            "apple", "orange", "banana", "kiwi", "strawberry"))
	        .verifyComplete();
	}	
	
	/**
	 * collectMap操作
	 */
	@Test
	public void collectMap() {
	    Flux<String> animalFlux = Flux.just("aardvark", "elephant", "koala", "eagle", "kangaroo");
	    
	    // 计算得到key，跟值一起组成k-v对，多个数据组成Map
	    Mono<Map<Character, String>> animalMapMono =
	        animalFlux.collectMap(a -> a.charAt(0));

	    animalMapMono.subscribe(s -> System.out.println("animalMapMono: " + s));
	    
	    StepVerifier
	        .create(animalMapMono)
	        .expectNextMatches(map -> {
	            return
	                map.size() == 3 &&
	                map.get('a').equals("aardvark") &&
	                map.get('e').equals("eagle") &&
	                map.get('k').equals("kangaroo");
	        })
	        .verifyComplete();
	}	
}

@Data
@AllArgsConstructor
class Player {
	private String firstName;
	private String lastName;
}
