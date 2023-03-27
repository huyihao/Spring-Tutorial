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





# 二、Web 表单处理和校验











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





