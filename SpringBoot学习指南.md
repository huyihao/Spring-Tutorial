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

  ​



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







# 三、使用操作数据

​	Spring 的理念之一就是 "面向接口编程"，将接口定义和具体实现分隔开，应用系统使用的是接口，这样方便替换使用不同的实现。

​	在数据访问中，访问数据需要先创建一个**数据访问对象（Data Access Object）**，为了面向接口编程，需要定义一个 **DAO 接口**，而 DAO 接口可以有多个不同的 **DAO 实现**， 业务系统中使用的是服务对象，服务对象通过接口来访问 DAO，这样既使得服务对象易于测试，又不再与特定的数据访问实现绑定在一起。

<img src="screenshot\15-DAO.png" style="zoom:60%;" />

## 1、定义数据对象

> **Ingredient 定义**

```java
package tacos.domain;

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
```



> **Taco 定义**

```java
package tacos.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {

	private Long id;
	private Date createdAt;
	
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;	
	
	@NotNull(message = "You must choose at least 2 ingredient")
	@Size(min = 2, message = "You must choose at least 2 ingredient")
	private List<Ingredient> ingredients;	
	
}
```

​	因为 Taco 和 Ingredient 是一对多关系，原来的 Taco 定义只是简单的存储了 Ingredient 的 id 的字符串，这里直接存储关联的数据对象。

​	Taco 表单勾选配料时上送上来的只是个字符串，而 Taco 对象的配料列表元素却是 Ingredient 对象，所以需要有个转换器来处理提交表单数据从字符串到 Ingredient 的转换，这里需要定义一个类实现 Spring 的 **Converter** 接口：

```java
package tacos.domain.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.data.IngredientRepository;
import tacos.domain.Ingredient;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

	@Autowired
	IngredientRepository repository;	
	
	@Override
	public Ingredient convert(String source) {
		return repository.findOne(source);
	}

}
```

​	使用 @Component 将其注册到上下文中，Converter 需要传入两个类型参数，第一个是转换前的类型，第二个是转换后的类型，然后实现类要实现 convert() 方法，方法的参数和返回值是由 Converter 的类型参数决定的。这里的转换是通过插表实现的。



> **Order 定义**

```java
package tacos.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {
	 
	private Long id;
	
	private Date placedAt;
	
	@NotBlank(message = "Name is required")
	private String deliveryName;

	@NotBlank(message = "Street is required")
	private String deliveryStreet;

	@NotBlank(message = "City is required")
	private String deliveryCity;

	@NotBlank(message = "State is required")
	private String deliveryState;

	@NotBlank(message = "Zip code is required")
	private String deliveryZip;

	@CreditCardNumber(message = "Not a valid credit card number")
	private String ccNumber;

	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	private String ccExpiration;

	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	private String ccCVV;
	
	private List<Taco> tacos = new ArrayList<Taco>();
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}	
}
```

​	Order 和 Taco 的关系同样是一对多，所以 Order 类中有一个 Taco 列表，并提供了添加 Taco 的方法。





## 2、定义数据接口

​	每个领域对象的数据操作都会有对应一个数据接口，这里为 Ingredient、Taco、Order 分别创建一个接口。

```java
package tacos.data;
import tacos.domain.Ingredient;

public interface IngredientRepository {
	Iterable<Ingredient> findAll();				// 查询所有配料信息
	Ingredient findOne(String id);              // 根据id，查询单个Ingredient
	Ingredient save(Ingredient ingredient);     // 保存Ingredient对象
}

package tacos.data;
import tacos.domain.Taco;

public interface TacoRepository {
	Taco save(Taco design);
}

package tacos.data;

import java.util.List;
import tacos.domain.Order;

public interface OrderRepository {
	Order save(Order order);
	List<Order> queryOrders();
}
```





## 3、使用 JdbcTemplate

​	直接使用 JDBC 也是可以操作数据库，但是不建议使用，因为每次操作都要书写加载驱动、创建连接、资源释放等一堆模板代码，JdbcTemplate 就是 Spring 针对 JDBC 使用不便提出的解决方案。

​	首先引入依赖：

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>	

<!-- 如果要连接 MySQL 数据库则加上下面的驱动 -->
<dependency>
	<groupId>com.mysql</groupId>
	<artifactId>mysql-connector-j</artifactId>
	<scope>runtime</scope>
</dependency>	
```

​	每个数据接口都会有对应的实现类：

```java
@Repository
public class JdbcTacoRepository implements TacoRepository {
	private JdbcTemplate jdbc; 
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
  
    // ...
}
```

​	实现类首先实现了接口，并且使用 **@Repository** 注解将其定义为一个数据仓库的 Bean，该类中内嵌了一个 JdbcTemplate 的对象，并通过自动注入 **@Autowired** 注解实现该属性的初始化，然后各个具体的数据操作方法中使用该对象进行具体的数据的增删查改等操作，接下来就要在上下文中创建一个 JdbcTemplate 的对象这样才能在启动初始化时注入。

###（1）直接使用 Spring 配置

​	如果不使用 Spring Boot，直接使用 Spring，首先要显式配置（不管是 XML 还是 Java 配置）一个数据源，然后定义一个 JdbcTemplate 并将数据源注入，如下所示：

```xml
<!-- sakila是安装MySQL时可选安装的演示数据库 -->
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
	<property name="driverClassName" value="com.mysql.jdbc.Driver" />
	<property name="url" value="jdbc:mysql://localhost:3306/tacocloud?useSSL=false" />
	<property name="username" value="root" />
	<property name="password" value="root" />
</bean>
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
	<constructor-arg ref="dataSource"/>
</bean>
```

​	使用的时候在 DAO 接口实现中注入 JdbcTemplate 对象即可。



### （2）使用 Spring Boot 配置

​	在 application.properties 中配置以下属性即可：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tacocloud
spring.datasource.username=root
spring.datasource.password=root
```

​	这样 Spring Boot 检测到 DAO Impl 需要注入 JdbcTemplate 时就会自动生成并注入。



### （3）使用 H2 嵌入式数据库

​	在开发阶段，我们不想马上创建一个数据库实体，而是希望有一个便捷的开发环境，H2 数据库能满足这样的需求，H2 数据库是一个嵌入式的数据库，使用它只需要引入对应的依赖即可，开箱即用：

```xml
<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>
```

​	如果使用了 H2 数据库，application.properties 要调整为：

```properties
# mem表示数据库是基于内存的，应用启动即创建，停止自动回收
spring.datasource.url=jdbc:h2:mem:taco
spring.datasource.username=root
spring.datasource.password=root
```

​	如果不配置上述属性也没关系，默认连接的数据库连接串格式为 `jdbc:h2:mem:8d73170a-0367-491e-ab69-189ab830c49f` ，后面是随机生成的 36 位数据库名，具体可以查看启动日志。

<img src="screenshot\16-h2.png" style="zoom:100%;" />

​	可以看到 H2 还提供了一个 Web Console，访问 `/h2-console` 即可。

<img src="screenshot\17-h2-console.png" style="zoom:60%;" />

​	默认用户名为 `sa`，密码为空。

​	使用 H2 内存数据库，我们依然需要定义表来存储数据，因此依然需要有 DDL 和 DML。默认情况下，Spring Boot 检测到使用了 H2，会在启动时自动去加载位于 `src/main/resources` 目录下的 **schame.sql** 和 **data.sql**，下面我们分别定义数据模式和要插入的数据。

​	新增 schema.sql，内容如下：

```sql
create table if not exists Ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null,
  primary key(id)
);

create table if not exists Taco (
  id identity,
  name varchar(50) not null,
  createdAt timestamp not null,
  primary key(id)
);

create table if not exists Taco_Ingredients (
  taco bigint not null,
  ingredient varchar(4) not null
);

alter table Taco_Ingredients
    add foreign key (taco) references Taco(id);
alter table Taco_Ingredients
    add foreign key (ingredient) references Ingredient(id);

create table if not exists Taco_Order (
	id identity,
	deliveryName varchar(50) not null,
	deliveryStreet varchar(50) not null,
	deliveryCity varchar(50) not null,
	deliveryState varchar(20) not null,
	deliveryZip varchar(10) not null,
	ccNumber varchar(16) not null,
	ccExpiration varchar(5) not null,
	ccCVV varchar(3) not null,
    placedAt timestamp not null
);

create table if not exists Taco_Order_Tacos (
	tacoOrder bigint not null,
	taco bigint not null
);

alter table Taco_Order_Tacos
    add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos
    add foreign key (taco) references Taco(id);
```

​	我们针对 Ingredient、Taco 领域分别定义了一张表，因为一个 Taco 会跟多个 Ingredient 关联，所以又定义了一张关联表 `Taco_Ingredients` ，表中只有两个字段，分别外联到 Taco、Ingredient 表的主键 id 上。`Taco_Order` 是订单表，`Taco_Order_Tacos` 是关联表，同样只有两个字段分别外联到 Order、Taco 表的主键 id上。

​	新增 data.sql，主要是插入配料数据：

```sql
delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;
insert into Ingredient (id, name, type) 
                values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, type) 
                values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type) 
                values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, type) 
                values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type) 
                values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type) 
                values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type) 
                values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type) 
                values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type) 
                values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type) 
                values ('SRCR', 'Sour Cream', 'SAUCE');
```

​	这样启动之后，Spring Boot 就会将对应的 DDL、DML 在 H2 上初始化执行，打开 H2 Console，可以看到对应的表和数据。

<img src="screenshot\18-h2-init.png" style="zoom:60%;" />

​	**注意，如果使用的 ORM 框架是 JPA，因为 JPA 会根据使用了 @Entity 注解的类自动生成 DDL，会不执行 schema.sql 和 data.sql，因此需要在配置文件中禁用该特性。**

```properties
spring.jpa.hibernate.ddl-auto=none

# 若使用了 JPA，因此会使用hibernate，所以开发测试阶段如果想查看DB操作的详情，将日志级别调整为debug
logging.level.org.hibernate=DEBUG
```

​	如果想要显式设置数据库初始化的行为，则可参考下面的属性配置：

```properties
# 数据初始化的模式: never-不初始化  always-每次启动都初始化
spring.sql.init.mode=always
spring.sql.init.platform=h2
spring.sql.init.username=sa
spring.sql.init.password=
spring.sql.init.schema-locations=classpath*:schema.sql
spring.sql.init.data-locations=classpath*:data.sql.sql
```



### （4）实现数据接口

> **Ingredient 接口的实现类**

```java
package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.domain.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

	private JdbcTemplate jdbc; 
	
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Iterable<Ingredient> findAll() {		
		return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
	}

	@Override
	public Ingredient findOne(String id) {		
		return jdbc.queryForObject("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)", 
					ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
		return ingredient;
	}

	// 将查询出来的每一行通过本方法转化为对象
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(rs.getString("id"), 
							  rs.getString("name"), 
							  Ingredient.Type.valueOf(rs.getString("type")));
	}
}
```

​	通过 JdbcTemplate 的 **query()** 方法可以查询数据列表，**queryObject()** 方法适合通过唯一索引查询单个数据的 SQL，**update()** 方法可以执行插入、更新的 SQL，后面跟着要传入的参数列表。

​	

> **Taco 接口的实现**

```java
package tacos.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tacos.domain.Ingredient;
import tacos.domain.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

	private JdbcTemplate jdbc; 
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Taco save(Taco taco) {
		// 保存taco对象到Taco表，并返回插入数据行对应的id
		long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		// 将ingredient和对应的taco对象的id插入到Taco_Ingredients表中
		for (Ingredient ingredient : taco.getIngredients()) {
			saveIngredientToTaco(ingredient, tacoId);
		}
		return taco;
	}

	private long saveTacoInfo(Taco taco) {
		taco.setCreatedAt(new Date());
		PreparedStatementCreatorFactory pcf = new PreparedStatementCreatorFactory(
													"insert into Taco (name, createdAt) values (?, ?)", 
													Types.VARCHAR, Types.TIMESTAMP
												);
		pcf.setReturnGeneratedKeys(true);
		PreparedStatementCreator psc =
				pcf.newPreparedStatementCreator(
					Arrays.asList(
						taco.getName(),
						new Timestamp(taco.getCreatedAt().getTime()))
				);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(psc, keyHolder);
		
		return keyHolder.getKey().longValue();
	}
	
    private void saveIngredientToTaco(
        Ingredient ingredient, long tacoId) {
		    jdbc.update(
		        "insert into Taco_Ingredients (taco, ingredient) " +
		        "values (?, ?)",
		        tacoId, ingredient.getId());
    }	
}
```

​	`save()` 方法做了保存 Taco 的所有要做的处理。

​	首先调用 `saveTacoInfo()` 方法来将 Taco 对象保存到 Taco 表中，并且返回生成的记录的 id。为了拿到这个 id，首先需要创建一个 `PreparedStatementCreatorFactory` 对象，传入插表的 SQL，并指定数据类型，然后通过 `setReturnGeneratedKeys(true)` 设置插表自动生成的 id 要返回；接着通过 `PreparedStatementCreatorFactory`  对象创建一个 `PreparedStatementCreator` 对象，传入参数列表，对应第一步操作的变量占位，注意数据类型要跟前面设置的对应；最后创建一个 `KeyHolder` 对象，用来存放 SQL 执行返回的 id，并作为参数传递给 `update()` 方法，执行结束后，通过该对象返回数据 id。

​	拿到 Taco 插入的数据 id 后，遍历 Taco 对象的配料列表，把 Taco id 和 Ingredient id 的对应关系插入到关联表中。



> **Order 接口的实现**

​	插入数据要获取 id 时，Taco 接口的实现比较繁琐，Spring JDBC 提供了更加简约的 **SimpleJdbcInsert**，因为在订单插表时，也要对 Taco 表进行操作，因此创建两个 SimpleJdbcInsert 对象分别处理对两个表的插入操作。

 	**SimpleJdbcInsert**  对象的创建，首先要传入 JdbcTemplate，然后通过 **withTableName()** 方法设置要操作的表名，**usingGeneratedKeyColumns()** 指定了是否要自动生成表的 id，也就是 schema.sql 中声明为 identify 的字段，Taco_Order_Tacos 表是关联表，所以不指定。

​	**ObjectMapper** 对象是帮助 POJO 转化为 Map，从而符合 **executeAndReturnKey()** 方法传入的参数类型要求。

```java
package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.domain.Order;
import tacos.domain.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository {

	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private ObjectMapper objectMapper;
	private JdbcTemplate jdbc; 

	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		this.orderInserter = new SimpleJdbcInsert(jdbc)
				                .withTableName("Taco_Order")
				                .usingGeneratedKeyColumns("id");	// 使用系统生成的id并返回

		this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
									.withTableName("Taco_Order_Tacos");

		this.objectMapper = new ObjectMapper();
		this.jdbc = jdbc;
	}

	@Override
	public Order save(Order order) {
		order.setPlacedAt(new Date());
		long orderId = saveOrderDetails(order);
		order.setId(orderId);
		
		List<Taco> tacos = order.getTacos();
	    for (Taco taco : tacos) {
	        saveTacoToOrder(taco, orderId);
	    }

	    return order;
	}

	private long saveOrderDetails(Order order) {
		@SuppressWarnings("unchecked")
		Map<String, Object> values = objectMapper.convertValue(order, Map.class);
		// objectMapper会将placedAt的值转化为long，所以这里要覆盖设置以下map
		values.put("placedAt", order.getPlacedAt());

		long orderId = orderInserter.executeAndReturnKey(values).longValue();
		return orderId;
	}
	
	private void saveTacoToOrder(Taco taco, long orderId) {
	    Map<String, Object> values = new HashMap<>();
	    values.put("tacoOrder", orderId);
	    values.put("taco", taco.getId());
	    orderTacoInserter.execute(values);
	}

	@Override
	public List<Order> queryOrders() {
		return jdbc.query("select * from Taco_Order", new RowMapper<Order>() {
			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order order = new Order();
				order.setDeliveryName(rs.getString("deliveryName"));
				order.setDeliveryStreet(rs.getString("deliveryStreet"));
				order.setDeliveryCity(rs.getString("deliveryCity"));
				order.setDeliveryState(rs.getString("deliveryState"));
				order.setDeliveryZip(rs.getString("deliveryZip"));
				order.setCcNumber(rs.getString("ccNumber"));
				order.setCcExpiration(rs.getString("ccExpiration"));
				order.setCcCVV(rs.getString("ccCVV"));
				order.setId(rs.getLong("id"));
				order.setPlacedAt(rs.getDate("placedAt"));
				return order;
			}
		});		
	}	
		
}
```

​	在 **saveOrderDetails()** 方法中，首先将 POJO 转化为 Map，然后调用 SimpleJdbcInserter 的 **executeAndReturnKey()** 方法执行 SQL，返回的对象为 Number 类型，通过 **longValue()** 方法转化为 long 类型并返回。这里要注意，**convertValue()** 方法有bug，会将placedAt的值转化为long，所以这里要覆盖设置一下map。拿到 Order id 后，遍历订单中的 Taco 将其 id 和 Order id 的关联关系写到 Taco_Order_Tacos 表中。

​	在 **saveTacoToOrder()** 方法中，直接通过 **execute()** 方法将 map 中的字段与数据表中的字段一一对应插入数据。





## 4、控制器使用 DAO

​	完成了数据操作层的处理，控制器中要使用起来，这里需要重新编写控制器处理的代码。

### （1）DesignTacosController

```java
package tacos.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.domain.Ingredient;
import tacos.domain.Ingredient.Type;
import tacos.domain.Order;
import tacos.domain.Taco;


@Slf4j						// 相当于 private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacosController.class); 
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacosController {

	private IngredientRepository ingredientRepo;
	private TacoRepository tacoRepo;
	
	@Autowired
	public DesignTacosController(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
	}
	
	// 相当于每次访问都将菜单放到model中返回
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		// 从数据库中查询到所有的配料数据
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}	
	
	@GetMapping
	public String showDesignForm(Model model) {
		//model.addAttribute("design", new Taco());
		return "design";
	}	
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}		
	
	@ModelAttribute(name = "design")
	public Taco taco() {
		return new Taco();
	}
	
	// 这里的 @ModelAttribute("design") 对应的就是 showDesignFrom() 方法里的model
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, 
								Errors errors,
								@ModelAttribute Order order) {
		if (errors.hasErrors()) {
			return "design";
		}
		log.info("Processing design: " + design);
		
		Taco saved = tacoRepo.save(design);
		order.addDesign(saved);
		
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

​	处理 Taco 设计的控制器，要展现配料信息，处理表单提交又要写入 Taco 数据，因此需要在类中注入 Ingredient、Taco 的数据操作对象。

​	展现数据时，要通过 **IngredientRepository** 查到所有的配料数据，传入 Model，最后在网页上展现。

​	处理表单提交时，因为一个订单可能包含多个 Taco，所以控制器的订单要保存每个 Taco 的数据状态，需要订单信息在多个请求中都能出现，因此 Model 的 order 属性的保持需要贯穿整个会话，所以通过 **@SessionAttributes("order")** 指定模型对象要保存在 session 中。

​	Order 参数带有 **@ModelAttribute** 注解，表明它的值应该是来源于模型的，Spring MVC 不会尝试将请求参数绑定到它上面。



### （2）OrderController

```java
package tacos.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.data.OrderRepository;
import tacos.domain.Order;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository orderRepo;
	
	@Autowired
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		
		orderRepo.save(order);
		sessionStatus.setComplete();
		
		return "redirect:/orders/list";
	}
	
	@ModelAttribute(name = "orders")
	private void addOrdersToModel(Model model) {
		List<Order> orders = orderRepo.queryOrders();
		model.addAttribute("orders", orders);
	}
	
	@GetMapping("/list")
	public String listOrders() {
		return "orderList";
	}
}
```

​	模型对象中的 order 属性是保存在 session 中的，因此订单控制器同样在类上使用了 **@SessionAttributes("order")** ，这样控制器就能在提交订单时，感知到 Order 中的 Taco 列表，并传递给 **OrderRepository** 对象操作保存数据。

​	当一个订单被提交保存处理时，order 会话的存在已无意义，所以这里通过 **sessionStatus.setComplete()** 来结束会话。



### （3）测试

​	在原来打印 Taco 日志的地方，会去执行保存 Taco 的操作，然后将提交的 Taco 添加到订单中。

​	测试一下代码，提交 taco 表单时：

<img src="screenshot\19-插入taco.png" style="zoom:60%;" />

​	这时候还没提交订单，但是 taco 和 taco_ingredients 表都已有了数据

<img src="screenshot\20-taco.png" style="zoom:60%;" />

<img src="screenshot\21-tacoIngredients.png" style="zoom:60%;" />

​	在订单表单提交页面上，点击再设计一个 Taco，然后再次提交 Taco，可以看到会话中的 order 依然保存了上次提交的 Taco 数据

<img src="screenshot\22-order.png" style="zoom:60%;" />

​	这次填写订单信息提交了，可以看到，会话中的 order 数据确实一直保持着

<img src="screenshot\23-order.png" style="zoom:60%;" />

​	订单提交完，重定向到 "/orders/list"，该页面会展现所有的订单数据，要渲染的订单数据通过 **addOrdersToModel()** 方法添加到数据模型中的 orders 属性。

<img src="screenshot\24-orderlist.png" style="zoom:60%;" />





## 5、使用 JPA

### （1）Spring Data 简介

​	Spring Data 是Spring 的一个子项目， 旨在统一和简化对各类型持久化存储， 而不拘泥于是关系型数据库还是NoSQL 数据存储。

​	无论是哪种持久化存储， 数据访问对象（或称作为DAO，即Data Access Objects）通常都会提供对单一域对象的CRUD （创建、读取、更新、删除）操作、查询方法、排序和分页方法等。Spring Data则提供了基于这些层面的统一接口（CrudRepository，PagingAndSortingRepository）以及对持久化存储的实现。

​	比较流行的几个 Spring Data 项目包括：

* **Spring Data JPA** ：基于关系型数据库进行 JPA 持久化（鬼佬喜欢用，国内不流行）。
* **Spring Data MongoDB** ：持久化到 Mongo 文档数据库。
* **Spring Data Neo4j** ：持久化到 Neo4j 图数据库。
* **Spring Data Redis** ：持久化到 Redis key-value 存储。
* **Spring Data Cassandra** ：持久化到 Cassandra 数据库。



### （2）添加依赖

​	Spring Boot 通过 JPA Starter 来添加 Spring Data JPA。注意，JPA 是一个标准，还需要有具体的实现，默认 JPA 会引入 Hibernate 作为实现。

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

​	如果想要使用其他实现，则示例如下：

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
  	<!-- 去除默认的实现 -->
    <exclusions>
        <exclusion>
            <artifactId>hibernate-entitymanager</artifactId>
            <groupId>org.hibernate</groupId>
        </exclusion>
    </exclusions>  
</dependency>
<!-- 使用其他JPA实现 -->
<dependency>
	<groupId>org.eclipse.persistence</groupId>
	<artifactId>eclipselink</artifactId>  
  	<version></version>
</dependency>
```



### （3）将领域对象标注为实体

​	JPA 中的实体，是跟数据表挂钩的，也就是说一个实体类对象对应数据表里一条数据，要将领域对象标注为实体，需要使用 **@Entity** 注解。

​	下面是几个实体类使用 JPA 后重写后的代码。

> **Ingredient 实体**

```java
package tacos.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 *     配料领域类
 */
@Data							// 自动生成getter、setter
@RequiredArgsConstructor        // 自动生成初始化final成员的构造函数
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Ingredient {
	@Id
	private final String id;
	private final String name;
	@Enumerated(EnumType.STRING)
	private final Type type;
	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	
}
```

​	跟原来的类相比，没什么大的变化，只是单纯加了一个 @Entity 注解而已。



> **Taco 实体**

```java
package tacos.jpa.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

// JPA中的驼峰标识自动映射到数据表中下划线标识
@Data
@Entity					// 声明为JPA实体
@Table(name = "Taco")
public class Taco {

	@Id					// 指定为数据库中唯一标识
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date createdat;
	
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;	
	
	/**
	 * cascade级联操作策略:
	 * 
	 * 1. CascadeType.PERSIST 级联新建 
     * 2. CascadeType.REMOVE 级联删除 
     * 3. CascadeType.REFRESH 级联刷新 
     * 4. CascadeType.MERGE 级联更新 
     * 5. CascadeType.ALL 四项全选 
     * 6. 默认，关系表不会产生任何影响
	 */
	@ManyToMany(targetEntity = Ingredient.class, cascade = CascadeType.ALL)
    @JoinTable(name = "Taco_Ingredients",
    		   joinColumns = @JoinColumn(name = "taco",  referencedColumnName = "id"),    
    		   inverseJoinColumns = @JoinColumn(name = "ingredient", referencedColumnName = "id"))
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;

	@PrePersist
	void createdAt() {
		this.createdat = new Date();
	}
}
```

​	除了 @Entity 注解外，这里还用了 **@Table(name = "Taco")** 表示该实体对应的表是 Taco，如果不使用的话，默认按照类名来映射表，所以若类名跟表名有差异，务必加上这个注解。

  	Taco 跟 Ingredient 是多对多的关系，所以这里用了 **@ManyToMany** ，**targetEntity** 表示目标映射的实体类型，**cascade** 表示级联操作策略，表示对 Taco 表进行增删改时，关联表里的数据要做的级联操作。

* **@JoinTable** ：表述了 Taco 跟 Ingredient 的关联情况。

* **name** ：属性表示关联的表为 Taco_Ingredients。

* **joinColumns** ：表示连接表主表的外键，主表为 Taco，name 表示关联表的字段，referencedColumnName 表示关联的主表的列名。

* **inverseJoinColumns** ：表示连接表副表的外键，副表为 Ingredient，name 表示关联表的字段，referencedColumnName  表示关联的副表的列名。

* **@PrePersist** ：表示 Taco 持久化之前，使用这个方法将 createdat 设置为当前的日期和事件。

  如果不配置默认的配置等同于：

```java
@JoinTable(name = "Taco_Ingredients",
		   joinColumns = @JoinColumn(name = "taco_id",  referencedColumnName = "id"),    
		   inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
```



> **Order 实体**

```java
package tacos.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

/**
 * JPA会将驼峰标识的字段映射到数据表中下划线标识字段
 */
@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id					
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date placedat;
	
	@NotBlank(message = "Name is required")
	private String deliveryName;

	@NotBlank(message = "Street is required")
	private String deliveryStreet;

	@NotBlank(message = "City is required")
	private String deliveryCity;

	@NotBlank(message = "State is required")
	private String deliveryState;

	@NotBlank(message = "Zip code is required")
	private String deliveryZip;

	@CreditCardNumber(message = "Not a valid credit card number")
	private String ccNumber;

	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	private String ccExpiration;

	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	private String ccCVV;
	
	/**
	 * 在下单之前Taco对象对应的数据已经持久化到数据库里了，所以这里的级联策略不能用CascadeType.ALL
	 * 否则会同步去往Taco表里插记录，因为记录已存在id一致，所以会报错
	 */
	@ManyToMany(targetEntity = Taco.class, cascade = CascadeType.MERGE)
    @JoinTable(name = "Taco_Order_Tacos",
			   joinColumns = @JoinColumn(name = "tacoorder",  referencedColumnName = "id"),    
			   inverseJoinColumns = @JoinColumn(name = "taco", referencedColumnName = "id"))	
	private List<Taco> tacos = new ArrayList<Taco>();
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}	
	
	@PrePersist
	void placedat() {
		this.placedat = new Date();
	}
}
```

​	这里要注意级联策略有了变化，改成了级联更新，即在往 Taco_Order 表插入数据时，如果 Order 实体对象的 Taco 列表中的 Taco 有了变化（之前已经插入到表里去了），则同步更新 Taco 表里的数据。



### （4）声明 Repository

​	Repository 就是一个 DAO 数据接口，只不过使用 JPA 时，它还要扩展 JPA 里的接口，一般情况下扩展 **CrudRepository** 接口即可。CrudRepository 定义了很多用于 CURD 操作的方法。它是参数化的，第一个参数是 repository 要持久化的实体类型，第二个参数是实体 ID 属性的类型。

​	几个 Repository 定义如下：

```java
package tacos.jpa.data;

import org.springframework.data.repository.CrudRepository;
import tacos.jpa.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}


package tacos.jpa.data;

import org.springframework.data.repository.CrudRepository;
import tacos.jpa.domain.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}

package tacos.jpa.data;

import org.springframework.data.repository.CrudRepository;
import tacos.jpa.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
```

​	 JPA 的牛逼之处在于定义完接口之后就不用实现类了，**因为 Spring Data JPA 会在运行期间自动生成实现类，并将其注册到上下文中，使用的时候将接口注入到控制器中即可**。



### （5）使用 Repository

​	控制器的代码逻辑只是从之前的接口改成使用扩展 CrudRepository 的 JPA Repository 接口。

> **DesignTacosController** 

```java
package tacos.jpa.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.jpa.data.IngredientRepository;
import tacos.jpa.data.TacoRepository;
import tacos.jpa.domain.Ingredient;
import tacos.jpa.domain.Ingredient.Type;
import tacos.jpa.domain.Order;
import tacos.jpa.domain.Taco;


@Slf4j						// 相当于 private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacosController.class); 
@Controller("jpa-designTacosController")
@RequestMapping("/jpa-design")
@SessionAttributes("order")
public class DesignTacosController {

	private IngredientRepository ingredientRepo;
	private TacoRepository tacoRepo;
	
	@Autowired
	public DesignTacosController(IngredientRepository ingredientRepo, 
								 TacoRepository tacoRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
	}
	
	// 相当于每次访问都将菜单放到model中返回
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		// 从数据库中查询到所有的配料数据
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}	
	
	@GetMapping
	public String showDesignForm(Model model) {		
		return "jpa-design";
	}	
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}		
	
	@ModelAttribute(name = "design")
	public Taco taco() {
		return new Taco();
	}
	
	// 这里的 @ModelAttribute("design") 对应的就是 showDesignFrom() 方法里的model
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, 
								Errors errors,
								@ModelAttribute Order order) {
		if (errors.hasErrors()) {
			return "jpa-design";
		}
		log.info("Processing design: " + design);
		
		Taco saved = tacoRepo.save(design);
		order.addDesign(saved);
		
		return "redirect:/jpa-orders/current";
	}
	
	// 根据指定配料种类筛选
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream()
				          .filter(x -> x.getType().equals(type))
				          .collect(Collectors.toList());
	}
}
```



> **OrderController** 

```java
package tacos.jpa.web;

import javax.validation.Valid;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.jpa.data.OrderRepository;
import tacos.jpa.domain.Order;


@Controller("jpa-orderController")
@RequestMapping("/jpa-orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository orderRepo;
	
	@Autowired
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm() {
		return "jpa-orderForm";
	}
	
	/**
	 * @AuthenticationPrincipal User user   获取当前会话的用户
	 */
	@PostMapping
	public String processOrder(@Valid Order order, 
							   Errors errors, 
							   SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "jpa-orderForm";
		}
		
		orderRepo.save(order);
		sessionStatus.setComplete();
		
		return "redirect:/jpa-orders/list";
	}
	
	@ModelAttribute(name = "orders")
	private void addOrdersToModel(Model model) {
		Iterable<Order> orders = orderRepo.findAll();
		model.addAttribute("orders", IteratorUtils.toList(orders.iterator()));
	}
	
	@GetMapping("/list")
	public String listOrders() {
		return "jpa-orderList";
	}
	
}
```

​	这里需要注意一个点，使用 H2 数据库，在 schame.sql 中即使字段是驼峰标志，在数据表里还是部分大小写，也不会自动转化为下划线，但是 JPA 实体定义为驼峰标识，执行 Repository 方法进行数据库操作时会被解析为下划线标识。

​	比如：

```sql
create table if not exists Taco_Order (
	id identity,
	deliveryName varchar(50) not null,
	deliveryStreet varchar(50) not null,
	deliveryCity varchar(50) not null,
	deliveryState varchar(20) not null,
	deliveryZip varchar(10) not null,
	ccNumber varchar(16) not null,
	ccExpiration varchar(5) not null,
	ccCVV varchar(3) not null,
    placedAt timestamp not null
);
```

​	在 H2 数据库中建库的效果是：

<img src="screenshot\25-H2schema.png" style="zoom:60%;" />

​	但是对应的 Order 实体，假如字段定义为 **deliveryName**，在执行数据操作前会被解析为 **delivery_name** 字段，从而导致报错找不到这个列。

<img src="screenshot\26-h2error.png" style="zoom:100%;" />

​	只要注意上述的点，就能够跑通流程了，在首页中添加超链接如下：

```html
<a href="/jpa-design">Start to design you taco!(use jpa)</a><br>	
```

​	其他的模板详见后面的github地址的仓库。



### （6）自定义 Repository

​	除了 CrudRepository 提供的基本 CRUD 操作之外，我们可能有时候需要自定义一些操作方法，依然还是不需要具体实现，但是需要遵循一定的规则。

​	Spring Data 会检查 repository 接口的所有方法，解析方法的名称，并基于被持久化的对象来试图推测方法的目的。Spring Data 定义了一组小型的领域特定语言（**Domain-Specific Language，DSL**），持久化的细节都是通过 repository 方法的签名来描述的。

​	比如：

```java
List<Order> findByDeliveryZip(String deliveryZip);
```

​	repository 方法由一个动词（find）、可选主题（order）、关键词（By）以及一个断言（DeliveryZip）组成。

​	等同于 SQL：

```sql
select * from Taco_Order where deliveryzip = #{deliveryZip};
```

​	再看一个更复杂的例子：

```java
List<Order> readOrdersByDeliveryZipAndPlacedAtBetweenOrderByDeliveryName(String deliveryZip, Date startDate, Date endDate);
```

​	动词是 read、主题是 orders、关键词 by/and/between、断言 DeliveryZip/PlacedAt，等同于 SQL：

```sql
select * from Taco_Order where deliveryzip = #{deliveryZip} 
and placedat between #{startDate} and #{endDate} order by deliveryname;
```

* **动词**：可以使用 **find、read、get**

* **操作符**：

  * IsAfter、After

  * IsGreaterThan、GreaterThan、IsGreaterThanEqual、GreaterThanEqual

  * IsBefore、Before

  * IsLessThan、LessThan、IsLessThanEqual、LessThanEqual

  * IsBetween、Between

  * IsNull、Null

  * IsNotNull、NotNull

  * IsIn、In

  * IsNotIn、NotIn

  * IsStartingWith、StartingWith、StartsWith

  * IsEndingWith、EndingWith、EndsWith

  * IsContaining、Containing、Contains

  * IsLike、Like

  * IsNotLike、NotLike

  * IsTrue、True

  * IsFalse、False

  * IsEquals

  * IsNot、Not

  * IgnoringCase、IgnoresCase、AllIgnoringCase、AllIgoresCase



  如果要执行一些非常复杂的查询，方法名的定义可能面临失控的风险。在这种情况下，可以将方法定义为任何想要的名称，通过添加 **@Query** 注解来实现，如：

  ```java
  @Query("Order o where o.deliveryCity = 'Seattle'")
  List<Order> readOrderSDeliveredInSeattle();
  ```

  ​

【JPA使用教程】https://wwwhxstrive.com/subject/open_jpa/569.htm

【演示项目github地址】https://github.com/huyihao/Spring-Tutorial/tree/main/2%E3%80%81SpringBoot/taco-cloud-data-persistence







# 四、Spring Security

​	Web 应用容易遭到各种攻击，所以采用一些安全措施来保护应用正常使用和信息不被窃取篡改非常必要。Spring Security 就是这么一个组件。

## 1、启用

​	要使用 Spring Security，添加对应的 starter 即可：

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>	
<!-- 如果使用的是thymeleaf模板要引入下面的依赖 -->
<dependency>
	<groupId>org.thymeleaf.extras</groupId>
	<artifactId>thymeleaf-extras-springsecurity5</artifactId>
</dependency>	
```

​	启动应用，日志中会打印随机生成密码信息，默认用户名是 `user`

```
Using generated security password: 9d05346a-e240-46cc-9c53-87de94f5734d
```

​	访问应用，会首先跳到登录页 `http://localhost:8080/login`

<img src="screenshot\27-login.png" style="zoom:70%;" />

​	输入正确的用户名和密码，就有权限访问应用了。

​	通过将 Security Starter 添加到项目的构建文件中，可以得到如下的安全特性：

* 所有的 HTTP 请求路径都需要认证；
* 不需要特定的角色和权限；
* 没有登录页面；
* 认证过程是通过 HTTP basic 认证对话框实现的；
* 系统只有一个用户，用户名为 user。



​	可以看到以上的安全特性，充其量只能称之为一个 demo，并不能真正地满足以下的功能需求：

* 通过登录页面提示客户进行认证，而不是使用 HTTP basic 对话框；

* 提供多个用户，并提供一个注册页面，这样新用户能够注册进来；

* 对不同的请求路径，执行不同的安全规则。比如主页和注册页面根本不需要进行认证。

  为了满足上述要求，需要做一些显式的配置，覆盖掉自动配置为我们提供的功能。







## 2、配置

​	需要定义一个 Spring Security 的基础配置类，该安全类要继承 `WebSecurityConfigurerAdapter` 类，并重写 `configure(AuthenticationManagerBuilder auth)` 方法。

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
      	...
    }
  
	/**
	 * 保护web请求，定义授权规则
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
        /**
         * 1、formLogin()：指定支持基于表单的身份验证
         * 2、未使用 FormLoginConfigurer#loginPage(String) 指定登录页时，将自动生成一个登录页面，亲测此页面引用的是联网的 bootStrap 的样式，所以断网时，样式会有点怪
         * 3、当用户没有登录、没有权限时默认会自动跳转到登录页面(默认 /login),当登录失败时，默认跳转到 /login?error,登录成功时会放行
         * 4、.defaultSuccessUrl("/design", true)：强制要求用户在登录之后统一跳转到"/design"页面
         */		
		http.formLogin().loginPage("/login");      
    }
}

/**
 * 密码编码。Spring Security 高版本必须进行密码编码，否则报错
 */
class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }
 
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
```

​	在 configure() 方法中，配置用户存储，Spring Security 为配置用户存储提供了多个可选方案，包括：

* 基于内存的用户存储
* 基于 JDBC 的用户存储
* 以 LDAP 作为后端的用户存储
* 自定义用户详情服务

  `configure(HttpSecurity http)` 方法 Web 请求保护授权规则，目前还没有配置任何规则，默认访问所有页面都需要保证先登录，`http.formLogin().loginPage("/login")` 表示使用了指定的自定义登录页面的 URL，如果只是简单访问页面，则在视图控制器中添加以下一行即可：

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// ...
		// 不设置视图名，则默认跟路径名相同，即http://localhost:8080/login 访问的是login.html
		registry.addViewController("/login");
	}
}
```

​	login.html 代码如下：

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" 
      xmlns:th="http://www.thymeleaf.org">
  <head>
    <title>Taco Cloud</title>
  </head>
  
  <body>
    <h1>Login</h1>
    <img th:src="@{/images/TacoCloud.png}"/>
    
    <div th:if="${error}"> 
      Unable to login. Check your username and password.
    </div>
    
    <p>New here? Click 
       <a th:href="@{/register}">here</a> to register.</p>
    
    <!-- tag::thAction[] -->
    <form method="POST" th:action="@{/login}" id="loginForm">
    <!-- end::thAction[] -->
      <label for="username">Username: </label>
      <input type="text" name="username"/><br/>
      
      <label for="password">Password: </label>
      <input type="password" name="password"/><br/>
      
      <input type="submit" value="Login"/>
    </form>
  </body>
</html>
```

### （1）基于内存的用户存储

​	下面展现了配置两个用户：

```java
auth.inMemoryAuthentication()
	.passwordEncoder(new MyPasswordEncoder())
	.withUser("buzz").password("infinity").roles("USER")	
     // 高版本使用roles(xxx)不使用authorities(xxx)
	.and()
	.withUser("woody").password("bullseye").roles("USER");
```

​	用户密码为：buzz/infinity、woody/bullseye，授权用户角色是 USER。



### （2）自定义用户认证

​	自定义用户认证，既可以自行定义表结构来注册用户和用户登录认证。

> **数据表**

```sql
create table if not exists Taco_User (
	id identity,
	username varchar(50) not null,
	password varchar(256) not null,
	fullname varchar(50) not null,
	street varchar(50) not null,
	city varchar(50) not null,
	state varchar(50) not null,
	zip varchar(50) not null,
	phone_number varchar(50) not null
);
```



> **领域对象**

```java
package tacos.jpa.domain;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Table(name = "Taco_User")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final String username;
	private final String password;	
	
	private final String fullname;
	private final String street;
	private final String city;
	private final String state;
	private final String zip;
	private final String phoneNumber;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	// 返回用户被授予权限的一个集合
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 所有的用户都被授予USER权限
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	// 用户的账户是否可用或者过期
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 用户的账户是否被未锁定
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() {		
		return true;
	}
}
```

​	类上用了例行的 JPA、lombok 注解，因为用户表名跟实体类名不一致，所以要用 **@Table** 来显式标注。

​	通过实现 Spring Security 的 **UserDetails** 接口，能够提供更多信息给框架，比如用户都被授予了哪些权限以及用户的账号是否可用。

​	 `getAuthorities()` 返回用户被授予权限的一个集合，这里表明所有的用户都被授予了 ROLE_USER 权限。各种 is...Expired() 方法要返回一个 boolean 值，表明用户的账号是否可用或过期。



> **Repository 接口**

```java
package tacos.jpa.data;

import org.springframework.data.repository.CrudRepository;

import tacos.jpa.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	
}
```

​	使用 JPA，因此继承了 `CrudRepository` 接口，除此之外还自定义了一个 `findByUsername()` 方法，Spring Data JPA 会在运行时自动生成这个接口的实现，相当于下面 SQL 的执行效果：

```sql
select * from Taco_User where username = #{username};
```



> **创建用户详情服务**

​	Spring Security 的 **UserDetailsService** 接口在认证用户时要用到，因此需要定义一个实现类，并注入到 **SecurityConfig** 中。

```java
package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tacos.jpa.data.UserRepository;
import tacos.jpa.domain.User;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

	private UserRepository userRepo;
	
	@Autowired
	public UserRepositoryUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return user;
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}

}
```

​	`loadUserByUsername()` 会在用户登录认证时用到，实现中先是注入了上下文的 UserRepository 对象，然后通过它来查询用户是否存在，存在则返回，不存在则抛出一个 `UsernameNotFoundException` 异常。



> **配置使用自定义的用户认证**

​	万事俱备，现在在 `configure(AuthenticationManagerBuilder auth)` 方法里使用

```java
/**
 * 自定义用户认证
 * 使用Spring Security的UserDetails、UserDetailsService接口
 */
auth.userDetailsService(userDetailsService)
	.passwordEncoder(encoder());				// 设置密码转码器
```

​	用户的密码明文储存在数据库里是不安全的，因此密码需要先经过加密转码后的处理才能保存到数据库中，这里需要在 SecurityConfig 中设置密码转码器。

```java
@Bean
public PasswordEncoder encoder() {
	return new StandardPasswordEncoder("53cr3t");
}
```

​	passwordEncoder() 方法可以接受 Spring Security 中 PasswordEncoder 接口的任意实现。Spring Security 的加密模块包括了多个这样的实现。

* **BCryptPasswordEncoder**：使用 bcrypt 强哈希加密。

* **NoOpPasswordEncoder**：不进行任何转码。

* **Pbkdf2PasswordEncoder**：使用 PBKDF2 加密。

* **SCryptPasswordEncoder**：使用 scrypt 加密。

* passwordEncoder：使用 SHA-256 哈希加密。

  也可使用自定义的 PasswordEncoder 实现。




> **注册用户**

​	现在已经有了自定义的用户详情服务，需要定义一个控制器来展现和处理用户注册，代码如下：

```java
package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.jpa.data.UserRepository;
import tacos.jpa.domain.User;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {		
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	// 用户注册页
	@GetMapping
	public String registerForm() {
		return "registration";
	}	
	
	// 接收表单上送的数据，注册用户
	@PostMapping
	public String processRegistration(RegistrationForm form) {
		User user = form.toUser(passwordEncoder);
		userRepo.save(user);
		return "redirect:/login";
	}	
}
```

​	用户访问 `"/register"` 即可访问用户注册页，对应的模板是 registration.html，代码如下：

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" 
      xmlns:th="http://www.thymeleaf.org">
  <head>
    <title>Taco Cloud</title>
  </head>
  
  <body>
    <h1>Register</h1>
    <img th:src="@{/images/TacoCloud.png}"/>    
    
    <form method="POST" th:action="@{/register}" id="registerForm">
    
        <label for="username">Username: </label>
        <input type="text" name="username"/><br/>

        <label for="password">Password: </label>
        <input type="password" name="password"/><br/>

        <label for="confirm">Confirm password: </label>
        <input type="password" name="confirm"/><br/>

        <label for="fullname">Full name: </label>
        <input type="text" name="fullname"/><br/>
    
        <label for="street">Street: </label>
        <input type="text" name="street"/><br/>
    
        <label for="city">City: </label>
        <input type="text" name="city"/><br/>
    
        <label for="state">State: </label>
        <input type="text" name="state"/><br/>
    
        <label for="zip">Zip: </label>
        <input type="text" name="zip"/><br/>
    
        <label for="phone">Phone: </label>
        <input type="text" name="phone"/><br/>
    
        <input type="submit" value="Register"/>
    </form>
    
  </body>
</html>
```

​	表单提交时会由 `processRegistration()` 方法响应处理，定义一个 **RegistrationForm** 类来映射绑定提交的表单数据，代码如下：

```java
package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import tacos.jpa.domain.User;

@Data
public class RegistrationForm {

	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
	}
	
}
```

​	转化为 User 对象时，会传入转码器对象，对密码进行转码处理，然后再持久化到数据库中。





## 3、保护 Web 请求

### （1）授权规则

​	修改 SecurityConfig 的 `configure(HttpSecurity http)` 方法。

```java
	/**
	 * 保护web请求，定义授权规则
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.authorizeRequests()
			.antMatchers("/design", "/orders").hasRole("USER")
			.antMatchers("/", "/**").permitAll();
	
		http.formLogin().loginPage("/login");
		
        http.logout().logoutSuccessUrl("/");		
		
        http.csrf().ignoringAntMatchers("/h2-console/**", "/design/**", "/orders/**");        
        http.headers().frameOptions().sameOrigin();
	}
```

* Ⅰ、只有经过认证的角色为 USER 的用户才能访问 "/design"、"/orders"，而其他请求对所有客户均可用。

* Ⅱ、使用指定的登录 URL，绑定自定义的登录页面。

* Ⅲ、用户登出后跳转的 URL

* Ⅳ、默认情况下 Spring 会开启 csrf 攻击防护，如果是 POST 请求，则必须验证 Token，如果没有，就会报错 403，无权限访问，即使上面对目标请求路径授权了也不行，这里配置对一些路径的访问忽略防护。

* Ⅴ、h2-console 默认禁止页面展示 <iframe> 标签，设置同源策略即可。




### （2）防止跨站请求伪造

​	跨站请求伪造**（Cross-Site Request Forgery，CSRF）**是一种常见的安全攻击。它会让用户在一个恶意的 Web 页面上填写信息，然后自动将表单以攻击受害者的身份提交到另一个应用上。

​	例如，用户看到一个来自攻击者的 Web 站点的表单，这个站点会自动将数据 POST 到用户银行 Web 站点的 URL 上（这个站点可能缺乏安全防护），实现转账的操作。用户可能根本不知道发生了攻击，直到他们发现账号上的钱已经不翼而飞。

​	为了防止这种类型的攻击，应用可以在展现表单的时候生成一个 CSRF Token，并放到隐藏域中，然后将其临时存储起来，以便后续在服务器上使用。在提交表单的时候，token 将和其他的表单数据一起发送至服务端。请求会被服务端拦截，并与最初生成的 token 进行对比。如果 token 匹配，那么请求将会允许处理；否则，表单肯定是恶意网站渲染的，因为它不知道服务器所生成的 token。

​	Spring Security 提供了内置的 CSRF 保护，默认是启用的。要保证应用的每个表单都有一个名为 **"_csrf"** 字段，它会持有 token。

​	在 Thymeleaf 模板中，可以在模板表单中嵌入以下隐藏域：

```html
<input type="hidden" name="_csrf" th:value="${_csrf.token}"/>
```

​	在 Thymeleaf 中，我们只需要确保 <form> 的某个属性带有 Thymeleaf 属性前缀即可。例如，为了让Thymeleaf 渲染隐藏域，只需要使用 th:action 属性即可。

​	访问该页面时，可以看到被自动填充了一个 token。

<img src="screenshot\28-csrf.png" style="zoom:70%;" />

​	还可以禁用 Spring Security 对 CSRF 的支持，但是一般情况下该支持可以非常好地防护表单提交的安全，要禁用通过 `disable()` 来实现。

```java
http.csrf().disable();
```





## 4、获取当前用户

​	有多种方式确定用户是谁，常用的方式如下：

* 注入 **Principal** 对象到控制器方法中；

* 注入 **Authentication** 对象到控制器方法中；

* 使用 **SecurityContextHolder** 来获取安全上下文；

  ```java
  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  User user = (User) authentication.getPrincipal();
  ```

* 使用 **@AuthenticationPrincipal** 注解来标注方法。

  OrderController 的 `processOrder()` 方法参数添加一个 `@AuthenticationPrincipal User`

 参数。

```java
/**
 * @AuthenticationPrincipal User user   获取当前会话的用户
 */
@PostMapping
public String processOrder(@Valid Order order, 
						 Errors errors, 
						 SessionStatus sessionStatus,
						 @AuthenticationPrincipal User user) {
	if (errors.hasErrors()) {
		return "jpa-orderForm";
	}
	
	order.setUserInfo(user);
	
	orderRepo.save(order);
	sessionStatus.setComplete();
	
	return "redirect:/jpa-orders/list";
}
```

​	

【演示项目github地址】https://github.com/huyihao/Spring-Tutorial/tree/main/2%E3%80%81SpringBoot/taco-cloud-security







# 五、Spring Boot 自动配置

​	使用 Spring 为 bean 注入属性时，通常都是在 XML 中设置属性，Java 类的中属性要为其添加 setter 方法，这样在启动的时候才可以去调用。

​	比如定义一个 User 类，代码如下：

```java
package spring.domain;

public class User {
    private String name;
  	private int age;
  
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setAge(int age) {
    	this.age = age;
    }
}
```

​	XML 配置如下：

```xml
<bean id="tom" class="spring.domain.User">
    <property name="name" value="Tom"/>	
    <property name="age" value="20"/>	
</bean>
```

​	在没有显式配置的情况下，为 bean 设置属性并不这么容易。

​	而 Spring Bean 提供了配置属性（Configuration Property）的方法。配置属性指为 Spring 上下文中的 Bean 配置属性，属性的数据源可以是 **JVM 系统参数、命令行参数和环境变量**。

## 1、细粒度的自动配置

​	自动配置的神奇之处在于，我们可以不显式配置 bean（包括 XML 和 Java 配置），比如现在要定义一个嵌入式的 H2 数据源，如果不使用 Spring Boot，则需要定义一个 bean：

```java
@Bean
public DataSource dataSource() {
  return new EmbeddedDataSourceBuilder()
    	.setType(H2)
    	.addScript("schema.sql")
    	.addScript("data.sql")
    	.build();
}
```

​	而使用 Spring Boot 时，只要引入 H2 的依赖，应用启动运行时就会自动在 Spring 应用上下文中创建对应的 DataSource bean，并且这个 bean 会自动运行名为 schema.sql 和 data.sql 的脚本。

### （1）Spring 环境属性源

​	Spring 会拉取多个属性源，包括：

* JVM 系统属性；
* 操作系统环境变量；
* 命令行参数；
* 应用属性配置文件。

  Spring 会将这些属性聚合到一个源中，通过这个源注入到 Spring Bean，如下图所示：

<img src="screenshot\29-spring属性源.png" style="zoom:70%;" />

​	Spring Boot 自动配置的 bean 都可以通过 Spring 环境提取的属性进行配置。比如配置应用对外服务端口，可以在 application.properties 中配置：

```properties
server.port=8090
```

​	也可以在 application.yml 中配置：

```yaml
server:
    port: 8090
```

​	如果喜欢在外部配置属性，可以使用命令行参数指定端口：

```shell
$ java -jar springbootapp.jar --server.port=8090
```

​	还可以通过环境变量设置：

```shell
$ export SERVER_PORT=8090
```

​	Spring 会将 `SERVER_PORT` 的环境变量解析为 `server.port` 。



### （2）配置数据源

​	Spring Boot 的自动配置很方便，但是一些属性的值如果跟默认不一致时，我们更希望能够在配置文件中自行配置。

​	下面是 H2 数据库的数据源配置：

```yaml
# 默认的 H2 数据库名是随机生成的，这里指定为taco，默认用户名为sa，密码为空
spring:
  datasource:
    url: jdbc:h2:mem:taco
    username: sa
    password:
    
  # 数据初始化
  sql:
    init:
      mode: always
      platform: h2
      username: sa
      password: 
      schema-locations: classpath*:schema.sql
      data-locations: classpath*:data.sql
      
  # h2-console
  # 	path: 控制台路径
  # 	enabled: 开启Web Console
  #	    settings.web-allow-others: 允许远程访问Web Console
  h2:
    console:
      path: /h2-console
      enabled: true
      settings:
        web-allow-others: true
```

​	如果有配置跟默认的配置不一致，可以修改响应的属性

​	如果要配置 MySQL 的数据源，配置如下：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tacocloud
    username: root
    password: root   
    # MySQL 8.0 用的驱动不一样
    driver-class-name: com.mysql.jdbc.Driver
    
  sql:
    init:
      mode: always
      platform: mysql
      username: root
      password: root
      schema-locations: classpath*:schema.sql
      data-locations: classpath*:data.sql
```

​	使用 MySQL 要引入对应的驱动：

```xml
<dependency>
	<groupId>com.mysql</groupId>
	<artifactId>mysql-connector-j</artifactId>
	<scope>runtime</scope>
</dependency>
```

​	如果在类路径中存在 Tomcat 的 JDBC 连接池，DataSource 将使用该连接池。否则，Spring Boot 将会在类路径下尝试查找并使用如下的连接池实现：

* HikariCP
* Commons DBCP 2

  如果自动配置不能满足需求，可以回到显式配置 DataSource Bean 的模式，这样可以使用任意喜欢的连接池实现。



### （3）配置日志

​	默认情况下，Spring Boot 通过 Logback 配置日志，日志会以 INFO 级别写入控制台中。

​	可以在 `src/main/resources` 目录下定义一个 logback.xml：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	
	<!-- spring boot logback日志输出配置颜色转换器 -->
	<conversionRule conversionWord="clr" converterClass="org.springframework.boot.logging.logback.ColorConverter"/>
	<conversionRule conversionWord="wex" converterClass="org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter"/>
	<conversionRule conversionWord="wEx" converterClass="org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter"/>
	
	<property name="PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS}  %clr(%-5p) %magenta(${PID:-}) --- [  %t] %cyan(%-40.40logger{39}) : %m%n"/>
	
	<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <charset>UTF-8</charset>
            <pattern>${PATTERN}</pattern>
        </encoder>
	</appender>
	
	<root level="INFO">
		<appender-ref ref="STDOUT"/>
	</root>
	
	<logger name="root" level="INFO"/>
	
</configuration>
```

​	appender 表示要将日志打印到哪里，默认是 console，也可以指定写入到对应的日志文件。

​	要设置日志级别，可以设置 **logging.level** 作为前缀的属性。随后设置想要设置日志级别的 logger。假设想要将 root logging 设置为 WARN，将 Sping Security 的日志级别设置为 DEBUG，则可在 appliaction.yml 中添加以下配置：

```yaml
logging:
  level:
    root: INFO
    org.springframework.security: DEBUG
```



### （4）使用特定的属性值

​	除了设置硬编码的属性值，还可以使用 ${} 占位符，表示值来自另外属性。

```yaml
greeting:
  welcome: You are using ${spring.application.name}
```

​	



## 2、创建和使用自定义配置属性

### （1）定义配置属性的持有者

​	定义一个对象，会读取属性配置来注入到成员属性中，

```java
package tacos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "taco.orders")
@Validated
public class OrderProps {

	@Min(value = 5, message = "must be between 5 and 25")
	@Max(value = 25, message = "must be between 5 and 25")
	private int pageSize;
	
}
```

​	OrderProps 使用了 `@Component` 注解被定义为一个 bean，使用 `@ConfigurationProperties` 注解表示为 bean 中那些能够根据 Spring 环境注入值的属性赋值。

​	比如这里的 `pageSize` 就会去找一个 page-size 的属性，因为注解还使用了 `taco.orders` 的前缀，所以要找的属性是 `taco.orders.pageSize` 。



### （2）声明配置属性元数据

​	在 application.yml 中，将鼠标悬停在配置的 Spring 自带某个属性上，会介绍该属性使用的一些信息。

<img src="screenshot\31-metadata.png" style="zoom:70%;" />

​	而如果是自定义的属性，会有警告的提示，鼠标悬停显示该属性未知，并且悬停框里还有可点击创建该属性元数据的按钮。

<img src="screenshot\32-metadata.png" style="zoom:70%;" />

​	点击 "Create metadata for xxx" 的按钮，就会自动在 `src/main/resources/META-INF` 目录下创建一个名为 `additional-spring-configuration-metadata.json` 的元数据描述 json 文件，打开该文件添加属性描述。

<img src="screenshot\33-metadata.png" style="zoom:60%;" />

​	再次打开 application.yml  可以看到不再有报警了，并且悬停显示 json 文件里添加的描述。

<img src="screenshot\34-metadata.png" style="zoom:60%;" />

PS. 如果悬停没有显示 **"Create metadata for xxx"** 的按钮，则需要安装插件，详情看附录B-问题汇总12。





## 3、使用 profile 进行配置

​	在不同的环境中，同个属性的值可能是不一样的，这种情况可以使用 Spring Profile，profile 是一种条件化配置，在运行时根据哪些 profile 处于激活状态，可以使用或忽略不同的 bean、配置类和配置属性。

### （1）定义特定的 profile 属性

> **方式1：定义不同的 profile 文件**

​	文件的名称要遵循如下的约定：**application-{profile名}.yml** 或 **application-{profile名}.properties** ，比如现在配置两个 yml 文件，一个是 application-dev.yml 用于本地开发测试，一个是 application-prod.yml 用于生产环境。

​	application-dev.yml，设置 H2 嵌入内存数据库，spring security 日志级别为 DEBUG。

```yaml
spring:
  sql:
    init:
      mode: always
      platform: h2
      username: sa
      password:
      schema-locations: classpath*:schema-h2.sql
      data-locations: classpath*:data-h2.sql

  datasource:
    url: jdbc:h2:mem:taco
    username: sa
    password:
    
logging:
  level:    
    org.springframework.security: DEBUG    
```

​	application-prod.yml，设置数据源为 mysql 数据库，spring security 日志级别为 WARN。

```yaml
spring:
  sql:
    init:
      mode: always
      platform: mysql
      username: root
      password: root
      schema-locations: classpath*:schema.sql
      #data-locations: classpath*:data.sql

  datasource:
    url: jdbc:mysql://localhost:3306/tacocloud
    username: root
    password: root   
    
logging:
  level:    
    org.springframework.security: WARN    
```



> **方式2：在一个 yml 中定义多个 profile**

```yaml
logging:
  level: 
    tacos: DEBUG
    
---
spring:
  profiles: dev
  
  sql:
    init:
      mode: always
      platform: h2
      username: sa
      password:
      schema-locations: classpath*:schema-h2.sql
      data-locations: classpath*:data-h2.sql

  datasource:
    url: jdbc:h2:mem:taco
    username: sa
    password:
    
logging:
  level:    
    org.springframework.security: DEBUG   
    
---
spring:
  profiles: prod

  sql:
    init:
      mode: always
      platform: mysql
      username: root
      password: root
      schema-locations: classpath*:schema.sql
      #data-locations: classpath*:data.sql

  datasource:
    url: jdbc:mysql://localhost:3306/tacocloud
    username: root
    password: root   
    
logging:
  level:    
    org.springframework.security: WARN   
```

​	在第一个 `---` 上面的属性配置表示适用于所有的 profile，下面的属性表示属于不同的 profile，激活哪个 profile 就用哪些属性配置。如果 profile 的属性设置跟适用于所有 profile 的属性重复，则 profile 里的配置会覆盖优先。



### （2）激活 profile

> **方式1：在 yml 中指定（不建议）**

```yaml
spring:
  profiles:
  	active:
  	- prod
  	- taco
```



> **方式2：设置环境参数**

```shell
export SPRING_PROFILES_ACTIVE=prod
```



> **方式3：命令行参数**

```shell
# -Dspring.profiles.active=prod,taco
java -jar springbootapp.jar --spring.profiles.active=prod,taco
```



### （3）条件化地创建 bean

​	有一些场景，我们希望某些 bean 仅在特定 profile 激活的情况下才需要创建，@Profile 注解可以将某些 bean 设置为仅适用于给定的 profile。

```java
@Bean
@Profile("prod")
public User user() {
  new User("Tom", 20);
}
```

​	上面的配置表示当 prod profile 被激活时就注册一个 User 对象的 bean，也可以设置多个 profile 中任一被激活时注册 bean，如：

```java
@Bean
@Profile({"prod", "qa"})
public User user() {
  new User("Tom", 20);
}
```

​	也可以设置某个 profile 被激活时不注册bean，使用 `!`

```java
@Bean
@Profile("!prod")
public User user() {
  new User("Tom", 20);
}
```

​	同样设置多个 profile 中任一被激活都不注册

```java
@Bean
@Profile({"!prod", "!qa"})
public User user() {
  new User("Tom", 20);
}
```



【演示项目github地址】https://github.com/huyihao/Spring-Tutorial/tree/main/2%E3%80%81SpringBoot/taco-cloud-config





# 六、REST 服务

## 1、关于什么是 Rest API

​	首先介绍API的概念，Application Programming Interface（应用程序接口）是它的全称。简单的理解就是，API是一个接口。那么它是一个怎样的接口呢，现在我们常将它看成一个 HTTP 接口即 HTTP API。

​	因为 HTTP 是一个比较通用的协议，所以不管是前后端交互，公司内部不同群组应用之间的交互，还是不同公司组织结构间的系统联动通信，通常都是通过调用 HTTP 接口来实现。比如简书这个web应用要允许用户查看（query）、创建（create）、编辑更新（update）、删除（delete）文章，就可以通过创建对应的 HTTP API 来实现：

> http://www.jianshu.com/query_article?id=xxx
>
> http:///www.jianshu.com/create_article...
>
> http:///www.jianshu.com/update_article?...
>
> http:///www.jianshu.com/delete_article?...

​	这4个API分别实现了对简书文章的增删查改，可以调用相应的接口来实现相关的功能。但是有个不方便的地方，这种 API 写法有个缺点，就是没有一个统一的风格，比如查询文章也可以写成：

> http://www.jianshu.com/view_article/xxx

​	这样会造成接口调用方必须详细了解 API 才知道接口如何运作调用。

​	而 REST 可以将我们从上述的困惑中解救出来。



**什么是REST？**

有了上面的介绍，你可能也大概有了直观的了解，说白了，**REST是一种风格！**

REST的作用是将我们上面提到的查看（query）、创建（create）、编辑更新（update）、删除（delete）直接映射到HTTP 中已实现的**GET,POST,PUT和DELETE**方法。

这四种方法是比较常用的，HTTP总共包含**八种**方法：

> GET
> POST
> PUT
> DELETE
> OPTIONS
> HEAD
> TRACE
> CONNECT

当我们在浏览器点点点的时候我们通常只用到了GET方法，当我们提交表单，例如注册用户的时候我们就用到了POST方法...

介绍到这里，我们重新将上面的四个接口改写成REST风格：

**查看文章：**

> GET http://www.jianshu.com/article/xxx

**新增文章：**

> POST http://www.jianshu.com/article
>
> Data: content=xxx,author=yyy

**修改文章：**

> PUT http://www.jianshu.com/article
>
> Data: id=xxx,content=zzz

**删除文章：**

> DELETE http://www.jianshu.com/article
>
> Data: id=xxx

​	改动之后API变得统一了，我们只需要改变请求方式就可以完成相关的操作，这样大大简化了我们接口的理解难度，变得易于调用。

**这就是REST风格的意义！**

**HTTP状态码**

REST的另一重要部分就是为既定好请求的类型来响应正确的状态码。如果你对HTTP状态码陌生，以下是一个简易总结。当你请求HTTP时，服务器会响应一个状态码来判断你的请求是否成功，然后客户端应如何继续。以下是四种不同层次的状态码：

- 2xx = Success（成功）
- 3xx = Redirect（重定向）
- 4xx = User error（客户端错误）
- 5xx = Server error（服务器端错误）

我们常见的是200（请求成功）、404（未找到）、401（未授权）、500（服务器错误）...

比如查询数据，查不到数据应该返回一个 404 的状态码而不是 200，更加贴近语义。

**API格式响应**

上面介绍了REST API的写法，响应状态码，剩下就是请求的数据格式以及响应的数据格式。说的通俗点就是，我们用什么格式的参数去请求接口并且我们能得到什么格式的响应结果。

我这里只介绍一种用的最多的格式——JSON格式

目前json已经发展成了一种最常用的数据格式，由于其轻量、易读的优点。

所以我们经常会看到一个请求的header信息中有这样的参数：

> Accept:application/json

这个参数的意思就是接收来自后端的json格式的信息。





## 2、Spring 解析请求参数

​	对不同的方式请求传递进来的参数，需要使用不同的解析方法。

### （1）请求 URL 参数

​	**注意：只支持 GET 请求，POST 会报错 403。**

​	就是参数需要从请求 URL 中解析出来，首先要 @GetMapping 注解里进行参数占位，然后方法参数使用 `@PathVariable` 注解解析获取参数。

​	比如：`/param/1`，获取参数1

```java
@Slf4j
@RestController
@RequestMapping("/param")
public class ParseRequestParamDemoController {

	@GetMapping("/{id}")
	public ResponseEntity<Map> getPathParam(@PathVariable("id") Long id) {
		log.info("id = " + id);		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		return new ResponseEntity<>(map, HttpStatus.OK);	
	}
  
}
```

​	测试效果：

<img src="screenshot\47-urlget.png" style="zoom:60%;" />

​	如果要多个路径参数，比如：`/param/zhangsan/20`

```java
@GetMapping("/{username}/{age}")
public ResponseEntity<Map> getPathParam2(@PathVariable("username") String user, @PathVariable("age") Integer age) {
	log.info("username = " + user + ", age = " + age);
	Map<String, Object> map = new HashMap<>();
	map.put("username", user);
	map.put("age", age);
	return new ResponseEntity<>(map, HttpStatus.OK);				
}
```

​	测试效果：

<img src="screenshot\48-urlget.png" style="zoom:60%;" />



### （2）GET 请求参数

​	第一种类型是（1）中的路径参数，第二种是形如 `/param?username=xxx&age=10` 的请求参数。

>  **方式1：方法参数名跟提交参数一致**

```java
@GetMapping
public ResponseEntity<Map> getRequestParam(Long id) {
	log.info("id = " + id);		
	Map<String, Object> map = new HashMap<>();
	map.put("id", id);
	return new ResponseEntity<>(map, HttpStatus.OK);
}
```

测试：

<img src="screenshot\49-get.png" style="zoom:60%;" />



> **方式2：通过 HttpServletRequest 接收，POST、GET 方式都可以**

```java
@GetMapping
public ResponseEntity<Map> getRequestParam2(HttpServletRequest request) {
	Long id = Long.valueOf(request.getParameter("id"));
	log.info("id = " + id);		
	Map<String, Object> map = new HashMap<>();
	map.put("id", id);
	return new ResponseEntity<>(map, HttpStatus.OK);
}
```



> **方式3：使用 @RequestParam 注解，POST、GET方式都可以**

​	当方法参数名跟上送参数不一致时，需要使用 **@RequestParam** 注解，但是使用该注解的参数若没上送会报错 400。

```java
@GetMapping
public ResponseEntity<Map> getRequestParam3(@RequestParam("id") Long ids) {
	log.info("id = " + ids);		
	Map<String, Object> map = new HashMap<>();
	map.put("id", ids);
	return new ResponseEntity<>(map, HttpStatus.OK);
}	
```



>  **方式4：通过 bean 接收参数**

​	比如用户登录上送用户名和密码 `login?username=xxx&password=yyy`，先创建一个对应的 Java Bean：

```java
public class UserLoginForm {
  
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
	@Override
	public String toString() {
		return "UserLoginForm [username=" + username + ", password=" + password + "]";
	}    
}
```

​	在 Controller 中使用该类：

```java
@GetMapping("/login")
public ResponseEntity<UserLoginForm> login(UserLoginForm loginForm) {
	log.info("loginForm = " + loginForm);
	return new ResponseEntity<>(loginForm, HttpStatus.OK);
}
```

测试：

<img src="screenshot\50-get.png" style="zoom:60%;" />



### （3）POST 请求参数

​	POST 请求通常是上送表单数据，或者Content-Type: 为 application/x-www-form-urlencoded编码的内容，也可以是直接请求 json 数据。

> **方式1：通过 HttpServletRequest 接收，POST、GET 方式都可以**

​	不支持 Content-Type 为 非 form-data 和 x-www-form-urlencoded 类型的请求，比如 application/json，会报错 500。

```java
@PostMapping("/login")
public ResponseEntity<Map> login(HttpServletRequest request) {
	Long id = Long.valueOf(request.getParameter("id"));
	log.info("id = " + id);		
	Map<String, Object> map = new HashMap<>();
	map.put("id", id);
	return new ResponseEntity<>(map, HttpStatus.OK);
}	
```

测试：

<img src="screenshot\51-post.png" style="zoom:60%;" />



<img src="screenshot\52-post.png" style="zoom:60%;" />

<img src="screenshot\53-post.png" style="zoom:60%;" />



> **方式2：使用 @RequestParam 注解，POST、GET方式都可以**

```java
@PostMapping("/login")
public ResponseEntity<Map> login2(@RequestParam("id") Long ids) {
	log.info("id = " + ids);		
	Map<String, Object> map = new HashMap<>();
	map.put("id", ids);
	return new ResponseEntity<>(map, HttpStatus.OK);
}	
```

​	不支持 Content-Type 为 非 form-data 和 x-www-form-urlencoded 类型的请求，比如 application/json，会报错 **400 bad request**。

<img src="screenshot\54-post.png" style="zoom:60%;" />



> **方式3：直接使用 bean 接收参数**

```java
@PostMapping("/login")
public ResponseEntity<UserLoginForm> login3(UserLoginForm loginForm) {
	log.info("loginForm = " + loginForm);
	return new ResponseEntity<>(loginForm, HttpStatus.OK);
}
```

<img src="screenshot\55-post.png" style="zoom:60%;" />



> **方式4：使用 @RequestBody 注解**

​	**@RequestBody** 注解常用来处理 content-type 不是默认的 application/x-www-form-urlcoded 编码的内容，比如说：application/json 或者是 application/xml 等。一般情况下来说常用其来处理 application/json 类型。

```java
@PostMapping("/login")
public ResponseEntity<UserLoginForm> login4(@RequestBody UserLoginForm loginForm) {
	log.info("loginForm = " + loginForm);
	return new ResponseEntity<>(loginForm, HttpStatus.OK);
}	
```

​	使用 JSON 请求参数体

<img src="screenshot\56-post.png" style="zoom:60%;" />

​	使用 form-data 或 x-www-form-urlcoded 请求参数会报错 **415 Unsupported Media Type**。

<img src="screenshot\57-post.png" style="zoom:60%;" />





## 2、创建 Restful 控制器

### （1）检索数据

​	定义一个控制器，用来获取最近创建的 Taco，并且只获取最新的 12 个。

​	为了使得 TacoRepository 具备分页获取并排序的能力，需要创建一个继承 JPA `PagingAndSortingRepository` 的 TacoRepository，代码如下：

```java
package tacos.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import tacos.Taco;

/**
 * 继承了PagingAndSortingRepository就具备了分页获取并排序的能力
 */
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
```

​	PagingAndSortingRepository 实际上依然是 CrudRepository，所以 TacoRepository 依然是一个 CrudRepository。

​	控制器取名为 `TacoApiController`，代码如下：

```java
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

@Slf4j
@RestController							
@RequestMapping(path = "/design", 		
			   produces = "application/json")   
@CrossOrigin(origins = "*")	
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
}
```

* **@RestController**：和使用 `@Controller` 的区别是，该注解会告诉 Spring，控制器中所有处理器方法的返回值都要直接写入响应体中，而不是将值放到模型中并传递给一个视图以便于进行渲染。

  如果使用的是 `@Controller`，则必须为每个处理器方法再添加 `@ResponseBody`注解才能达到一样的效果。

* **@RequestMapping**：`path` 属性表示要响应哪个路径的请求，`produces` 属性表示控制器中的所有处理器方法返回的报文信息头是 `"application/json"`，如果要限制只处理 Accept 头信息包含 `"application/json"` 的请求，添加 `consumes = "application/json"` 即可。

* **@CrossOrigin**：配置 `origins = "*"` 表示允许来自任何域的客户端消费该 API。

* **PageRequest**：`recentTacos()` 方法中先构造了一个 PageRequest 对象，指明想要第一页的 12 条结果，并且要按照 Taco 创建时间降序排列，然后该对象被传递给 Repository 的 `findAll()` 方法，获得的分页结果内容会返回到客户端。



测试效果如下：

<img src="screenshot\35-restapiget.png" style="zoom:50%;" />

​	查看响应报文的 Headers，可以看到 Content-Type 是 `application/json`

<img src="screenshot\36-restapiget.png" style="zoom:50%;" />

​	下面为控制器编写新的方法，我们希望通过请求 `"/design/{id}"` 的 GET 请求获取某条数据，新增控制器响应处理方法如下：

```java
@GetMapping("/{id}")
public Taco tacoById(@PathVariable("id") Long id) {
	Optional<Taco> optTaco = tacoRepo.findById(id);
	if (optTaco.isPresent()) {
		return optTaco.get();
	}
	return null;
}
```

​	路径的 `"{id}"` 部分是占位符，请求中的实际值将会传递给 id 参数，它通过 `@PathVariable` 注解和 {id} 占位符进行匹配。Repository 的 `findById()` 方法返回一个 Optional 类型的对象，如果查到了数据 `isPresent()` 方法会返回 true，并且可以通过 `get()` 方法获得查询到的 Taco 对象，否则返回 null。

<img src="screenshot\37-restapiget.png" style="zoom:50%;" />

​	但是这里有个问题，如果查询的数据不存在，会返回 null，客户端将收到空的响应体和 200（OK）的 HTTP 状态码。客户端实际上收到一个无法使用的响应，但是状态码却提示一切正常。

<img src="screenshot\38-restapiget.png" style="zoom:50%;" />			      有一种更好的方式是在响应中使用 HTTP 404（NOT FOUND）状态，代码如下：

```java
@GetMapping("/{id}")
public ResponseEntity<Taco> tacoById2(@PathVariable("id") Long id) {
	Optional<Taco> optTaco = tacoRepo.findById(id);
	if (optTaco.isPresent()) {
		log.info("Query taco succ: " + optTaco.get());
		return new ResponseEntity<Taco>(optTaco.get(), HttpStatus.OK);			
	}
	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
}	
```

​	这里返回的是一个 `ResponseEntity` 的对象，查到数据会将其放入 Entity 对象中，并且会带有 **OK** 的 HTTP 状态；如果查不到数据，则包装一个 null 并带有 **NOT FOUND** 的 HTTP 状态。

​	同样测试请求获取不存在的数据，返回响应状态码变了。

<img src="screenshot\39-restapiget.png" style="zoom:50%;" />



### （2）解析 URL 和请求参数



> **方式2：通过 HttpServletRequest 接收，POST、GET 方式都可以**

```java
@GetMapping("/taco")
public ResponseEntity<Taco> tacoById4(HttpServletRequest request) {
	Long id = Long.valueOf(request.getParameter("id"));
	Optional<Taco> optTaco = tacoRepo.findById(id);
	if (optTaco.isPresent()) {
		log.info("Query taco succ: " + optTaco.get());
		return new ResponseEntity<Taco>(optTaco.get(), HttpStatus.OK);			
	}
	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
}	
```

<img src="screenshot\41-restapiget.png" style="zoom:60%;" />

​	验证下接收 POST 参数，修改代码为：

```java
@PostMapping("/taco")
public ResponseEntity<Taco> tacoById4(HttpServletRequest request) {
	System.out.println(request.getParameter("id"));
	Long id = Long.valueOf(request.getParameter("id"));
	Optional<Taco> optTaco = tacoRepo.findById(id);
	if (optTaco.isPresent()) {
		log.info("Query taco succ: " + optTaco.get());
		return new ResponseEntity<Taco>(optTaco.get(), HttpStatus.OK);			
	}
	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
}
```

​	使用以下配置发起请求

<img src="screenshot\43-restapipost.png" style="zoom:100%;" />

​	服务端正常解析接收数据

<img src="screenshot\44-restapipost.png" style="zoom:60%;" />



> **方式3：使用 @RequestParam 注解，POST、GET方式都可以**

​	当方法参数名跟上送参数不一致时，需要使用 **@RequestParam** 注解，但是使用该注解的参数若没上送会报错 400。

```java
@GetMapping("/taco")
public ResponseEntity<Taco> tacoById5(@RequestParam("id") Long ids) {		
	Optional<Taco> optTaco = tacoRepo.findById(ids);
	if (optTaco.isPresent()) {
		log.info("Query taco succ: " + optTaco.get());
		return new ResponseEntity<Taco>(optTaco.get(), HttpStatus.OK);			
	}
	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
}
```

<img src="screenshot\42-restapiget.png" style="zoom:60%;" />

​	使用 POST 方式表单提交，和Content-Type: 为 application/x-www-form-urlencoded编码的内容，@RequestParam 能正常解析，修改注解为：

```java
@PostMapping("/taco")
```

<img src="screenshot\45-restapipost.png" style="zoom:100%;" />

​	服务端正常解析接收数据

<img src="screenshot\46-restapipost.png" style="zoom:60%;" />



### （3）发送数据到服务器

​	在 taco 页面填写表单并发送数据到服务器，代码如下：

```java
@PostMapping(consumes="application/json")
@ResponseStatus(HttpStatus.CREATED)
public Taco postTaco(@RequestBody Taco taco) {
	return tacoRepo.save(taco);
}
```

​	`postTaco()` 方法使用 `@PostMapping` 注解表示只处理 POST 请求，这里没有指定 **path** 属性，所以默认按控制器类级别 `@RequestMapping` 注解设置的 **path** 为准。

​	这里设置了 **consumes** 属性，表示只处理报文头 Content-Type: application/json 的请求，对应的 **produces** 属性用于指定请求输出。

​	方法头参数带有 `@RequestBody` 注解，表示请求应该被转换为一个 Taco 对象并绑定到该参数上。该注解表示要将请求体中的 JSON 绑定到对象，而不是请求参数（表单参数、请求参数）绑定到 Taco 对象上。

​	方法还使用了 `@ResponseStatus(HttpStatus.CREATED)` 注解，正常情况下所有响应的 HTTP 状态码都是200（OK），表示请求是成功的。尽管我们都希望得到 HTTP 200，但是有些时候他的描述性不足。在 POST 请求新增数据的情况下，201（CREATED）的 HTTP 状态更具有描述性，它会告诉客户端，请求不仅成功了，还创建了一个资源。在适当的地方使用该注解将最具描述性和最精确的 HTTP 状态码传递给客户端。



### （4）更新数据

​	POST 请求通常用来创建资源，而 PUT、PATCH 请求通常用来更新资源。

​	尽管 PUT 经常被用来更新资源，但它在语义上其实是 GET 的对立面。GET 请求用来从服务端往客户端传输数据，而 PUT 请求则是从客户端往服务端发送数据。

​	从这个意义上将，**PUT 真正的目的是执行大规模的替换（replacement）操作，而不是更新操作；PATCH 的目的是对资源数据打补丁或局部更新**。

​	比如要更新某个订单的信息，使用 PUT 请求处理，使用 `@PutMapping` 注解：

```java
@PutMapping(path="/{orderId}", consumes="application/json")
public Order putOrder(@RequestBody Order order) {		
	return repo.save(order);	    
}	
```

测试：

<img src="screenshot\59-put.png" style="zoom:60%;" />

<img src="screenshot\58-put.png" style="zoom:60%;" />

​	如果说 PUT 请求所做的是对资源数据的大规模替换，那么 PATCH 请求就是处理局部更新，使用 `@PatchMapping` 注解：

```java
@PatchMapping(path = "/{orderId}", consumes = "application/json")
public Order patchOrder(@PathVariable("orderId") Long orderId, 
						@RequestBody Order patch) {

	Order order = repo.findById(orderId).get();
	if (patch.getDeliveryName() != null) {
		order.setDeliveryName(patch.getDeliveryName());
	}
	if (patch.getDeliveryStreet() != null) {
		order.setDeliveryStreet(patch.getDeliveryStreet());
	}
	if (patch.getDeliveryCity() != null) {
		order.setDeliveryCity(patch.getDeliveryCity());
	}
	if (patch.getDeliveryState() != null) {
		order.setDeliveryState(patch.getDeliveryState());
	}
	if (patch.getDeliveryZip() != null) {
		order.setDeliveryZip(patch.getDeliveryState());
	}
	if (patch.getCcNumber() != null) {
		order.setCcNumber(patch.getCcNumber());
	}
	if (patch.getCcExpiration() != null) {
		order.setCcExpiration(patch.getCcExpiration());
	}
	if (patch.getCcCvv() != null) {
		order.setCcCvv(patch.getCcCvv());
	}
	return repo.save(order);
}
```

​	在语义上 PATCH 表示局部更新，但是实际处理还是要依赖业务代码。

测试：

<img src="screenshot\60-patch.png" style="zoom:60%;" />

<img src="screenshot\61-patch.png" style="zoom:60%;" />



### （5）删除数据

​	使用 DELETE 请求语义上表示删除数据，配套的注解是 `@DeleteMapping` ，示例代码如下：

```java
@DeleteMapping("/{orderId}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteOrder(@PathVariable("orderId") Long orderId) {
	try {
		repo.deleteById(orderId);
	} catch (EmptyResultDataAccessException e) {
		log.error("delete order(" + orderId + ") exception", e);
	}
}	
```

​	首先会从 URL 中接收到订单 id，然后直接根据id删除数据库中的记录，若数据库中不存在该记录会抛出 **EmptyResultDataAccessException** 异常，跟删除成功的结果是一样的，不管如何最终都是数据资源不存在，所以使用 `@ResponseStatus(HttpStatus.NO_CONTENT)` 表示资源不存在。

测试：

<img src="screenshot\63-delete.png" style="zoom:60%;" />

<img src="screenshot\62-delete.png" style="zoom:60%;" />

​	如果程序逻辑处理的不同分支要使用不同的响应码，那方法返回值应该是一个 **ResponseEntity** 对象。





## 3、Spring HATEOAS

​	HATEOAS 的全名是**超媒体作为应用状态引擎（Hypermedia as the Engine of Application State）**，是一种创建自描述 API 的方式。API 返回的资源中会包含相关资源的链接，客户端只需了解最小的 API URL 信息就能导航整个 API。这种方式能够掌握 API 所提供的资源之间的关系，客户端能够基于 API 的 URL 中所发现的关系对它们进行遍历。

​	比如，查询用户最近创建的 taco 列表，使用常规 API 开发的方式，在控制器中创建一个方法：

```java
@GetMapping("/recent")
public List<Taco> getRecentTacos() {
	PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
	List<Taco> tacos = tacoRepo.findAll(page).getContent();
	return tacos;
} 	
```

​	访问 `/taco/recent` ，效果如下：

```
[
    {
        "id": 2,
        "name": "kk's taco",
        "createdAt": "2023-03-10T03:40:04.000+00:00"
    },
    {
        "id": 1,
        "name": "huyihao's taco",
        "createdAt": "2023-03-09T12:24:20.000+00:00"
    }
]
```

​	如果要访问某个 taco 的详情，则在点击对应 taco 时必须编写对应的 JavaScript 代码将 id 属性拼接到 `/taco` URL 上，再发起请求，比如 `/taco/2` 。但是如果 API 发现了变化，硬编码的信息都要跟着修改，而使用 HATEOAS API，返回的响应报文如下：

```
{
    "_embedded": {
        "tacoModels": [
            {
                "taco": {
                    "name": "kk's taco",
                    "createdAt": "2023-03-10T03:40:04.000+00:00"
                },
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/taco/2"
                    }
                }
            },
            {
                "taco": {
                    "name": "huyihao's taco",
                    "createdAt": "2023-03-09T12:24:20.000+00:00"
                },
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/taco/1"
                    }
                }
            }
        ]
    }
}
```

​	这样无需硬编码访问 URL，即使 API 发生变化也不需要修改 JavaScript 代码。

​	Spring HATEOAS 项目是一个 API 库，我们可以使用它轻松创建遵循 HATEOAS（超文本作为应用程序状态引擎）原则的 REST 表示。

​	**一般来说，该原则意味着 API 应通过返回有关后续潜在步骤的相关信息以及每个响应来指导客户端完成应用程序。**将客户端和服务器解耦，理论上允许 API 在不破坏客户端的情况下更改其 URI 方案。

### （1）准备

​	引入依赖，使用 Spring Boot 时引入对应的 starter 即可（需要指定 spring-boot-starter-parent 为父工程）：

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-hateoas</artifactId>
</dependency>
```



### （2）添加 HATEOAS 支持

​	在Spring HATEOAS项目中，我们既不需要查找Servlet上下文，也不需要将路径变量连接到基本URI。

​	相反，**Spring HATEOAS 提供了三个用于创建 URI 的抽象 – RepresentationModel、Link 和 WebMvcLinkBuilder**。我们可以使用这些来创建元数据并将其与资源表示相关联。

#### Ⅰ、向资源添加超媒体支持

​	Spring Hateoas 提供了一个名为 *RepresentationModel* 的基类，以便在创建资源表示时继承：

```java
package tacos.web.api;

import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import tacos.Taco;

public class TacoModel extends RepresentationModel<TacoModel> {

	private final Taco taco;

	/**
	 * 参数加上@JsonProperty("content")会有以下效果:
	 * 
	 *   "content" : {
     *	 	"name" : "huyihao's taco",
     *		"createdAt" : "2023-03-09T12:24:20.000+00:00"
     *	 }
     *
     * 默认效果等同于@JsonProperty("taco")
	 *   "taco" : {
     *	 	"name" : "huyihao's taco",
     *		"createdAt" : "2023-03-09T12:24:20.000+00:00"
     *	 }     
	 */
	public TacoModel(Taco taco) {		
		this.taco = taco;
	}

	public Taco getTaco() {
		return taco;
	}
	
}
```

​	**客户资源从 RepresentationModel 类扩展为继承 add（） 方法**。因此，一旦我们创建了一个链接，我们就可以轻松地将该值设置为资源表示形式，而无需向其添加任何新字段。



#### Ⅱ、创建链接

​	**Spring HATEOAS 提供了一个链接对象来存储元数据（资源的位置或 URI）。**

​	首先，我们将手动创建一个简单的链接：

```java
Link link = new Link("http://localhost:8080/taco/1");
tacoModel.add(link);    // 控制器方法直接返回 TacoModel 对象
```

​	Link 对象遵循 *Atom* 链接语法，由标识与资源关系的 *rel* 和 *href* 属性（即实际链接本身）组成。

​	下面是客户资源现在包含新链接的外观：

```json
{
	"taco": {
		"name": "kk's taco",
		"createdAt": "2023-03-10T03:40:04.000+00:00"
	},
	"_links": {
		"self": {
			"href": "http://localhost:8080/taco/1"
		}
	}
}
```

​	与响应关联的 URI 被限定为*自*链接。*自我*关系的语义很清楚——它只是可以访问资源的规范位置。



#### Ⅲ、创建更好的链接

​	该库提供的另一个非常重要的抽象是**WebMvcLinkBuilder - 它**通过避免硬编码链接来简化URI的构建。

​	以下代码片段演示如何使用 *WebMvcLinkBuilder* 类构建客户自链接：

```java
Link link = WebMvcLinkBuilder.linkTo(RecentTacosController.class)
						   .slash(taco.getId())
						   .withRel("self");
```

一起来看看：

- *linkTo（）* 方法检查控制器类并获取其根映射
- *slash（）* 方法将 *tacoId* 值添加为链接的路径变量
- 最后，*withSelfMethod（）* 将关系限定为自链接


代码如下：

```java
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
```

测试效果：

<img src="screenshot\64-hateoasapi.png" style="zoom:60%;" />

​	`linkTo(RecentTacosController.class)` 的效果体现在，href 超链接的前缀是 `http://localhost:8080/taco` ，`.slash(tacoModel.getTaco().getId())` 的效果体现在超链接的后缀是 `1` ，`.withRel("self")` 的效果体现在表示超链接的 json 的属性是 `self` 。

​	如果想要去除多余的 `taco` 属性，希望返回的报文是下面这样的：

```json
{
    "id": 1,
    "name": "huyihao's taco",
    "createdAt": "2023-03-09T12:24:20.000+00:00",
    "_links": {
        "self": {
            "href": "http://localhost:8080/taco/1"
        }
    }
}
```

​	首先要重新定义一个继承 *RepresentationModel* 的 Taco 类：

```java
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
```

​	控制器定义一个映射路径不冲突的新方法：

```java
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
```

​	测试：

<img src="screenshot\65-hateoasapi.png" style="zoom:60%;" />



#### Ⅳ、获取资源列表

​	现在我们希望创建一个返回资源列表的 HATEOAS 的 API，返回的报文中带有每个 taco 的访问链接，形式如下所示：

```json
{
    "_embedded": {
        "tacoes": [
            {
                "id": 2,
                "name": "kk's taco",
                "createdAt": "2023-03-10T03:40:04.000+00:00",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/taco/2"
                    }
                }
            },
            {
                "id": 1,
                "name": "huyihao's taco",
                "createdAt": "2023-03-09T12:24:20.000+00:00",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/taco/1"
                    }
                }
            }
        ]
    }
}
```

​	新增控制器方法：

```java
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
```

​	测试：

<img src="screenshot\66-hateoasapi.png" style="zoom:60%;" />



###（3）嵌套的关系

​	每个 taco 都有对应的配料信息，我们希望创建一个查询 taco 的 HATEOAS API，返回配料的信息和访问配料的连接，报文如下：

```java
{
    "id": 1,
    "name": "huyihao's taco",
    "createdAt": "2023-03-09T12:24:20.000+00:00",
    "_links": {
        "allIngredients": [
            {
                "href": "http://localhost:8080/ingredients/COTO"
            },
            {
                "href": "http://localhost:8080/ingredients/FLTO"
            }
        ],
        "self": {
            "href": "http://localhost:8080/taco/1"
        }
    }
}
```

​	控制器新增方法：

```java
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
```

​	测试：

<img src="screenshot\67-hateoasapi.png" style="zoom:60%;" />

​	



## 4、启用后端数据服务

### （1）自动创建的 REST API

​	Spring Data 有一种特殊的魔法，它能够基于我们定义的接口自动创建 repository 实现，还能**帮助我们定义应用的 API**。

​	Spring Data REST 是 Spring Data 家族中的另一个成员，它会为 Spring Data 创建的 repository 自动生成 REST API。只需将 Spring Data REST 添加到构建文件中，就能得到一套 API，操作和定义 repository 接口是一致的。

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
```

​	添加完依赖后，不需要做什么其他事情，应用的自动配置功能会为 Spring Data 创建的所有 repository 自动创建 RESTAPI。

​	为了统一自动生成的 API 的路径，也为了避免与所编写的控制器发生冲突，在配置文件中添加以下配置：

```properties
spring.data.rest.base-path=/api
```

​	访问 `http://localhost/api` 即可获取所有可访问的 API 的端点链接：

<img src="screenshot\68-hateoasapi.png" style="zoom:60%;" />

​	访问 ingredient 端点

<img src="screenshot\69-restapi.png" style="zoom:60%;" />

​	API 端点中，每个实体的后缀都是单词复数，如果是元音字母，复数为es，比如 taco 本来的链接前缀是 `http://localhost:8080/api/tacoes` ，如果想统一复数为s，则为其添加类注解：

```java
...
@RestResource(rel = "tacos", path = "tacos")
...
public class Taco {

}
```

​	@RestResource 注解能够为实体提供任何我们想要的关系名和路径。在本例中，我们将它们都设置成 "tacos"。



### （2）分页和排序

​	为了在查询 taco 时有分页和排序的能力，定义 TacoRepository 时继承了 *PagingAndSortingRepository* ，如下：

```java
package tacos.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import tacos.Taco;

/**
 * 继承了PagingAndSortingRepository就具备了分页获取并排序的能力
 */
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
```

​	所以自动生成的 REST API 如下：

```http
http://localhost:8080/api/tacos{?page,size,sort}
```

​	**size** 表示每页数据的数量，**page** 表示数据页序号（从0开始），**sort** 表示数据按照什么排序。

​	如果想获取第一页 taco，包含5个条目，可以 GET 请求：

```http
http://localhost:8080/api/tacos?size=5
```

​	如果想获取第二页的 taco，可以 GET 请求：

```http
http://localhost:8080/api/tacos?size=5&page=1
```

​	HATEOAS 返回的报文中分为3部分：

* 第一部分 **"_embedded"** 是被查询的数据本身。
* 第二部分 **"_links"** 返回第一页、下一页、上一页和最后一页的链接。
* 第三部分 **"page"** 返回数据总数、总数据页数等信息。

<img src="screenshot\70-restapi.png" style="zoom:60%;" />

​	如果要对查询出来的数据进行排序，比如按照创建日期字段降序，则请求的 URL :

```http
http://localhost:8080/api/tacos?size=2&sort=createdAt,desc
```

<img src="screenshot\71-restapi.png" style="zoom:60%;" />

​	在比如根据名字进行升序，则请求的 URL 为：

```http
http://localhost:8080/api/tacos?size=2&sort=name,acs
```

<img src="screenshot\72-restapi.png" style="zoom:60%;" />



### （3）添加自定义的端点

​	有时候自动生成的 Spring Data REST API 不能满足需求，需要自行定义对应的控制器和方法，为了保持跟 Spring Data REST 生成端点的 API 保持一致，可以在控制器上的映射路径注解加上 `/api` ，如：

```java
@RequestMapping("/api/taco")
```

​	但是这样硬编码了，如果 `spring.data.rest.base-path` 参数的设置有变化，就意味着该控制器硬编码要重新修改以保持一致，更好的方案是使用 `@RepositoryRestController` 注解，所有映射将会具有和 spring.data.rest.base-path 属性一样的前缀。

> **注意：使用了 `@RepositoryRestController` 注解，就不能在控制器上使用 `@RequestMapping` 注解**

​	比如新增一个自定义端点，根据 id 查询 taco：

```java
@RepositoryRestController
public class RecentTacosController {
    // other code
	@Value("${spring.data.rest.base-path}")
	private String apiBasePath;
	
	@GetMapping(path = "/tacos/{tacoId}", produces = "application/hal+json")
	public ResponseEntity<tacos.web.api.Taco> getTacoById3(@PathVariable Long tacoId) {
		Optional<Taco> taco = tacoRepo.findById(tacoId);
		tacos.web.api.Taco tacoModel = new tacos.web.api.Taco(taco.get());
	    Link link = WebMvcLinkBuilder.linkTo(RecentTacosController.class)	    							 
	            					 .slash(apiBasePath + "/tacos/" + tacoId)
	            					 .withRel("self");
	    tacoModel.add(link);
	    return new ResponseEntity<>(tacoModel, HttpStatus.OK);
	}
}
```

​	测试：

<img src="screenshot\73-restapi.png" style="zoom:60%;" />

​	生成链接时，除了可以使用 `slash()` 指定，也可以调用其他控制器方法生成。

```java
@GetMapping(path = "/tacos/recent", produces = "application/hal+json")
public ResponseEntity<CollectionModel<tacos.web.api.Taco>> getRecentTacos3() {
	PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
	List<Taco> tacos = tacoRepo.findAll(page).getContent();
	List<tacos.web.api.Taco> tacosList = new ArrayList<>();
	for (Taco taco : tacos) {
//		    Link link = WebMvcLinkBuilder.linkTo(RecentTacosController.class)
//										 .slash(taco.getId())
//										 .withRel("self");
		Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RecentTacosController.class).getTacoById3(taco.getId())).withRel("self");		    
		tacos.web.api.Taco apiTaco = new tacos.web.api.Taco(taco);
		apiTaco.add(link);
		tacosList.add(apiTaco);
	}
	return new ResponseEntity<>(CollectionModel.of(tacosList), HttpStatus.OK);
}
```

​	测试：

<img src="screenshot\74-restapi.png" style="zoom:60%;" />

​	这里还有点小问题囧。。。明明调用 `getTacoById3()` 返回的超链接是有带 api 前缀的，但是在 中通过 `methodOn()`  反射调用得到的链接反而没有，知道的大佬指导一下o(╥﹏╥)o。





## 5、Spring RestTemplate

​	客户端与 Rest 资源交互涉及很多工作，通常都是单调的模板代码。如果使用较低层级的 HTTP 库，客户端就需要创建一个客户端实例和请求对象、执行请求、解析响应、将响应映射为领域对象，并且还要处理这个过程中可能会抛出的所有异常。

​	Spring 提供了 RestTemplate 来避免样板代码，就像 JdbcTemplate 一样。

### （1）用法

​	RestTemplate 拥有很多操作方法，主要分为以下几种形式：
| 形式                    | 含义                                       |
| --------------------- | ---------------------------------------- |
| **...ForEntity()**    | 发送一个 GET 或 POST 请求，返回的 ResponseEntity 包含了由响应体所映射成的对象，有 **getForEntity()、postForEntity()** |
| **...ForObject()**    | 发送一个 GET、POST 或 PATCH 请求，返回的响应体将映射为一个对象，支持方法有：**getForObject()、postForObject()、patchForObject()** |
| **postForLocation()** | POST 数据到一个 URL，返回一个新创建资源的 URL            |
| **put()**             | PUT 资源到特定的 URL，主要用于更新数据                  |
| **execute()**         | 在 URL 上执行特定的HTTP方法，返回一个从响应体映射得到的对象       |
| **exchange()**        | 在 URL 上执行特定的HTTP方法，返回一个包含了由响应体所映射成的ResponseEntity 对象 |



### （2）获取资源

#### Ⅰ、获取 POJO

```java
RestTemplate rest = new RestTemplate();

// 1、使用URL占位变量，并指定 POJO 类型和传参
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

// 2、使用URL占位变量，并指定 POJO 类型和传入Map参数
public Ingredient getIngredientById2(String ingredientId) {
	Map<String, String> urlVariables = new HashMap<>();
	urlVariables.put("id", ingredientId);
	return rest.getForObject("http://localhost:8080/api/ingredients/{id}",
							 Ingredient.class, 
							 urlVariables);
}	

// 3、使用 URI 参数
public Ingredient getIngredientById3(String ingredientId) {
	Map<String, String> urlVariables = new HashMap<>();
	urlVariables.put("id", ingredientId);
	URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/ingredients/{id}")
							    .build(urlVariables);
	return rest.getForObject(url, Ingredient.class);
}

// 4、获取ResponseEntity对象，得到响应细节，并提取POJO
public Ingredient getIngredientById4(String ingredientId) {
	ResponseEntity<Ingredient> responseEntity = 
      		rest.getForEntity("http://localhost:8080/api/ingredients/{id}",
							Ingredient.class, 
							ingredientId);			
	log.info("Fetched time: " + responseEntity.getHeaders().getDate());
	log.info("Response header: " + responseEntity.getHeaders());
	log.info("Response status: " + responseEntity.getStatusCode());	
	return responseEntity.getBody();
}	

// 5、使用 URI 参数并获取 ResponseEntity
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

// 6、查询获取数据列表
public List<Ingredient> getAllIngredients() {
	return rest.exchange("http://localhost:8080/ingredients", 
						HttpMethod.GET, 
						null,
						new ParameterizedTypeReference<List<Ingredient>>() {})
      		  .getBody();
}
```



#### Ⅱ、获取 JSON

```java
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response
  = restTemplate.getForEntity("http://localhost:8080/ingredients/{id}", 
                              String.class,
                              ingredientId);
String json = response.getBody();
```



#### Ⅲ、获取 Header

```java
public void retrieveHeaders() {
	HttpHeaders httpHeaders = rest.headForHeaders("http://localhost:8080/api/ingredients");
	log.info("Headers : " + httpHeaders);
}
```

测试：

```tex
Headers : [Vary:"Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", Link:"<http://localhost:8080/api/ingredients>;rel="self",<http://localhost:8080/api/profile/ingredients>;rel="profile"", X-Content-Type-Options:"nosniff", X-XSS-Protection:"1; mode=block", Cache-Control:"no-cache, no-store, max-age=0, must-revalidate", Pragma:"no-cache", Expires:"0", X-Frame-Options:"SAMEORIGIN", Date:"Wed, 26 Apr 2023 08:03:22 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]
```



### （3）更新资源

```java
RestTemplate rest = new RestTemplate();
// 1、URL 使用占位变量，传入参数
public void updateIngredient(Ingredient ingredient) {		
	rest.put("http://localhost:8080/api/ingredients/{id}", 
			 ingredient, 
			 ingredient.getId());		
}

// 2、URL 使用占位变量，传入Map参数
public void updateIngredient2(Ingredient ingredient) {
	Map<String, String> urlVariables = new HashMap<>();
	urlVariables.put("id", ingredient.getId());
	rest.put("http://localhost:8080/api/ingredients/{id}", 
			 ingredient, 
			 urlVariables);
}	

// 3、使用exchange()，指定请求方法为 POST
public void updateIngredient3(Ingredient ingredient) {   
	HttpEntity<Ingredient> entity = new HttpEntity<Ingredient>(ingredient);
	rest.exchange("http://localhost:8080/api/ingredients/{id}", 
				  HttpMethod.PUT, 
				  entity, 
				  Ingredient.class,
				  ingredient.getId());
}		
```



### （4）创建资源

```java
RestTemplate rest = new RestTemplate();

// 1、使用 postForObject()
public Ingredient createIngredient(Ingredient ingredient) {
	return rest.postForObject("http://localhost:8080/api/ingredients", 
							  ingredient, 
							  Ingredient.class);
}

// 2、使用 postForLocation()
// 返回的URI是从响应的Location头信息派生出来的
public URI createIngredient2(Ingredient ingredient) {
	return rest.postForLocation("http://localhost:8080/api/ingredients", 
							  ingredient, 
							  Ingredient.class);
}	

// 3、使用 postForEntity()
public Ingredient createIngredient3(Ingredient ingredient) {
	ResponseEntity<Ingredient> responseEntity = 
      		rest.postForEntity("http://localhost:8080/api/ingredients",
			   			      ingredient, 
							 Ingredient.class);		
	log.info("New resource created at " + responseEntity.getHeaders().getLocation());		
	return responseEntity.getBody();
}
```



### （5）删除资源

```java
public void deleteIngredient(Ingredient ingredient) {
	rest.delete("http://localhost:8080/api/ingredients/{id}", 
				ingredient.getId());
}
```



### （6）提交表单数据

​	**首先，我们需要将 Content-Type 标头设置为 application/x-www-form-urlencoded。** 这可确保可以将大型查询字符串发送到服务器，其中包含由 & 分隔的键值对。

```java
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

// 将表单变量包装到 MultiValueMap 中
MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
map.add("id", "1");

// 构建 HttpEntity 请求实例
HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

// 通过在端点上调用 restTemplate.postForEntity（） 来连接到 REST 服务：/foos/form
ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl+"/form", 
                                                             request , 
                                                             String.class);
Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
```



【演示项目github地址】https://github.com/huyihao/Spring-Tutorial/tree/main/2%E3%80%81SpringBoot/taco-cloud-rest







# 七、异步消息处理

## 1、使用 JMS

### （1）搭建 Active Artemis 环境

#### Ⅰ、安装

​	安装包下载（找到与自己使用的客户端匹配的版本）：[ActiveMQ (apache.org)](https://activemq.apache.org/components/artemis/download/past_releases)

​	将 apache-artemis-xxx-bin.zip 解压到安装目录，设置环境变量 `ARTEMIS_HOME` ，值为解压目录。

<img src="screenshot\78-artemis.png" style="zoom:60%;" />

​	添加执行目录到 PATH 环境参数中

<img src="screenshot\79-artemis.png" style="zoom:60%;" />

​	

#### Ⅱ、创建 broker 实例

​	使用以下命令创建 broker（并设定用户密码）：

```shell
artemis create brokername
```

<img src="screenshot\80-artemis.png" style="zoom:60%;" />

​	在当前命令行目录下会创建一个对应的目录

<img src="screenshot\81-artemis.png" style="zoom:60%;" />



#### Ⅲ、启停 broker

​	切换到生成的 broker 目录下的 bin 子目录，执行以下命令即可启动该 broker：

```shell
artemis run
```

<img src="screenshot\82-artemis.png" style="zoom:60%;" />

​	启动后访问控制台 

```http
http://localhost:8161/console
```

<img src="screenshot\83-artemis.png" style="zoom:60%;" />

​	使用一开始创建 broker 时设定的账号密码登录，登入后的控制台菜单和页面如下

<img src="screenshot\84-artemis.png" style="zoom:60%;" />

​	停止 broker

```shell
artemis stop
```

<img src="screenshot\85-artemis.png" style="zoom:60%;" />



#### Ⅳ、发送接收消息

​	在`${ARTEMIS_HOME}\examples`目录下，自带了非常多的使用示例，可以选取其中的示例来验证发送消息、接收消息功能。

​	比如，选取了“examples\features\standard\queue”这个例子，在Broker实例启动的情况下，执行以下命令来运行示例

```shell
mvn -PnoServer verify
```

​	或者在没有Broker实例启动的情况下，执行以下命令来运行示例

```shell
mvn verify
```

​	运行完成之后，可以看到控制台输出内容如下：

<img src="screenshot\86-artemis.png" style="zoom:60%;" />

​	可以看到示例程序已经能够成功发送和接收到消息“This is a text message”了。



### （2）JmsTemplate

​	首先要引入 JMS Starter 依赖

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-artemis</artifactId>
</dependency>
```

​	引入该依赖后，SpringBoot 会在启动时自动配置一个 JmsTemplate，可以将它注入到程序中，并用它来发送和接收消息。

​	JmsTemplate 是 Spring JMS 集成支持的核心。与 Spring 的其他面向模板的组件非常相似，JmsTemplate 消除了大量与 JMS 协同工作所需的样板代码。如果没有 JmsTemplate，将需要编写代码来创建与消息代理的连接和会话，并编写更多代码来处理在发送消息过程中可能抛出的任何异常。JmsTemplate 专注于真正想做的事情：发送消息。

​	

#### Ⅰ、发送消息

​	JmsTemplate 有几个发送消息的有用方法，包括：JmsTemplate 有几个发送消息的有用方法，包括：

```java
// 发送原始消息
void send(MessageCreator messageCreator) throws JmsException;
void send(Destination destination, MessageCreator messageCreator) throws JmsException;
void send(String destinationName, MessageCreator messageCreator) throws JmsException;
// 发送转换自对象的消息
void convertAndSend(Object message) throws JmsException;
void convertAndSend(Destination destination, Object message) throws JmsException;
void convertAndSend(String destinationName, Object message) throws JmsException;
// 发送经过处理后从对象转换而来的消息
void convertAndSend(Object message, MessagePostProcessor postProcessor) throws JmsException;
void convertAndSend(Destination destination, Object message, MessagePostProcessor postProcessor) throws JmsException;
void convertAndSend(String destinationName, Object message, MessagePostProcessor postProcessor) throws JmsException;
```

​	实际上只有两个方法，send() 和 convertAndSend()，每个方法都被重载以支持不同的参数。如果仔细观察，会发现 convertAndSend() 的各种形式可以分为两个子类。在试图理解所有这些方法的作用时，请考虑以下细分：

- send() 方法需要一个 MessageCreator 来制造一个 Message 对象。
- convertAndSend() 方法接受一个 Object，并在后台自动将该 Object 转换为一条 Message。
- 三种 convertAndSend() 方法会自动将一个 Object 转换成一条 Message，但也会接受一个 MessagePostProcessor，以便在 Message 发送前对其进行定制。

此外，这三个方法类别中的每一个都由三个重载的方法组成，它们是通过指定 JMS 目的地（队列或主题）的方式来区分的：

- 一个方法不接受目的地参数，并将消息发送到默认目的地。

- 一个方法接受指定消息目的地的目标对象。

- 一个方法接受一个 String，该 String 通过名称指定消息的目的地。

  ​

  ​使用 send() 方法发送消息：

```java
private JmsTemplate jms;

private void useSend(Order order) {
	// 写法1：使用MessageCreator的匿名内部类写法
	/*jms.send(new MessageCreator() {			
		@Override
		public Message createMessage(Session session) throws JMSException {
			return session.createObjectMessage(order);
		}
	});*/
	
	// 写法2：函数式接口匿名内部类的简化写法，lambda表达式，默认为 spring.jms.template.default-destination 参数设置的队列
	jms.send(session -> session.createObjectMessage(order));
	
	// 写法3：指定队列（使用Destination、直接使用字符串）
	jms.send(orderQueue,
			 session -> session.createObjectMessage(order));
	jms.send("tacocloud.order.queue",
			 session -> session.createObjectMessage(order));	
	
	jms.send("tacocloud.order.queue", new MessageCreator() {			
		@Override
		public Message createMessage(Session session) throws JMSException {
			Message message = session.createObjectMessage(order);
			message.setStringProperty("X_ORDER_SOURCE", "WEB");
			return message;
		}
	});	
	jms.send("tacocloud.order.queue", session -> {
			Message message = session.createObjectMessage(order);
			message.setStringProperty("X_ORDER_SOURCE", "WEB");
			return message;			
	});		
				
}
```

​	使用 useConvertAndSend() 方法发送消息：

```java
private void useConvertAndSend(Order order) {
	jms.convertAndSend(order);
	jms.convertAndSend(orderQueue, order);
	jms.convertAndSend("tacocloud.order.queue", order);			
	// 在发送数据之前修改底层的Message对象
	jms.convertAndSend("tacocloud.order.queue", order, new MessagePostProcessor() {			
		@Override
		public Message postProcessMessage(Message message) throws JMSException {
			message.setStringProperty("X_ORDER_SOURCE", "WEB");
			return message;
		}
	});
	
	// lambda
	jms.convertAndSend("tacocloud.order.queue", order, message -> {
			message.setStringProperty("X_ORDER_SOURCE", "WEB");
			return message;			
	});		
	
	// 方法引用
	jms.convertAndSend("tacocloud.order.queue", order, this::addOrderSource);			
}

private Message addOrderSource(Message message) throws JMSException {
	message.setStringProperty("X_ORDER_SOURCE", "WEB");
	return message;				
}
```

​	编写 Jms 服务

```java
package tacos.messaging;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import tacos.Order;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

	private JmsTemplate jms;
	private Destination orderQueue;
	
	@Autowired
	public JmsOrderMessagingService(JmsTemplate jms, Destination orderQueue) {		
		this.jms = jms;
		this.orderQueue = orderQueue;
	}

	@Override
	public void sendOrder(Order order) {
		jms.convertAndSend("tacocloud.order.queue", order, this::addOrderSource);	
	}
}
```

​	编写使用 Jms 服务的控制器：

```java
package tacos.messaging;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@RestController
@RequestMapping("/jms")
@Slf4j
public class JmsController {
	
	@Autowired
	JmsOrderMessagingService jmsService;

	@GetMapping("/convertAndSend/order")
	public String convertAndSendOrder() {
		log.info("----------------------- SEND ORDER -------------------------");
		Order order = buildOrder();
		log.info("Ready to send order: " + order);
		jmsService.sendOrder(order);
		log.info("Send order success!");
		return "Convert and send order";
	}

	private Order buildOrder() {
		Order order = new Order();
		order.setId(1L);
		order.setUserId(1L);
		order.setDeliveryName("huyihao");
		order.setDeliveryState("广东省");			
		order.setDeliveryCity("广州市");
		order.setDeliveryStreet("荔湾区石围塘街道");
		order.setDeliveryZip("515100");
		order.setCcNumber("6226890243871067");
		order.setCcExpiration("12/23");
		order.setCcCvv("282");
		order.setPlacedAt(new Date());
		
		return order;
	}
}
```

​	发起请求测试：

<img src="screenshot\87-jms.png" style="zoom:70%;" />

<img src="screenshot\89-jms.png" style="zoom:70%;" />

<img src="screenshot\88-jms.png" style="zoom:70%;" />



#### Ⅱ、接收消息

​	在消息消费的时候，可以选择**拉取模式（pull mode）**和**推送模式（push mode）**，前者会在我们的代码中请求消息并一直等待直到消息到达为止，而后者则会在消息可用的时候自动在代码中执行。

​	JmsTemplate 提供了几种接收消息的方法，但它们都使用拉模型。调用其中一个方法来请求消息，线程会发生阻塞，直到消息可用为止（可能是立即可用，也可能需要一段时间）。

​	另一方面，还可以选择使用推模型，在该模型中，定义了一个消息监听器，它在消息可用时被调用。

​	这两个选项都适用于各种用例。**人们普遍认为推模型是最佳选择，因为它不会阻塞线程。但是在某些用例中，如果消息到达得太快，侦听器可能会负担过重。**拉模型允许使用者声明他们已经准备好处理新消息。

​	JmsTemplate 提供多个用于拉模式的方法，包括以下这些：

```java
Message receive() throws JmsException;
Message receive(Destination destination) throws JmsException;
Message receive(String destinationName) throws JmsException;

Object receiveAndConvert() throws JmsException;
Object receiveAndConvert(Destination destination) throws JmsException;
Object receiveAndConvert(String destinationName) throws JmsException;
```

​	可以看到，这 6 个方法是 JmsTemplate 中的 send() 和 convertAndSend() 方法的镜像。receive() 方法接收原始消息，而 receiveAndConvert() 方法使用配置的消息转换器将消息转换为域类型。对于其中的每一个，可以指定 Destination 或包含目的地名称的 String，也可以从缺省目的地获取一条消息。

​	定义一个订单接收接口：

```java
package tacos.messaging;

import javax.jms.JMSException;

import org.springframework.jms.support.converter.MessageConversionException;

import tacos.Order;

public interface OrderReceiver {
	public Order receiveOrder() throws MessageConversionException, JMSException ;
}
```

​	定义一个实现类并注册为Spring服务：

```java
package tacos.messaging;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Service
@Slf4j
public class JmsOrderReceiver implements OrderReceiver {
	private JmsTemplate jms;
	private MessageConverter converter;
	
	public JmsOrderReceiver(JmsTemplate jms, MessageConverter converter) {		
		this.jms = jms;
		this.converter = converter;
	}

	@Override
	public Order receiveOrder() throws MessageConversionException, JMSException {
		Message message = jms.receive("tacocloud.order.queue");
		String source = message.getStringProperty("X_ORDER_SOURCE");
		log.info("This order is from " + source);
		return (Order) converter.fromMessage(message);
	}
}
```

​	在控制器中新增响应请求方法：

```java
@GetMapping("/receiveAndConvert/order")
public Order receiveAndConvertOrder() {
	log.info("----------------------- RECEIVE ORDER -------------------------");
	Order order = null;
	try {
		order = jmsReceiver.receiveOrder();
	} catch (MessageConversionException e) {
		log.error("Convert message exception", e);
		e.printStackTrace();
	} catch (JMSException e) {
		log.error("Receive message exception", e);
		e.printStackTrace();
	}
	log.info("Send order success!");
	return order;
}
```

​	测试：

<img src="screenshot\90-jms.png" style="zoom:60%;" />

<img src="screenshot\91-jms.png" style="zoom:60%;" />

<img src="screenshot\92-jms.png" style="zoom:60%;" />

​	消息监听器是推送模式接收消息，在消息到达之前，它会一直处于空闲状态，消息到达时接收消息处理。

​	要定义一个监听器需要使用 `@JmsListener` 注解，下面定义一个 Spring 组件，并且定义一个使用了该注解的接收订单的方法：

```java
package tacos.messaging;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Component
@Slf4j
public class OrderListener {

	@JmsListener(destination = "tacocloud.order.queue")
	public void receiveOrder(Order order) {
		log.info("----------------------- LISTENER RECEIVE ORDER -------------------------");
		log.info("Reveive a order: " + order);
	}
}
```

​	监听器接收到消息时会打印响应的日志。

​	发一笔对 `/jms/convertAndSend/order` 的请求，以触发消息发送，若监听器生效，会自动接收消息和打印日志。

<img src="screenshot\93-jms.png" style="zoom:60%;" />

​	可以看到监听器马上打印出了相应的日志，证明起效果了。





## 2、使用 RabbitMQ

### （1）AMQP 协议

​	RabbitMQ 使用 AMQP 协议，AMQP协议规定了路由消息必须有三个组成部分：交换器、绑定、队列，如下图所示：

<img src="screenshot\120-rabbitmq.png" style="zoom:40%;" />

​	生产者将消息发布到交换器，交换器根据绑定规则，将消息分发到对应的队列，消息出队后被消费者消费。

​	交换器是一个虚拟的用来接收、投递消息的中间构件。生产者将消息发布到交换器上，并声明绑定的路由规则，然后交换器根据绑定路由规则，将消息投递到满足绑定路由规则的队列上。

​       举个例子，你写信给女朋友Alice，信件上写明要投递到某个地址上面去，然后将信件投递到邮箱，接着邮递员从邮箱中取邮件，根据你填写的地址将信件发到Alice的家中，这里邮箱、快递员、信件上的地址就组成了交换器的效果，邮箱负责接收信件、快递员负责发信件、信件地址是投递信件的规则。

​       当然，绑定规则跟信件地址并不是完全相等的关系，参考上面的“发后即忘”模型图。

​       RabbitMQ中有三种常用的交换器，分别是direct、fanout、topic，还有一种headers交换器，允许匹配AMQP消息的header而非路由键，但其性能差因此几乎不会用到。

> **direct交换器**

​        direct交换器是直接路由键匹配，如图所示：

<img src="screenshot\121-rabbitmq.png" style="zoom:60%;" />

​	RabbitMQ默认实现了一个名字为空字符串的direct交换器，当声明一个队列时，默认会绑定到这个默认的交换器。

<img src="screenshot\122-rabbitmq.png" style="zoom:60%;" />



> **fanout 交换器**

​	fanout交换器会将收到的消息广播到绑定的队列上，比如应用程序中A模块完成之后必须触发B、C、D模块，并且B、C、D之前是并发的没有程序耦合关系，则适合对B、C、D分别声明一条队列来实现与A模块的关联，通过MQ，A模块无需显式地去调用BCD，实现了程序的解耦，这也是MQ存在的意义之一。

<img src="screenshot\123-rabbitmq.png" style="zoom:60%;" />



> **topic 交换器**

​	topic交换器是RabbitMQ中最灵活的交换器，由于队列支持对绑定关系进行模糊匹配，因此能够实现多种复杂的应用场景需求，下面以日志搜集为例，实现对不同日志级别维度的日志进行收集，如图所示：

<img src="screenshot\124-rabbitmq.png" style="zoom:60%;" />



### （2）环境搭建

#### Ⅰ、安装Erlang

​	RabbitMQ服务端代码是使用并发式语言Erlang编写的，安装Rabbit MQ的前提是安装Erlang，下载地址：https://www.erlang.org/downloads

<img src="screenshot\94-rabbitmq.png" style="zoom:60%;" />

​	双击 `otp_win64_25.3.2.exe` 直接安装即可。

<img src="screenshot\95-rabbitmq.png" style="zoom:70%;" />

​	设置环境变量

<img src="screenshot\96-rabbitmq.png" style="zoom:60%;" />

<img src="screenshot\97-rabbitmq.png" style="zoom:60%;" />

​	验证环境

<img src="screenshot\98-rabbitmq.png" style="zoom:70%;" />



#### Ⅱ、安装RabbitMQ

​	下载地址：https://www.rabbitmq.com/install-windows.html，根据使用操作系统下载对应的安装包，windows是对应 `rabbitmq-server-3.11.15.exe` 文件，双击该可执行文件，按照默认配置完成安装即可。

<img src="screenshot\99-rabbitmq.png" style="zoom:70%;" />

<img src="screenshot\100-rabbitmq.png" style="zoom:70%;" />

​	安装Web网页管理插件 RabbitMQ-Plugins，这个相当于是一个管理界面，方便我们在浏览器界面查看RabbitMQ各个消息队列以及exchange的工作情况。

​	程序安装好之后默认情况下服务是开启的，这一点可以打开Windows的服务界面查看。

<img src="screenshot\101-rabbitmq.png" style="zoom:70%;" />

​	打开开始菜单，选择RabbitMQ Server->RabbitMQ Service-stop命令，停止服务。

<img src="screenshot\102-rabbitmq.png" style="zoom:50%;" />

​	此时再次打开Windows的服务管理界面，可以看见RabbitMQ服务已停止。

<img src="screenshot\103-rabbitmq.png" style="zoom:50%;" />

​	打开 CMD 窗口，切换到安装目录下的 sbin 目录下

<img src="screenshot\104-rabbitmq.png" style="zoom:70%;" />

​	输入命令：

```shell
rabbitmq-plugins enable rabbitmq_management
```

​	安装完插件，开启服务，打开菜单，**RabbitMQ Service Start**

<img src="screenshot\105-rabbitmq.png" style="zoom:50%;" />

​	成功开启服务之后，同样打开服务管理界面，查看是否已开启服务

<img src="screenshot\106-rabbitmq.png" style="zoom:60%;" />

​	打开浏览器，访问：

```http
http://localhost:15672/
```

​	进入控制台登录页面，默认账户密码为：guest/guest

<img src="screenshot\107-rabbitmq.png" style="zoom:70%;" />

​	登录成功之后，如下图所示：

<img src="screenshot\108-rabbitmq.png" style="zoom:100%;" />

​	选择Queues页面，打开“Add a new queue”，为队列命名，如“MQ\_Test”，其它选择可以默认，然后点击“Add queue”按钮，

<img src="screenshot\109-rabbitmq.png" style="zoom:60%;" />

​	添加成功之后，在“All queues”选项下面会列出刚才创建的队列信息，

<img src="screenshot\110-rabbitmq.png" style="zoom:60%;" />

​	在“All queues”列表中选择刚刚创建的队列“MQ\_Test”,会显示“MQ\_Test”的信息页面，主要显示了该队列的网络状态以及速率监控等，然后选择“Publish message”，在“Payload”中输入”Hello world!!!”，

<img src="screenshot\111-rabbitmq.png" style="zoom:60%;" />

​	然后点击“Publish message”按钮，就可以发送消息，发送完之后在“Overview”中显示了实时的网络状态。

<img src="screenshot\112-rabbitmq.png" style="zoom:60%;" />

​	然后选择“Get messages”下拉框，会弹出接收消息的显示界面，点击“Get message”按钮，接收到来自服务器的消息，也就是刚刚发送的“Hello world!!!”

<img src="screenshot\113-rabbitmq.png" style="zoom:60%;" />

​	到此，可以说明RabbitMQ的安装均已正常，进一步操作请参考官网的操作文档：[http://www.rabbitmq.com/documentation.html](http://www.rabbitmq.com/documentation.html?spm=a2c6h.12873639.article-detail.8.28b36106naoNVQ)



### （3）配置 Spring Rabbit

​	引入对应的 Starter：

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-amqp</artifactId>			
</dependency>
```

​	将 AMQP starter 添加到构建中将触发自动配置，该配置将创建 AMQP 连接工厂和 RabbitTemplate bean，以及其他支持组件。只需添加此依赖项，就可以开始使用 Spring 从 RabbitMQ broker 发送和接收消息。

​	RabbitMQ 常用属性：
| 属性                        | 描述                           |
| ------------------------- | ---------------------------- |
| spring.rabbitmq.addresses | 一个逗号分隔的 RabbitMQ Broker 地址列表 |
| spring.rabbitmq.host      | Broker 主机（默认为localhost）      |
| spring.rabbitmq.port      | Broker 端口（默认为5672）           |
| spring.rabbitmq.username  | 访问 Broker 的用户名（可选）           |
| spring.rabbitmq.password  | 访问 Broker 的密码（可选）            |

​	在 application.yml 中配置为：

```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
    # 配置默认交换器和路由key
    template:
      exchange: tacocloud.orders
      routing-key: kitchens.central     
```



### （4）RabbitTemplate

​	Spring 对 RabbitMQ 的支持是提供了 RabbitTemplate，类似于 JmsTemplate，工作方式略有差异。

​	关于使用 RabbitTemplate 发送消息，send() 和 convertAndSend() 方法与来自 JmsTemplate 的同名方法并行。但是不同于 JmsTemplate 方法，它只将消息路由到给定的队列或主题，RabbitTemplate 方法根据交换和路由键发送消息。下面是一些用 RabbitTemplate 发送消息的最有用的方法：

```java
// 发送原始消息
void send(Message message) throws AmqpException;
void send(String routingKey, Message message) throws AmqpException;
void send(String exchange, String routingKey, Message message) throws AmqpException;

// 发送从对象转换过来的消息
void convertAndSend(Object message) throws AmqpException;
void convertAndSend(String routingKey, Object message) throws AmqpException;
void convertAndSend(String exchange, String routingKey, Object message) throws AmqpException;

// 发送经过处理后从对象转换过来的消息
void convertAndSend(Object message, MessagePostProcessor mPP) throws AmqpException;
void convertAndSend(String routingKey, Object message, MessagePostProcessor messagePostProcessor) throws AmqpException;
void convertAndSend(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor) throws AmqpException;
```

​	这些方法与 JmsTemplate 中的孪生方法遵循类似的模式。前三个 send() 方法都发送一个原始消息对象。接下来的三个 convertAndSend() 方法接受一个对象，该对象将在发送之前在后台转换为消息。最后三个 convertAndSend() 方法与前三个方法类似，但是它们接受一个 MessagePostProcessor，可以在消息对象发送到代理之前使用它来操作消息对象。

​	这些方法与对应的 JmsTemplate 方法不同，它们接受 String 值来指定交换和路由键，而不是目的地名称（或 Destination 对象)。不接受交换的方法将把它们的消息发送到默认交换。同样，不接受路由键的方法将使用默认路由键路由其消息。



​	使用 RabbitTemplate 发送消息与使用 JmsTemplate 发送消息差别不大。事实证明，从 RabbitMQ 队列接收消息与从 JMS 接收消息并没有太大的不同。

与 JMS 一样，有两个选择：

- 使用 RabbitTemplate 从队列中拉取消息
- 获取被推送到 @RabbitListener 注解的方法中的消息

RabbitTemplate 有多个从队列中拉取消息的方法，一部分最有用的方法如下所示RabbitTemplate 有多个从队列中拉取消息的方法，一部分最有用的方法如下所示：

```java
// 接收消息
Message receive() throws AmqpException;
Message receive(String queueName) throws AmqpException;
Message receive(long timeoutMillis) throws AmqpException;
Message receive(String queueName, long timeoutMillis) throws AmqpException;

// 接收从消息转换过来的对象
Object receiveAndConvert() throws AmqpException;
Object receiveAndConvert(String queueName) throws AmqpException;
Object receiveAndConvert(long timeoutMillis) throws AmqpException;
Object receiveAndConvert(String queueName, long timeoutMillis) throws AmqpException;

// 接收从消息转换过来的类型安全的对象
<T> T receiveAndConvert(ParameterizedTypeReference<T> type) throws AmqpException;
<T> T receiveAndConvert(String queueName, ParameterizedTypeReference<T> type) throws AmqpException;
<T> T receiveAndConvert(long timeoutMillis, ParameterizedTypeReference<T> type) throws AmqpException;
<T> T receiveAndConvert(String queueName, long timeoutMillis, ParameterizedTypeReference<T> type) throws AmqpException;
```

​	这些方法是前面描述的 send() 和 convertAndSend() 方法的镜像。send() 用于发送原始 Message 对象，而 receive() 从队列接收原始 Message 对象。同样地，receiveAndConvert() 接收消息，并在返回消息之前使用消息转换器将其转换为域对象。

​	但是在方法签名方面有一些明显的区别。首先，这些方法都不以交换键或路由键作为参数。这是因为交换和路由键用于将消息路由到队列，但是一旦消息在队列中，它们的下一个目的地就是将消息从队列中取出的使用者。使用应用程序不需要关心交换或路由键，队列是在消费应用程序是仅仅需要知道一个东西。

​	许多方法接受一个 long 参数来表示接收消息的超时。默认情况下，接收超时为 0 毫秒。也就是说，对 receive() 的调用将立即返回，如果没有可用的消息，则可能返回空值。这与 receive() 方法在 JmsTemplate 中的行为有明显的不同。通过传入超时值，可以让 receive() 和 receiveAndConvert() 方法阻塞，直到消息到达或超时过期。但是，即使使用非零超时，代码也要准备好处理返回的 null 值。



#### Ⅰ、发送消息

​	定义一个消息接口：

```java
package tacos.messaging;

import tacos.Order;

public interface OrderMessagingService {
	void sendOrder(Order order);
}
```

​	定义一个实现接口的消息服务：

```java
package tacos.messaging;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.Order;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

	private RabbitTemplate rabbit;
	
	@Autowired
	public RabbitOrderMessagingService(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
	}
	
	@Override
	public void sendOrder(Order order) {
		// 1、显式使用消息转换器
		/*MessageConverter converter = rabbit.getMessageConverter();
		MessageProperties props = new MessageProperties();
		props.setHeader("X_ORDER_SOURCE", "WEB");
		Message message = converter.toMessage(order, props);
		rabbit.send("tacocloud.order", message);*/
		
		// 2、使用convertAndSend屏蔽消息转换细节
		//rabbit.convertAndSend("tacocloud.order", order);		
		
		// 3、使用convertAndSend时要设置消息属性只能通过MessagePostProcessor来处理消息细节
		rabbit.convertAndSend("tacocloud.order", order, new MessagePostProcessor() {		
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				MessageProperties props = message.getMessageProperties();
				props.setHeader("X_ORDER_SOURCE", "WEB");
				return message;
			}
		});
	}
}
```

​	定义使用服务的控制器：

```java
package tacos.messaging;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@RestController
@RequestMapping("/rabbit")
@Slf4j
public class RabbitController {

	@Autowired
	RabbitOrderMessagingService rabbitService;
	
	@GetMapping("/convertAndSend/order")
	public String convertAndSendOrder() {
		log.info("----------------------- SEND ORDER -------------------------");
		Order order = buildOrder();
		log.info("Ready to send order: " + order);
		rabbitService.sendOrder(order);
		log.info("Send order success!");
		return "Convert and send order";
	}
	
	private Order buildOrder() {
		Order order = new Order();
		order.setId(1L);
		order.setUserId(1L);
		order.setDeliveryName("huyihao");
		order.setDeliveryState("广东省");			
		order.setDeliveryCity("广州市");
		order.setDeliveryStreet("荔湾区石围塘街道");
		order.setDeliveryZip("515100");
		order.setCcNumber("6226890243871067");
		order.setCcExpiration("12/23");
		order.setCcCvv("282");
		order.setPlacedAt(new Date());
		
		return order;
	}	
}
```

​	使用 postman 对 `/rabbit/convertAndSend/order` 发起请求，因为未指定 exchange，所以默认是 yml 中 `spring.rabbit.template.exchange ` 属性配置的 **tacocloud.orders** 。

​	当前不存在这个交换器，所以先创建该交换器，类型为 topic

<img src="screenshot\114-rabbitmq.png" style="zoom:60%;" />

​	交换器要有绑定规则绑定到相应的队列，先创建队列 order、tacocloud

<img src="screenshot\115-rabbitmq.png" style="zoom:60%;" />

​	在交换器上设定绑定规则，当 Routing key 与 `*.order` 匹配时消息会发送到 order 队列，与 `tacocloud.*` 匹配时消息会被发送到 tacocloud 队列。

<img src="screenshot\116-rabbitmq.png" style="zoom:60%;" />

​	在程序里设定的 Routing key 是 `tacocloud.order` ，所以发送一条消息会同时被发送到上述两个队列中。

​	发起测试，从 Rabbit 网页控制台上可以看到

<img src="screenshot\117-rabbitmq.png" style="zoom:60%;" />

​	两个队列都收到了消息

<img src="screenshot\118-rabbitmq.png" style="zoom:60%;" />

<img src="screenshot\119-rabbitmq.png" style="zoom:60%;" />



#### Ⅱ、接收消息

​	定义一个接收器：

```java
@Component
public class RabbitOrderReceiver {

	private RabbitTemplate rabbit;
	private MessageConverter converter;
	
	@Autowired
	public RabbitOrderReceiver(RabbitTemplate rabbit, MessageConverter converter) {		
		this.rabbit = rabbit;
		this.converter = converter;
	}
	
	public Order receiveOrder() {
		Message message = rabbit.receive("order");
		Order order = null;
		if (message != null) {
			order = (Order) converter.fromMessage(message);
		} 
		return order;
	}
	
}
```

​	控制器中新增一个处理方法：

```java
@GetMapping("/receiveAndConvert/order")
public Order receiveAndConvertOrder() {
	log.info("----------------------- RECEIVE ORDER -------------------------");
	Order order = rabbitReceiver.receiveOrder();		
	log.info("Receive order success, order = " + order);
	return order;
}
```

​	使用 postman 对 `/receiveAndConvert/order` 发起请求，可以看到接收的消息里有设置的属性

<img src="screenshot\125-rabbitmq.png" style="zoom:50%;" />

<img src="screenshot\126-rabbitmq.png" style="zoom:60%;" />

​	查看 Rabbit Management，Order 队列中的消息数变成0，而 tacocloud 队列中的消息数依然为1。

 <img src="screenshot\127-rabbitmq.png" style="zoom:60%;" />

​	接收队列消息时队列中可能没有消息，有时可能想在没消息时再等一下，`receive()` 、`receiveAndConvert()` 都提供了带超时时间参数的方法，修改代码为如下：

```java
public Order receiveOrder() {
	//Message message = rabbit.receive("order");
	
	// 带超时时间的消息接收
	/*Message message = rabbit.receive("order", 30000);
	Order order = null;
	if (message != null) {
		order = (Order) converter.fromMessage(message);
	}*/
	
    // receiveAndConvert 的另一种写法
	//Order order = (Order) rabbit.receiveAndConvert("order", 30000, new ParameterizedTypeReference<Order>() {});
	
	log.info("Start to receive order!");
	Order order = (Order) rabbit.receiveAndConvert("order", 30000);
	log.info("Receive order end, succ? " + (order != null));
	return order;
}
```

​	发起接收消息的测试，因为 order 队列中没有消息，所以在 30s 超时时间内会被阻塞，此时在发起发送消息的请求，order 队列里就会新增一条消息，马上被阻塞的消息消费方所消费。

<img src="screenshot\128-rabbitmq.png" style="zoom:60%;" />

​	tacocloud 队列因为又一次收到消息并且没被消费，所以消息数量会变为 2，而 order 队列依然为 0。

<img src="screenshot\129-rabbitmq.png" style="zoom:60%;" />

​	有时，我们需要的是有个后台程序一直在监听队列，只要队列里有消息就消费，这种场景可以使用监听器的功能，Spring 提供了 RabbitListener，相当于 RabbitMQ 中的 JmsListener。要指定当消息到达 RabbitMQ 队列时应该调用某个方法，请在相应的 bean 方法上使用 @RabbitTemplate 进行注解 。

​	启动程序，监听器会接收 tacocloud 队列里的 2 条消息。

<img src="screenshot\130-rabbitmq.png" style="zoom:60%;" />

<img src="screenshot\131-rabbitmq.png" style="zoom:60%;" />





## 3、使用 Kafka

### （1）kafka 的介绍和环境安装

​	[高性能分布式消息系统 —— Kafka](https://www.jianshu.com/p/5336aeef2dc3)



### （2）启动环境

<img src="screenshot\132-kafka.png" style="zoom:60%;" />

<img src="screenshot\133-kafka.png" style="zoom:60%;" />

​	查看对应端口进程是否已启动

<img src="screenshot\134-kafka.png" style="zoom:60%;" />



### （3）配置 Spring Kafka

​	Spring 没有针对 kafka 的 Spring Boot Starter，添加以下依赖即可：

```xml
<dependency>
	<groupId>org.springframework.kafka</groupId>
	<artifactId>spring-kafka</artifactId>			
</dependency>
```

​	这个依赖项将 Kafka 所需的一切都带到项目中。更重要的是，它的存在将触发 Kafka 的 Spring Boot 自动配置，它将在 Spring 应用程序上下文中生成一个 KafkaTemplate。你所需要做的就是注入 KafkaTemplate 并开始发送和接收消息。

​	在开始发送和接收消息之前，应该了解一些在使用 Kafka 时会派上用场的属性。具体来说就是，KafkaTemplate 默认在 localhost 上运行 Kafka broker，并监听 9092 端口。在开发应用程序时，在本地启动 Kafka broker 是可以的，但是在进入生产环境时，需要配置不同的主机和端口。

​	在 yml 中配置如下：

```yaml
spring:
  kafka:
    bootstrap-servers:
    - localhost:9092
    template:
      default-topic: tacocloud.orders.topic    
```

​	spring.kafka.bootstrap-servers 属性是复数形式，它接受一个列表。因此，可以在集群中为它提供多个 Kafka 服务器。



### （4）KafkaTemplate

​	在许多方面，KafkaTemplate 与 JMS 和 RabbitMQ 类似。与此同时，它也是不同的，尤其是在我们考虑它发送消息的方法时。

```java
ListenableFuture<SendResult<K, V>> send(String topic, V data);
ListenableFuture<SendResult<K, V>> send(String topic, K key, V data);
ListenableFuture<SendResult<K, V>> send(String topic, Integer partition, K key, V data);
ListenableFuture<SendResult<K, V>> send(String topic, Integer partition, Long timestamp, K key, V data);
ListenableFuture<SendResult<K, V>> send(ProducerRecord<K, V> record);
ListenableFuture<SendResult<K, V>> send(Message<?> message);
ListenableFuture<SendResult<K, V>> sendDefault(V data);
ListenableFuture<SendResult<K, V>> sendDefault(K key, V data);
ListenableFuture<SendResult<K, V>> sendDefault(Integer partition, K key, V data);
ListenableFuture<SendResult<K, V>> sendDefault(Integer partition, Long timestamp, K key, V data);
```

​	注意到的第一件事是没有 convertAndSend() 方法。这是因为 KafkaTemplate 是用的泛型，同时能够在发送消息时直接处理域类型。在某种程度上，所有的 send() 方法都在做 convertAndSend() 的工作。

​	再者 send() 和 sendDefault() 的参数，它们与 JMS 和 Rabbit 中使用的参数完全不同。当使用 Kafka 发送消息时，可以指定以下参数来指导如何发送消息：

* 发送消息的 topic（send() 方法必要的参数）
* 写入 topic 的分区（可选）
* 发送记录的键（可选）
* 时间戳（可选；默认为 System.currentTimeMillis()）
* payload（必须）

  ​topic 和 payload 是两个最重要的参数。分区和键对如何使用 KafkaTemplate 几乎没有影响，除了作为 send() 和 sendDefault() 的参数用于提供额外信息。出于我们的目的，我们将把重点放在将消息有效负载发送到给定主题上，而不考虑分区和键。

  ​对于 send() 方法，还可以选择发送一个 ProducerRecord，它与在单个对象中捕获所有上述参数的类型差不多。也可以发送 Message 对象，但是这样做需要将域对象转换为 Message。通常，使用其他方法比创建和发送 ProducerRecord 或 Message 对象更容易。

#### Ⅰ、发送消息

​	定义一个服务：

```java
package tacos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Service
@Slf4j
public class KafkaOrderMessagingService implements OrderMessagingService {

	private KafkaTemplate<String, Order> kafkaTemplate;
		
	@Autowired
	public KafkaOrderMessagingService(KafkaTemplate<String, Order> kafkaTemplate) {		
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void sendOrder(Order order) {
		log.info("Ready to send message!");
		kafkaTemplate.send("tacocloud.orders.topic", order);
		// 不指定主题使用yml配置的默认主题
		//kafkaTemplate.sendDefault(order);
		log.info("Ready to send message!");
	}

}
```

​	定义一个控制器：

```java
package tacos.messaging;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {

	@Autowired
	private KafkaOrderMessagingService kafkaService;
	
	@GetMapping("/convertAndSend/order")
	public String convertAndSendOrder() {
		log.info("----------------------- SEND ORDER -------------------------");
		Order order = buildOrder();
		log.info("Ready to send order: " + order);
		kafkaService.sendOrder(order);
		log.info("Send order success!");
		return "Convert and send order";
	}
	
	private Order buildOrder() {
		Order order = new Order();
		order.setId(1L);
		order.setUserId(1L);
		order.setDeliveryName("huyihao");
		order.setDeliveryState("广东省");			
		order.setDeliveryCity("广州市");
		order.setDeliveryStreet("荔湾区石围塘街道");
		order.setDeliveryZip("515100");
		order.setCcNumber("6226890243871067");
		order.setCcExpiration("12/23");
		order.setCcCvv("282");
		order.setPlacedAt(new Date());
		
		return order;
	}
}
```

​	使用 postman 对 `/kafka/convertAndSend/order` 发起测试请求，会发生以下报错：

```
org.apache.kafka.common.errors.SerializationException: Can't convert value of class tacos.Order to class org.apache.kafka.common.serialization.StringSerializer specified in value.serializer
```

​	因为 Kafka 默认的键值序列化器都是 StringSerializer，对于自定义的领域对象，需要自定义序列化器：

```java
package tacos.messaging;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.Order;

public class OrderSerializer implements  Serializer<Order> {

	@Override
	public byte[] serialize(String topic, Order data) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString(data).getBytes();
			System.out.println("Using OrderSerializer!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

}
```

​	并且要在配置文件中指定：

```yaml
spring:
    producer:      
      acks: 1
      value-serializer: tacos.messaging.OrderSerializer
```

​	再次发起请求，可以看到 Kafka 自动创建了队列，默认只有一个分区，偏移量是1，证明只有一条数据，即刚才测试发送的消息。

<img src="screenshot\135-kafka.png" style="zoom:60%;" />



#### Ⅱ、接收消息

​	除了 send() 和 sendDefault() 的惟一方法签名之外，KafkaTemplate 与 JmsTemplate 和 RabbitTemplate 的不同之处在于它不提供任何接收消息的方法。这意味着使用 Spring 消费来自 Kafka 主题的消息的唯一方法是编写消息监听器。

​	对于 Kafka，消息监听器被定义为被 @KafkaListener 注解的方法。@KafkaListener 注解大致类似于 @JmsListener 和 @RabbitListener，其使用方式大致相同。

​	编写一个 Kafka 监听器：

```java
package tacos.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Component
@Slf4j
public class OrderListener {

	@KafkaListener(topics = {"tacocloud.orders.topic"})
	public void receiveOrder(Order order) {
		log.info("----------------------- LISTENER RECEIVE ORDER -------------------------");
		log.info("Reveive a order: " + order);
	}
	
}
```

​	接收消息同样要经过反序列化的过程，需要定义一个反序列化器：

```java
package tacos.messaging;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.apache.kafka.common.serialization.Deserializer;

import com.alibaba.fastjson.JSON;

import tacos.Order;

public class OrderDeserializer implements Deserializer<Order> {

	@Override
	public Order deserialize(String topic, byte[] data) {
        if (Objects.isNull(data)) {
            return null;
        }

        String orderStr = new String(data, StandardCharsets.UTF_8);
        return JSON.parseObject(orderStr, Order.class);
	}

}
```

​	同样接收消息需要做一些消费者配置：

```yaml
spring:
    consumer:
      bootstrap-servers:
      - localhost:9092
      auto-offset-reset: latest
      group-id: kafkaListener
      value-deserializer: tacos.messaging.OrderDeserializer
```

​	`autoOffsetReset` 参数配置的值为 latest，表示不消费 topic 里已经存在的消息，因此程序启动后，得先发消息，监听器才会消费消息。

<img src="screenshot\136-kafka.png" style="zoom:60%;" />



【演示项目github地址】https://github.com/huyihao/Spring-Tutorial/tree/main/2%E3%80%81SpringBoot/taco-cloud-message







# 八、Spring Integration







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




## @ConfigurationProperties

​	表示为 bean 中那些能够根据 Spring 环境注入值的属性赋值。

```java
@Component
@ConfigurationProperties(prefix = "taco.orders")
public class OrderProps {
	private int pageSize;
}
```

​	注入属性为 taco.orders.page-size，属性获取的来源可以是应用属性文件（application.properties、application.yml）、JVM参数、命令行参数、系统参数。



## @Profile

​	条件化注册 bean，根据 profile 的激活情况。

```java
@Bean
@Profile("prod")
public User user() {
  new User("Tom", 20);
}
```

​	上面的配置表示当 prod profile 被激活时就注册一个 User 对象的 bean，



## @RequestMapping





## @GetMapping



## @PutMapping



## @PutMapping



## @PatchMapping



## @DeleteMapping







## @ModelAttribute





## @SessionAttributes





## @CrossOrigin





## @PathVariable








# 附录B：使用 SpringBoot 问题汇总

## 1、访问视图网页报错模板引擎解析异常的问题

```xml
<!-- 通过以下依赖项来解决此问题，同时支持旧版 HTML 格式。因为代码 charset="UTF-8"> 这里没有关闭元标记。 -->				
<dependency>
	<groupId>net.sourceforge.nekohtml</groupId>
	<artifactId>nekohtml</artifactId>			                           
</dependency>
```





## 2、JPA使用查无数据问题

​	默认引入H2嵌入数据库会在程序启动时执行资源目录（src/main/resources）下的schema.sql、data.sql，但是引入jpa依赖后，jpa会自动根据@Entity注解标注的POJO的定义生成DDL并建表，覆盖H2默认初始化的行为，为了不被覆盖，需要禁用JPA的自动建表，在配置文件中添加配置：

```properties
# 禁用JPA的自动生成DDL并建表
spring.jpa.hibernate.ddl-auto=none
```





## 3、使用JPA框架ORM映射报错NumberFormatException

​	枚举类型字段，需要使用注解 **@Enumerated(EnumType.STRING)** 声明

```java
@Data							// 自动生成getter、setter
@RequiredArgsConstructor        // 自动生成初始化final成员的构造函数
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Ingredient {
	@Id
	private final String id;
	private final String name;
	@Enumerated(EnumType.STRING)
	private final Type type;
	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	
}
```





## 4、Validation IO @Size 使用问题

​	对于普通类型，数据未上送即会报错，但是对于一个容器类型，比如 List<Ingredient> ，如果请求表单未上送数据，则 **@Size(min = 1)** 是不起作用的，这时候为了完善，需要添加一个 **@NotNull** 的注解配合使用。





## 5、表单提交嵌套的对象的问题

​	需要定义转换器 ，比如 Taco 对象里有 List<Ingredient> 的成员，表单提交的是字符串，对应 Ingredient 对象的 id，这就需要转换器来做转换，将字符串的 id 转换为 Ingredient 对象。

```java
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

	@Autowired
	IngredientRepository repository;	
	
	@Override
	public Ingredient convert(String source) {
		return repository.findOne(source);
	}

}
```





## 6、Spring-JDBC 使用 KeyHolder 的问题

​	新版本的Spring-Jdbc要求显式调用方法设置返回主键，否则 `keyHolder.getKey()` 会报空指针异常。

```java
private long saveTacoInfo(Taco taco) {
	taco.setCreatedAt(new Date());
	PreparedStatementCreatorFactory pcf = new PreparedStatementCreatorFactory(
		"insert into Taco (name, createdAt) values (?, ?)", 
	    Types.VARCHAR, Types.TIMESTAMP
    );
    // 必须加上这行
	pcf.setReturnGeneratedKeys(true);
	PreparedStatementCreator psc =
			pcf.newPreparedStatementCreator(
				Arrays.asList(
					taco.getName(),
					new Timestamp(taco.getCreatedAt().getTime()))
			);
	
	KeyHolder keyHolder = new GeneratedKeyHolder();
	jdbc.update(psc, keyHolder);
	
	return keyHolder.getKey().longValue();
}
```





## 7、H2 schema语法问题

​	外键关联，需要被关联的表中被关联的字段声明为primary key。





## 8、Spring Security页面403问题

​	赋予角色用 `roles(xxx)`，判断用户角色用 `hasRoles(xxx)` 。

```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
	/**
	 * 在内存用户存储中配置两个用户，并且都授予ROLE_USER权限
	 */
	auth.inMemoryAuthentication()
		.passwordEncoder(new MyPasswordEncoder())
		.withUser("buzz").password("infinity").roles("USER")	// 高版本使用roles(xxx)不使用authorities(xxx)
		.and()
		.withUser("woody").password("bullseye").roles("USER");
}
```

​	UserDetails 实现类返回用户授予权限集合时写法：

```java
// 返回用户被授予权限的一个集合
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	// 所有的用户都被授予USER权限，这里要用 ROLE_USER 而不是 USER
	return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
}
```





## 9、使用Spring Security的UserDetailService的各种问题

（1）org.h2.jdbc.JdbcSQLSyntaxErrorException: Sequence "HIBERNATE_SEQUENCE" not found; SQL statement:

call next value for hibernate_sequence [90036-214]

​	修改id字段的生成类型注解：

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

​	AUTO自动选择一个最适合底层数据库的主键生成策略。这个是默认选项，即如果只写@GeneratedValue，等价于@GeneratedValue(strategy=GenerationType.AUTO)。  该策略为主键序列化,而mysql是不支持的 ，oracle支持的。所以才会报错。



（2）提示要执行的SQL预编译失败，有语法错误，日志中显示"insert into [*]user ..."

​	这里的[*]是因为user跟数据库的关键字重合，因此需要显式注解表名 

```java
@Table(name = "Taco_User")
```

​	往用户表插入数据时，驼峰标识的字段phoneNumber，会被识别为phone_number字段，因此建表或者实体类的字段名要做相应的配合修改。



（3）正确注册了用户和登录，但是登录后跳转页面提示403 forbiden

​	因为登录用户权限不足，刷新浏览器因为会话没消失，所以一直提示403

```java
public class User implements UserDetails {

	// other code
	
	// 返回用户被授予权限的一个集合
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 所有的用户都被授予USER权限
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
}
```

​	用户权限集默认需要添加 "ROLE_" 前缀，原来没前缀，所以权限不足，访问报403。





## 10、JPA大坑

​	org.hibernate.PersistentObjectException: detached entity passed to persist

​	使用@ManyToMany、@OneToMany这类注解，需要配合@JoinTable、@JoinColumn这类注解使用

[Spring Data JPA相关——多表联合查询中注解的使用_51CTO博客_spring data jpa 注解](https://blog.51cto.com/u_15067236/4193000)

​	表关联会按照默认的规则插表和关联字段，如果不按照规则的关联表以及表中的字段会报错

```
insert into taco_ingredients (taco_id, ingredients_id) values (?, ?)

java.sql.SQLSyntaxErrorException: Unknown column 'taco_id' in 'field list'

```





## 11、属性加载问题

​	application.properties和application.yml不能共存，前者优先于后者，且可用@PropertySources注解加载

[SpringBoot - 配置文件application.yml使用详解（附：Profile多环境配置） (hangge.com)](https://www.hangge.com/blog/cache/detail_2459.html)





## 12、悬停application.yml不提示create metadata的问题

​	Help->Install New Software -> Add

​	安装插件：http://download.springsource.com/release/TOOLS/update/e4.17

PS. e.xx要看具体使用的eclipse版本

<img src="screenshot\30-springboot_metadata.png" style="zoom:70%;" />

​	安装完之后重启IDE，Windows -> Perferences -> General -> Editors -> File Associations

​	选择*.yml文件默认打开的编辑器为 **Spring YAML Properties Editor**。





## 13、插入数据报错Incorrect string value: '\xE5\xB9\xBF\xE5\xB7\x9E...' for column 'xxx' at row 1

​	编码声明问题，默认是latin编码，插入中文会报错，将其修改为UTF-8即可。

```sql
create table taco_user (
	id bigint not null auto_increment,
	username varchar(50) not null,
	password varchar(256) not null,
	fullname varchar(50) not null,
	street varchar(50) not null,
	city varchar(50) not null,
	state varchar(50) not null,
	zip varchar(50) not null,
	phone_number varchar(50) not null,
	primary key(id)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_bin comment='Taco用户表';
```





## 14、ApplicationContextException: Unable to start ServletWebServerApplicationContext due to missing ServletWebServerFactory bean

[【已解决】Spring容器中找不到ServletWebServerFactory类出现的异常-云社区-华为云 (huaweicloud.com)](https://bbs.huaweicloud.com/blogs/270062)

```properties
# 在配置中添加，表示不是web项目
spring.main.web-application-type=none
```

否则检查是否有引用 spring-boot-starter-web 依赖和使用 @SpringBootApplication注解。





## 15、使用 @RepositoryRestController 的注意事项

（1）不能与 @RestController、@RequestMapping 注解共用

（2）控制器中定义的方法前缀必须跟自动生成的 API 前缀相同，比如自动生成的 API 是：

> **http://localhost:8080/api/tacos{?page,size,sort}**

要对 taco 实体定义一个控制器添加自定义端点，方法的 mapping 前缀就必须是 `"/tacos"` ，否则请求响应 404

<img src="screenshot\75-restapi.png" style="zoom:70%;" />

<img src="screenshot\76-restapi.png" style="zoom:70%;" />

<img src="screenshot\77-restapi.png" style="zoom:70%;" />





## 16、访问静态资源报错 No mapping for GET /css/xx.css

​	在 `WebMvcConfigurer` 接口实现类中添加资源处理器方法：

```java
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private static final String[] CLASS_RESOURCE_LOACTIONS = {
            "classpath:/META-INF/resources/",
            "classpath:/resources/",
            "classpath:/static/",
            "classpath:/public/"
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASS_RESOURCE_LOACTIONS);
    }
}
```

