# 一、从一个SpringBoot小程序开始

## 1、工具环境安装

​	推荐使用Spring Tool Suite4，官网下载：https://spring.io/tools。

​	配套工具安装lombok，官网下载：https://projectlombok.org/download。





## 2、初始化SpringBoot工程

​	File -> New -> Spring Starter Project

<img src="screenshot\1-创建SpringBoot项目.png" style="zoom:100%;" />

​	初始化  SpringBoot工程信息

<img src="screenshot\2-初始化SpringBoot.png" style="zoom:50%;" />

​	选择 Spring Boot 版本，选择要使用的功能对应的 Starter，然后点击 Finish，IDE 会自动完成剩下的工作。

<img src="screenshot\3-选择版本和starter.png" style="zoom:50%;" />

​	Starter 使开发人员避免陷入各种依赖的版本兼容问题的泥淖，只要 Spring Boot 库中有对应的 Starter 即可一键引入，并且官方保证依赖的兼容性。





## 3、默认配置和规则

​	一个 Spring Boot 工程完成初始化之后默认自动生成的文件目录结构如下：

<img src="screenshot\4-工程结构.png" style="zoom:80%;" />

* **mvnw和mvnw.cmd**：这是Maven包装器（wrapper）脚本。借助这些脚本，即便你的机器上没有安装Maven，也可以构建项目。
* **TacoCloudApplication.java**：这是Spring Boot主类，它会启动项目。
* **application.properties**：默认配置文件，若同时存在 application.properties 和 application.yml 则会优先加载前者的配置，所以如果要使用 application.yml，必须删除 application.properties。
* **static**：在这个文件夹下，你可以存放任意为浏览器提供服务的静态内容（图片、样式表、JavaScript等），该文件夹初始为空。
* **templates**：这个文件夹中存放用来渲染内容到浏览器的模板⽂件。这个文件夹初始是空的，不过我们很快就会往里面添加Thymeleaf模板。
* **TacoCloudApplicationTests.java**：这是⼀个简单的测试类，它能确保Spring应用上下文可以成功加载。







## 4、生成的 pom.xml 详解

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.9</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>sia</groupId>
	<artifactId>taco-cloud</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>taco-cloud</name>
	<description>Taco Cloud Example</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<!-- Spring-Boot: thymeleaf(模板引擎) -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<!-- Spring-Boot: web -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!-- devtools:
			1. 应用自动重启
			2. 浏览器自动刷新（静态资源如js、图片、css等）和禁用模板缓存 
			3. 自动启用H2数据库
		 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<!-- Spring-Boot: test -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
				
		<!-- 通过以下依赖项来解决此问题，同时支持旧版 HTML 格式。因为代码 charset="UTF-8"> 这里没有关闭元标记。 -->				
		<dependency>
			<groupId>net.sourceforge.nekohtml</groupId>
			<artifactId>nekohtml</artifactId>			                           
		</dependency>				
		
		<!-- lombok: 自动代码生成 -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		
		<!-- Spring-Boot: validation -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

* **spring-boot-starter-web** : web 工程必须引入的 starter。

* **spring-boot-starter-thymeleaf** : 使用 thymeleaf 模板引擎渲染网页。

* **spring-boot-devtools** ：为 Spring 开发人员提供的遍历的开发期工具，功能包括：

  * 代码变更后应用会自动重启；
  * 当面向浏览器的资源（如模板、JavaScript、css）等发生变化时，会自动刷新浏览器；
  * 自动禁用模板缓存；
  * 如果使用 H2 数据库，内置 H2 控制台。

  DevTools 在运行时，会在应用程序侧启动一个 LiveReload 服务器，浏览器也必须先安装 LiveReload 插件，这样就可以实现浏览器访问网页时，一些静态资源发生变化时，会自动刷新浏览器。

* **spring-boot-starter-test** ：为 Spring 程序提供编写单元测试支持的 starter。

* **spring-boot-starter-validation** ：校验网页提交的表单数据的合法性。

* **lombok** ：通过注解 POJO，使其不必写构造器、getter/setter。

* **nekohtml** ：使用 thymeleaf 模板时，若网页模板中的 xml 标签没有闭标签，可能会导致引擎解析异常，而加入本依赖可处理这种问题。

* **spring-boot-maven-plugin** ：maven 编译插件，功能包括：

  * 提供一个 Maven goal，允许通过 Maven 来运行应用；
  * 确保依赖的所有库都会包含在可执行 Jar 文件中，并且保证运行时在类路径下是可用的；
  * 在 Jar 中生成一个 manifest 文件，将引导类声明为可执行 Jar 的主类。





## 5、主类

```java
package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}
```

​	使用 **@SpringBootApplication** 表示是程序主类，负责引导应用执行。主类的包名是 **tacos**，默认 **tacos.*** 的包里的类都会被扫描到，如果使用了 **@Service、@Component、@Respository、@Controller** 等注解，则会被自动注册为 bean，若包名不符合该规则，或者想显式指定包扫描，则指定注解的 **scanBasePackages** 属性即可。

​	比如要增加对包 com.foo 进行扫描，则注解调整为：

```java
@SpringBootApplication(scanBasePackages = {"tacos", "com.foo"})
```

​	



## 6、处理 Web 请求

​	Spring 自带了一个 Web 开发框架 Spring MVC。Spring MVC 的核心是控制器的理念，控制器是处理请求并以某种方式进行信息相应的类。

​	这里定义一个控制器，处理浏览器的 "/" 请求。

```java
package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
}
```

​	**@Controller** 表示这是一个控制器，可以响应处理浏览器请求；**@GetMapping("/")** 表示只响应对 "/" 发起的 GET 请求；方法返回值是一个字符串 "home"，不代表直接返回这个字符串响应，而是表示要使用哪个视图，这个值会被解析为视图的逻辑名，Spring 会从资源目录（`src/main/resources/templates`）下找到对应的视图文件 home.html，内容如下：

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
	<head>
		<meta charset="UTF-8">
		<title>Taco Cloud</title>
	</head>
	<body>
		<h1>Welcome to...</h1>
		<img th:src="@{/images/TacoCloud.png}"></img>
		<br>
		<a href="/design">Start to design you taco!</a><br>
		<a href="/valid-design">Start to design you taco! (With validation)</a>
	</body>
</html>
```

​	除了静态的 HTML，这里还使用了 thymeleaf 模板引擎，可以用它来引用资源渲染数据，作用类似于 JSP。

​	启动容器，Boot Dashboard 可以看到 Spring Boot 项目列表，启动的应用会变绿，显示使用了 devtools，应用监听 8080 端口，上面的工具栏可以停止、重启、运行、调试应用，可以为应用打开一个 web 浏览器和控制台等。

<img src="screenshot\5-构建和启动应用.png" style="zoom:80%;" />

​	先点击启动，再点击浏览器，访问效果如下

<img src="screenshot\6-访问web.png" style="zoom:60%;" />

【演示项目github地址】

https://github.com/huyihao/Spring-Tutorial/tree/main/2%E3%80%81SpringBoot/taco-cloud





# 二、Web 表单处理和校验

## 1、信息展现

### （1）构建领域类

​	应用的领域是指它所要解决的主题范围。

​	比如在一个餐饮管理系统中，配料、菜品、订单、用户都是单独一个领域，映射到代码中就是对应的领域类。

​	现在在 Taco 餐饮系统中，配料、Taco、订单都是不同的领域，为其设计对应的 Java 领域类。

```java
package tacos;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *     配料领域类
 */
@Data							// 自动生成getter、setter
@RequiredArgsConstructor        // 自动生成初始化final成员的构造函数
public class Ingredient {
	private final String id;
	private final String name;
	private final Type type;
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}

package tacos;
import java.util.List;
import lombok.Data;

@Data
public class Taco {
	private String name;	
	private List<String> ingredients;	
}

package tacos;
import lombok.Data;

@Data
public class Order {
	  private String name;	  
	  private String street;	  
	  private String city;	  
	  private String state;	   
	  private String zip;	    
	  private String ccNumber;	    
	  private String ccExpiration;	    
	  private String ccCVV;
}
```

​	可以看到领域类，使用了 **@Data** 的注解，该注解可以为类成员自动生成 getter/setter；而 **@RequiredArgsConstructor**  注解可以自动生成初始化final成员的构造函数。这两个注解都是属于 lombok 组件带来的功能，为了避免 IDE 报错，提高使用体验，IDE 最好安装对应的 lombok 插件。

​	

### （2）创建控制器

​	访问首页时，可以看到有个超链接，会发起对 **"/design"** 资源的 GET 请求。

​	现在要创建一个控制器响应该请求，其完整代码如下：

```java
package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;

/**
 * @Slf4j 相当于 private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacosController.class); 
 */
@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacosController {

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
		  new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
		  new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
		  new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
		  new Ingredient("CARN", "Carnitas", Type.PROTEIN),
		  new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
		  new Ingredient("LETC", "Lettuce", Type.VEGGIES),
		  new Ingredient("CHED", "Cheddar", Type.CHEESE),
		  new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
		  new Ingredient("SLSA", "Salsa", Type.SAUCE),
		  new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		model.addAttribute("design", new Taco());
		return "design";
	}	
	
	@PostMapping
	public String processDesign(Taco design) {
		log.info("Processing design: " + design);
		return "redirect:/orders/current";
	}
	
	// 根据指定配料种类筛选
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream()
				          .filter(x -> x.getType().equals(type))
				          .collect(Collectors.toList());
	}
}
```

* **@Controller** ：定义控制器响应客户端请求，这个注解会将这个类识别为控制器，并将其作为组件扫描的候选者，Spring 会发现它并自动创建一个 DesignTacosController 实例，并将该实例作为 Spring 应用上下文的 bean。
* **@RequestMapping("/design")** ：表示只处理 "/design" 的请求。
* **@Slf4j** ：lombok 的注解，效果相当于定义了一个 log 对象，等同于代码 private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacosController.class);。



​	为了响应 GET 请求，定义使用 **@GetMapping** 注解的 **showDesignForm** 方法，为了返回数据到页面，方法头要有个 Model 类型的参数，需要参数里添加属性，我们想要在设计页面上展现配料的数据，所以先创建一个配料的列表，再将其根据不同的类型分为多个列表，每个列表的属性名就是列表的类型，所以一共会有 5 类配料。

​	最后设置一个 design 的属性，对应 Taco 的实例对象，将数据渲染到网页表单时，表单的 name 属性名就对应对象的属性名。

​	最后方法返回 "design"，Spring 会去模板目录中找对应的 **design.html** 的视图文件，现在我们还没有视图文件，所以接下来应该创建一个模板文件。



### （3）设计视图

​	在 `src/main/resources/templates` 目录下，创建一个 design.html 文件，代码如下：

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="UTF-8">
	<title>Taco Cloud</title>
	<link rel="stylesheet" th:href="@{/styles.css}" />
</head>

<body>
	<h1>Design your taco!</h1>
	<img th:src="@{/images/TacoCloud.png}" />

	<!-- th:object指定了要在表单中渲染的数据对象，也就是Controller传递过来的Model -->
	<form method="POST" th:object="${design}">		
		<!-- 配料栏 -->
		<div class="grid">
			<div class="ingredient-group" id="wraps">
				<h3>Designate your wrap:</h3>
				<div th:each="ingredient : ${wrap}">
					<input name="ingredients" type="checkbox" th:value="${ingredient.id}" />
					<span th:text="${ingredient.name}">INGREDIENT</span><br />
				</div>
			</div>

			<div class="ingredient-group" id="proteins">
				<h3>Pick your protein:</h3>
				<div th:each="ingredient : ${protein}">
					<input name="ingredients" type="checkbox" th:value="${ingredient.id}" />
					<span th:text="${ingredient.name}">INGREDIENT</span><br />
				</div>
			</div>

			<div class="ingredient-group" id="cheeses">
				<h3>Choose your cheese:</h3>
				<div th:each="ingredient : ${cheese}">
					<input name="ingredients" type="checkbox" th:value="${ingredient.id}" />
					<span th:text="${ingredient.name}">INGREDIENT</span><br />
				</div>
			</div>

			<div class="ingredient-group" id="veggies">
				<h3>Determine your veggies:</h3>
				<div th:each="ingredient : ${veggies}">
					<input name="ingredients" type="checkbox" th:value="${ingredient.id}" />
					<span th:text="${ingredient.name}">INGREDIENT</span><br />
				</div>
			</div>

			<div class="ingredient-group" id="sauces">
				<h3>Select your sauce:</h3>
				<div th:each="ingredient : ${sauce}">
					<input name="ingredients" type="checkbox" th:value="${ingredient.id}" /> 
					<span th:text="${ingredient.name}">INGREDIENT</span><br />
				</div>
			</div>
					
		</div>

		<!-- 提交栏 -->
		<div>
			<h3>Name your taco creation:</h3>
			<!-- th:field="*{name}" 会被解析为 id="name" name="name" -->
			<input type="text" th:field="*{name}" /> 			
			<br/><br/>
			
			<button>Submit your taco</button>
		</div>
	</form>
</body>
</html>
```

​	这里使用的 Thymeleaf 模板引擎，它类似于 jsp，就是在加载静态资源的同时，可以加载后端 Model 传输过来的数据，达到动态加载渲染数据的效果。

​	为了使用该模板引擎，首先需要在 html 标签中添加对应的命名空间 `xmlns:th="http://www.thymeleaf.org"`，就类似 Java 使用类要先 import 一样。



* **th:src** ：首先要加载网站图片，这里在 img 标签里使用了 `th:src="@{/images/TacoCloud.png}"` 的语法，th:src 表示使用 Thymeleaf 引擎的属性，@{} 表达式引用了相对上下文路径的图片，我们的静态资源是默认放在 `src/main/resources/static` 路径下，因为网页加载会在该路径下的 images 子目录尝试找到 TacoCloud.png 图片。
* **th:object**：指定了要在表单中渲染的数据对象，`${design}` 表示要渲染的是 Model 里的 design 属性，对应 Taco 对象，该对象有两个成员 name、ingredients。
* **th:field** ：`th:field="*{name}"` 的效果等同于 `id="${design.name}" name="${design.name}"`，因为后端传过来的数据没有赋予数据，所以网页渲染为空。
* **th:each** ：迭代元素集合，后端将不同的配料分类为不同的属性，每个属性名是配料类型名的小写，属性值是该类型配料 Ingredient 的列表，语法类似 foreach，`${wrap}` 表示配料类型为 Type.WRAP 的配料，，因为有两个配料属性该类型，所以该属性的列表中有两个 Ingredient 数据；在渲染循环体的内部，因为 Ingredient 对象有 id 和 name 两个成员，所以使用 `${ingredient.id}、${ingredient.name}` 来表示要渲染的具体数据字段。`<input name="ingredients">`  表示这个表单输入项的值，会映射到 th:object 数据 Taco 对象的 ingredients 中去。
* **th:text** ：将默认的 "INGREDIENT" 占位文本替换为 `${ingredient.name}` 对应的数据。



​	启动程序，访问首页并点击设计，可以看到如下的效果：

<img src="screenshot\7-展现信息.png" style="zoom:60%;" />

​	浏览器 F12 查看源码，可以看到这样的效果：

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="UTF-8">
	<title>Taco Cloud</title>
	<link rel="stylesheet" href="/styles.css" />
</head>

<body>
	<h1>Design your taco!</h1>
	<img src="/images/TacoCloud.png" />

	<!-- th:object指定了要在表单中渲染的数据对象，也就是Controller传递过来的Model -->
	<form method="POST">		
		<!-- 配料栏 -->
		<div class="grid">
			<div class="ingredient-group" id="wraps">
				<h3>Designate your wrap:</h3>
				<div>
					<input name="ingredients" type="checkbox" value="FLTO" />
					<span>Flour Tortilla</span><br />
				</div>
				<div>
					<input name="ingredients" type="checkbox" value="COTO" />
					<span>Corn Tortilla</span><br />
				</div>
			</div>

			...
					
		</div>

		<!-- 提交栏 -->
		<div>
			<h3>Name your taco creation:</h3>
			<!-- th:field="*{name}" 会被解析为 id="name" name="name" -->
			<input type="text" id="name" name="name" value="" /> 			
			<br/><br/>
			
			<button>Submit your taco</button>
		</div>
	</form>
</body>
</html>
```





## 2、表单提交

###（1）接交提交数据

​	网页展现信息后，客户会根据自己的需求设计 Taco，选择搭配的配料，并且为自己的个性化设计命名，并点击表单 POST 提交到后端。

<img src="screenshot\8-提交表单.png" style="zoom:60%;" />

一般后端是要对数据进行存储的，因为还没学习使用 Spring 来操作数据库，所以这里的代码只是简单打印提交的 Taco 对象，然后进行重定向。

```java
@PostMapping
public String processDesign(Taco design) {
	log.info("Processing design: " + design);
	return "redirect:/orders/current";
}
```

<img src="screenshot\9-控制器处理数据.png" style="zoom:100%;" />

​	重定向之后会跳到能处理 "/orders/current" 请求的控制器。

​	Taco 配料选择设置完后，接下来就要填写订单，现在还没有处理订单的控制器，所以新增一个，代码如下所示：

```java
package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(Order order) {
		log.info("Order submitted: " + order);
		return "redirect:/";
	}
	
}
```

​	首先要展现订单页面让客户填写，前端展示的表单和数据会跟 Model 里的 order 属性绑定，然后用户点击提交表单到后端处理。

​	同样的，页面的展现和渲染需要定义对应的视图模板 **orderForm.html**，代码如下：

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="UTF-8">
    <title>Taco Cloud</title>
    <link rel="stylesheet" th:href="@{/styles.css}" />
</head>
<body>
	<form method="POST" th:action="@{/orders}" th:object="${order}">
		<h1>Order your taco creations!</h1>
		
		<img th:src="@{/images/TacoCloud.png}"/>
      	<a th:href="@{/design}" id="another">Design another taco</a><br/>
      	
		<h3>Deliver my taco masterpieces to...</h3>
		<label for="name">Name: </label>
		<input type="text" th:field="*{name}"/>	
		<br/>
		
		<label for="street">Street address: </label>
		<input type="text" th:field="*{street}"/>
		<br/>
		
		<label for="city">City: </label>
		<input type="text" th:field="*{city}"/>
		<br/>
		
		<label for="state">State: </label>
		<input type="text" th:field="*{state}"/>				
		<br/>
		
		<label for="zip">Zip code: </label>
		<input type="text" th:field="*{zip}"/>	
		<br/><br/>
		
		<h3>Here's how I'll pay...</h3>
		<label for="ccNumber">Credit Card #: </label>
		<input type="text" th:field="*{ccNumber}"/>		
		<br/>
		
		<label for="ccExpiration">Expiration: </label>
		<input type="text" th:field="*{ccExpiration}"/>			
		<br/>
		
		<label for="ccCVV">CVV: </label>
		<input type="text" th:field="*{ccCVV}"/>		
		<br/><br/>
		
		<input type="submit" value="Submit order"/>      	
	</form>
</body>
</html>
```

* **th:href** ：引用网页渲染样式表，`@{/styles.css}` 的资源对应 `src/main/resources/static` 目录下。

* **th:action** ：表单提交的 URL，跟 Taco 提交的网页不同，Taco 展现的页面是 `/design` ，所以在表单中不设置 action 属性，直接提交，对应处理 `/design` 的 POST 请求的映射方法；Order 展现的页面是 `/orders/current` ，直接提交并不存在处理 `/orders/current` POST 请求的映射方法，所以这里要指定 action 属性。

* **th:field** ：跟 Taco 提交的表单一样，效果等同于 `id="${order.xxx}" name="${order.xxx}` 。

  网页展现订单的效果如下：

<img src="screenshot\10-订单展现.png" style="zoom:60%;" />

​	填写信息提交表单，可以在后端看到日志打印

<img src="screenshot\11-处理订单提交.png" style="zoom:60%;" />

​	但是这里可以看到很多数据的格式其实是不合法的，为了保障业务处理，需要在控制器里逐个检查 Order 中的每个对象的格式，如果不合法则返回报错给前端页面展现给客户看，这样客户才能修改并重新提交，直到所有的数据都符合业务规则。

​	但是这样一来，控制器的处理就复杂了起来，Spring 有没有更好的方案？答案当然是有。



### （2）校验表单输入

#### Ⅰ、引入依赖

​	要使用 Spring 的表单校验功能，首先要引入对应的 starter：

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```



#### Ⅱ、声明校验规则

​	即对 POJO 中的属性，使用 validation 的注解标注校验规则，在另一个包目录下新建类：

```java
package tacos.validation.object;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {

	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;	
	
	@NotNull
	@Size(min = 2, message = "You must choose at least 2 ingredient")
	private List<String> ingredients;	
	
}

@Data
public class Order {
	 
	  @NotBlank(message="Name is required")	 
	  private String name;	  

	  @NotBlank(message="Street is required")	 
	  private String street;	 

	  @NotBlank(message="City is required")	 
	  private String city;	 

	  @NotBlank(message="State is required")	 
	  private String state;	 

	  @NotBlank(message="Zip code is required")	  
	  private String zip;	  

	  @CreditCardNumber(message="Not a valid credit card number")	  
	  private String ccNumber;	  

	  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
	           message="Must be formatted MM/YY")	  
	  private String ccExpiration;	  

	  @Digits(integer=3, fraction=0, message="Invalid CVV")	  
	  private String ccCVV;
}
```



#### Ⅲ、表单后端校验

​	我们在 home.html，添加一个超链接，演示带校验的表单提交

```html
<a href="/valid-design">Start to design you taco!</a>
```

​	在另一个包目录下，新建一个 DesignTacosController，代码如下：

```java
package tacos.validation.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.validation.object.Taco;

@Slf4j
@Controller("validDesignTacosController")
@RequestMapping("/valid-design")
public class DesignTacosController {

	// 相当于每次访问都将菜单放到model中返回
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
		  new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
		  new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
		  new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
		  new Ingredient("CARN", "Carnitas", Type.PROTEIN),
		  new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
		  new Ingredient("LETC", "Lettuce", Type.VEGGIES),
		  new Ingredient("CHED", "Cheddar", Type.CHEESE),
		  new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
		  new Ingredient("SLSA", "Salsa", Type.SAUCE),
		  new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
		  model.addAttribute(type.toString().toLowerCase(),
		      filterByType(ingredients, type));
		}
	}	
	
	@GetMapping
	public String showDesignForm(Model model) {
		model.addAttribute("design", new Taco());
		return "valid-design";
	}	
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "valid-design";
		}
		log.info("Processing design: " + design);
		return "redirect:/valid-orders/current";
	}
	
	// 根据指定配料种类筛选
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream()
				          .filter(x -> x.getType().equals(type))
				          .collect(Collectors.toList());
	}
}
```

​	因为 Controller 注册时默认 Bean id 为类名首字母小写，为了避免冲突，这里显式指定 Bean id 为 `validDesignTacosController`，因为避免处理请求路径冲突，将路径改为 `/valid-design` 。

​	首先定义了一个被 **@ModelAttribute** 注解的方法，该方法的效果是，对类中的所有带 Model 参数的方法，都会设置 Ingredient 的属性到 Model 里去，方便页面渲染数据，这样就避免了在需要设置 Ingredient 属性到 Model 里去的方法里都重复写以下代码：

```java
		List<Ingredient> ingredients = Arrays.asList(
		  new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
		  new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
		  new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
		  new Ingredient("CARN", "Carnitas", Type.PROTEIN),
		  new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
		  new Ingredient("LETC", "Lettuce", Type.VEGGIES),
		  new Ingredient("CHED", "Cheddar", Type.CHEESE),
		  new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
		  new Ingredient("SLSA", "Salsa", Type.SAUCE),
		  new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
```

* **@Valid** ：表示要对 Taco 对象的数据进行校验。

* **@ModelAttribute("design")** ：表示对象映射的是表单头 th:object 对应的 `design` 对象。

* **Error error** ：Errors 参数保存经过 Spring Validatation 组件校验之后的处理结果。

* **Model model** ：Model 参数，因为表单校验可能不通过，这时候要返回 `/valid-design` 的页面，页面上要显示不同种类的配料数据，所以方法头里要有这个参数。

  如果表单校验有错误，则 `errors.hasErrors()` 会返回 false，从而会重新回到设计的页面让客户重选。



#### Ⅳ、前端展现错误

​	后端光校验了还不完整，还要把校验的结果告诉客户，所以要在前端网页上展现，客户才能针对性去修改要提交的表单，所以在前端要添加对应的一些代码，如下：

```html
...
<body>
	<h1>Design your taco!</h1>
	<img th:src="@{/images/TacoCloud.png}" />

	<form method="POST" th:object="${design}">
		<span class="validationError" th:if="${#fields.hasErrors('ingredients')}" th:errors="*{ingredients}">Ingredient Error</span>
		
		<!-- 配料栏 -->
		<div class="grid">
			<div class="ingredient-group" id="wraps">
				<h3>Designate your wrap:</h3>
				<div th:each="ingredient : ${wrap}">
					<input name="ingredients" type="checkbox" th:value="${ingredient.id}" />
					<span th:text="${ingredient.name}">INGREDIENT</span><br />
				</div>
			</div>

			...
			
		</div>

		<!-- 提交栏 -->
		<div>
			<h3>Name your taco creation:</h3>
			<input type="text" th:field="*{name}" /> 			
			<span class="validationError" th:if="${#fields.hasErrors('name')}" th:errors="*{name}">Name Error</span>
			<br/><br>
			
			<button>Submit your taco</button>
		</div>
	</form>
</body>
</html>
```

​	对每个属性，使用 span 标签来展现错误，使用 **th:if** 决定是否要展现该元素；**fields** 属性的 **hasErrors()** 方法会检查 Taco 对象对应的 name、ingredients 域是否存在错误，其内容就是 Errors 参数里的内容。

​	对 Taco 提交的表单，我们限定了至少选择两样配料，并且名字要超过 5 个字符，不满足时报错如下：

<img src="screenshot\12-展现表单错误.png" style="zoom:60%;" />

​	Order 表单的处理跟 Taco 的类似，对应新增的带校验的控制器代码如下：

```java
package tacos.validation.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.validation.object.Order;


@Slf4j
@Controller("validOrderController")
@RequestMapping("/valid-orders")
public class OrderController {

	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "valid-orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors) {
		if (errors.hasErrors()) {
			return "valid-orderForm";
		}
		
		log.info("Order submitted: " + order);
		return "redirect:/";
	}
	
}
```

​	页面代码同样添加了是否展现报错信息的 span 元素，代码如下：

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="UTF-8">
    <title>Taco Cloud</title>
    <link rel="stylesheet" th:href="@{/styles.css}" />
</head>
<body>
	<form method="POST" th:action="@{/valid-orders}" th:object="${order}">
		<h1>Order your taco creations!</h1>
		
		<img th:src="@{/images/TacoCloud.png}"/>
      	<a th:href="@{/design}" id="another">Design another taco</a><br/>
      	
      	<div th:if="${#fields.hasErrors()}">
        	<span class="validationError">Please correct the problems below and resubmit.</span>
      	</div>
      	
		<h3>Deliver my taco masterpieces to...</h3>
		<label for="name">Name: </label>
		<input type="text" th:field="*{name}"/>
		<span class="validationError"
		      th:if="${#fields.hasErrors('name')}"
		      th:errors="*{name}">Name Error</span>		
		<br/><br/>
		
		<label for="street">Street address: </label>
		<input type="text" th:field="*{street}"/>
		<span class="validationError"
		      th:if="${#fields.hasErrors('street')}"
		      th:errors="*{street}">Street Error</span>
		<br/><br/>
		
		<label for="city">City: </label>
		<input type="text" th:field="*{city}"/>
		<span class="validationError"
		      th:if="${#fields.hasErrors('city')}"
		      th:errors="*{city}">City Error</span>
		<br/><br/>
		
		<label for="state">State: </label>
		<input type="text" th:field="*{state}"/>		
		<span class="validationError"
		      th:if="${#fields.hasErrors('state')}"
		      th:errors="*{state}">State Error</span>		
		<br/><br/>
		
		<label for="zip">Zip code: </label>
		<input type="text" th:field="*{zip}"/>	
		<span class="validationError"
		      th:if="${#fields.hasErrors('zip')}"
		      th:errors="*{zip}">Zip Error</span>
		<br/><br/>
		
		<h3>Here's how I'll pay...</h3>
		<label for="ccNumber">Credit Card #: </label>
		<input type="text" th:field="*{ccNumber}"/>	
		<span class="validationError"
		      th:if="${#fields.hasErrors('ccNumber')}"
		      th:errors="*{ccNumber}">CC Num Error</span>		
		<br/> <br/>
		
		<label for="ccExpiration">Expiration: </label>
		<input type="text" th:field="*{ccExpiration}"/>		
		<span class="validationError"
		      th:if="${#fields.hasErrors('ccExpiration')}"
		      th:errors="*{ccExpiration}">CC Num Error</span>		
		<br/><br/>
		
		<label for="ccCVV">CVV: </label>
		<input type="text" th:field="*{ccCVV}"/>		
		<span class="validationError"
		      th:if="${#fields.hasErrors('ccCVV')}"
		      th:errors="*{ccCVV}">CC Num Error</span>		
		<br/><br/>
		
		<input type="submit" value="Submit order"/>      	
	</form>
</body>
</html>
```

​	测试效果如下：

<img src="screenshot\13-订单提交报错.png" style="zoom:60%;" />





## 3、视图控制器

​	如果一个控制器非常简单，不需要填充模型和处理输入，那么可以直接声明为视图控制器：也就是只将请求转发到视图而不做其他事情的控制器。

​	在目前定义的控制器中，HomeController 适合用视图控制器来实现，代码如下：

```java
package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 声明视图控制器
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 为URL "/" 指定视图home
		// 即 http://localhost:8080/ 访问的是home.html
		registry.addViewController("/").setViewName("home");
	}
}
```

​	首先视图控制器配置类 **WebConfig** 实现了 **WebMvcConfigurer** 接口，并实现 **addViewControllers** 方法，**ViewControllerRegistry** 参数用来在方法中添加视图控制器，`addViewController("/")` 表示视图控制器会针对 "/" 路径执行 GET 请求，`setViewName("home")` 表示用 home 视图（即 home.html）来渲染展现。

​	WebConfig 要求是一个配置类，所以要添加 **@Configuration** 注解，也可以让启动主类实现接口并实现对应方法（主类使用了 @SpringBootApplication，默认就是个配置类），如下：

```java
@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}  
}
```

​	这样可以较少项目中配置类的数量，但是从持续开发的角度，最好还是为每种配置（Web、数据、安全等）创建新的配置类，这样能够保持应用的引导配置类尽可能地整洁和简单。





## 4、视图模板

### （1）模板引擎使用

​	Spring 配置支持多种模板引擎，除了默认的 JSP，还有 Thymeleaf、Freemarker、Mustcache、Groovy，使用的方式大同小异，引入对应的 starter，模板文件都是放在 templates 目录下，各个模板引擎使用的语法略有差异。

<img src="screenshot\14-模板引擎.png" style="zoom:60%;" />

​	比如使用 Mustcache，首先引入依赖：

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-mustache</artifactId>
</dependency>
```

​	原来使用 Thymeleaf 语法的代码片段：

```html
<div th:each="ingredient : ${wrap}">
	<input name="ingredients" type="checkbox" th:value="${ingredient.id}" />
	<span th:text="${ingredient.name}">INGREDIENT</span><br />
</div>
```

​	用 Mustache 语法则为：

```html
{{#wrap}}
<div>
	<input name="ingredients" type="checkbox" value="{{id}}" />
	<span th:text="{{name}}">INGREDIENT</span><br />
</div>
{{/wrap}}
```

​	{{#wrap}} 代码块会遍历请求中 key 为 wrap 属性并为每个条目渲染嵌入式 HTML。{{id}}、{{name}} 标签分别会引用每个 Ingredient 的 id 和 name 属性。



### （2）模板缓存

​	模板会有缓存，即相同的内容第一次加载后就会缓存起来，第二次则直接使用缓存，但是在开发阶段不希望缓存生效，可以通过设置禁用模板缓存参数来实现：

```properties
# Thymeleaf
spring.thymeleaf.cache=false

# Mustache
spring.mustache.cache=false

# Freemarker
spring.freemarker.cache=false

# Groovy Templates
spring.groovy.template.cache=false
```

​	如果是 yml 配置文件，格式为：

```yaml
spring:
  thymeleaf:
    cache: false
    
  mustache:
    cache: false
    
  freemarker:
    cache: false
    
  groovy:
    template:
      cache: false
```

【演示项目github地址】

https://github.com/huyihao/Spring-Tutorial/tree/main/2%E3%80%81SpringBoot/taco-cloud

















# 附录A：SpringBoot 中的各种注解

## @ComponentScan

​	启用组件扫描。使我们可以使用 **@Service、@Component、@Respository、@Controller** 等注解声明类，Spring 启动扫描到后会将其自动注册为上下文的组件。



## @Service

​	作用于类，使得类可以被扫描注册到上下文中。



## @Component

​	作用于类，使得类可以被扫描注册到上下文中。



## @Respository

​	作用于类，使得类可以被扫描注册到上下文中。



## @Controller

​	作用于类，使得类可以被扫描注册到上下文中。



## @SpringBootApplication

​	本注解是一个组合注解，组合了其他3个注解：

* **@SpringBootConfiguration**：将该类声明为配置类，这样就支持在 Java 类中定义 bean，是@Configuration 注解的特殊形式。
* **@EnableAutoConfiguration**：启用 Spring Boot 的自动配置，比如数据源我们只需在 application.properties 中，使用 JDBC 或 ORM 框架时 Spring Boot 会帮我们自动注入数据源。
* **@ComponentScan**：启用组件扫描。使我们可以使用 **@Service、@Component、@Respository、@Controller** 等注解声明类，Spring 启动扫描到后会将其自动注册为上下文的组件。







# 附录B：使用 SpringBoot 问题汇总

## 1、访问视图网页报错模板引擎解析异常的问题

```xml
<!-- 通过以下依赖项来解决此问题，同时支持旧版 HTML 格式。因为代码 charset="UTF-8"> 这里没有关闭元标记。 -->				
<dependency>
	<groupId>net.sourceforge.nekohtml</groupId>
	<artifactId>nekohtml</artifactId>			                           
</dependency>
```





