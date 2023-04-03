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







# 三、使用操作数据

​	Spring 的理念之一就是 "面向接口编程"，将接口定义和具体实现分隔开，应用系统使用的是接口，这样方便替换使用不同的实现。

​	在数据访问中，访问数据需要先创建一个**数据访问对象（Data Access Object）**，为了面向接口编程，需要定义一个 **DAO 接口**，而 DAO 接口可以有多个不同的 **DAO 实现**， 业务系统中使用的是服务对象，服务对象通过接口来访问 DAO，这样既使得服务对象易于测试，又不再与特定的数据访问实现绑定在一起。

<i#mg src="screenshot\15-DAO.png" style="zoom:60%;" />

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

​	 JPA 的牛逼之处在于定义完接口之后就不用实现类了，因为 Spring Data JPA 会在运行期间自动生成实现类，并将其注册到上下文中，使用的时候将接口注入到控制器中即可。



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

    ​


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

  ​`configure(HttpSecurity http)` 方法 Web 请求保护授权规则，目前还没有配置任何规则，默认访问所有页面都需要保证先登录，`http.formLogin().loginPage("/login")` 表示使用了指定的自定义登录页面的 URL，如果只是简单访问页面，则在视图控制器中添加以下一行即可：

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

  ​



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

  ​



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

​	OrderController 的 `processOrder()` 方法参数添加一个 `@AuthenticationPrincipal User`

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











































































s





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



## 2、JPA使用查无数据问题

​	默认引入H2嵌入数据库会在程序启动时执行资源目录（src/main/resources）下的schema.sql、data.sql，但是引入jpa依赖后，jpa会自动根据@Entity注解标注的POJO的定义生成DDL并建表，覆盖H2默认初始化的行为，为了不被覆盖，需要禁用JPA的自动建表，在配置文件中添加配置：

```properties
# 禁用JPA的自动生成DDL并建表
spring.jpa.hibernate.ddl-auto=none
```



## 3、使用JPA框架ORM映射报错NumberFormatException

​	枚举类型字段，需要使用注解@Enumerated(EnumType.STRING)声明



## 4、Validation IO @Size 使用问题

​	对于普通类型，数据未上送即会报错，但是对于一个容器类型，比如 List<Ingredient> ，如果请求表单未上送数据，则 **@Size(min = 1)** 是不起作用的，这时候为了完善，需要添加一个 **@NotNull** 的注解配合使用。



## 5、表单提交嵌套的对象的问题

​	需要定义转换器 