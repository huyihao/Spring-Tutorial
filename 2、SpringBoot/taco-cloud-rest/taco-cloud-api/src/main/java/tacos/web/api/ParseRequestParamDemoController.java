package tacos.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/param")
public class ParseRequestParamDemoController {

	/**
	 * 路径参数获取
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Map> getPathParam(@PathVariable("id") Long id) {
		log.info("id = " + id);		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		return new ResponseEntity<>(map, HttpStatus.OK);	
	}
	
	/**
	 * 多个路径参数获取
	 */
	@GetMapping("/{username}/{age}")
	public ResponseEntity<Map> getPathParam2(@PathVariable("username") String user, @PathVariable("age") Integer age) {
		log.info("username = " + user + ", age = " + age);
		Map<String, Object> map = new HashMap<>();
		map.put("username", user);
		map.put("age", age);
		return new ResponseEntity<>(map, HttpStatus.OK);				
	}
	
	@PostMapping("/{username}/{age}")
	public ResponseEntity<Map> getPathParam3(@PathVariable("username") String user, @PathVariable("age") Integer age) {
		log.info("username = " + user + ", age = " + age);
		Map<String, Object> map = new HashMap<>();
		map.put("username", user);
		map.put("age", age);
		return new ResponseEntity<>(map, HttpStatus.OK);		
	}	
	
	/**
	 * GET参数获取
	 * 
	 * 方式1：方法参数名跟提交参数一致
	 */
	//@GetMapping
	public ResponseEntity<Map> getRequestParam(Long id) {
		log.info("id = " + id);		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	/**
	 * GET参数获取
	 * 
	 * 方式2：通过 HttpServletRequest 接收
	 */	
	//@GetMapping
	public ResponseEntity<Map> getRequestParam2(HttpServletRequest request) {
		Long id = Long.valueOf(request.getParameter("id"));
		log.info("id = " + id);		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	/**
	 * GET参数获取
	 * 
	 * 方式3：使用 @RequestParam 注解
	 */	
	@GetMapping
	public ResponseEntity<Map> getRequestParam3(@RequestParam("id") Long ids) {
		log.info("id = " + ids);		
		Map<String, Object> map = new HashMap<>();
		map.put("id", ids);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}		

	/**
	 * GET参数获取
	 * 
	 * 方式4：使用 bean 接收参数
	 */	
	@GetMapping("/login")
	public ResponseEntity<UserLoginForm> getRequestParam4(UserLoginForm loginForm) {
		log.info("loginForm = " + loginForm);
		return new ResponseEntity<>(loginForm, HttpStatus.OK);
	}	
	
	/**
	 * POST参数获取
	 * 
	 * 方式1：通过 HttpServletRequest 接收
	 */
	//@PostMapping("/login")
	public ResponseEntity<Map> login(HttpServletRequest request) {
		Long id = Long.valueOf(request.getParameter("id"));
		log.info("id = " + id);		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}		
	
	/**
	 * POST参数获取
	 * 
	 * 方式2：通过 @RequestParam 接收
	 */
	//@PostMapping("/login")
	public ResponseEntity<Map> login2(@RequestParam("id") Long ids) {
		log.info("id = " + ids);		
		Map<String, Object> map = new HashMap<>();
		map.put("id", ids);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}	
	
	/**
	 * POST参数获取
	 * 
	 * 方式3：通过 bean 接收
	 */	
	//@PostMapping("/login")
	public ResponseEntity<UserLoginForm> login3(UserLoginForm loginForm) {
		log.info("loginForm = " + loginForm);
		return new ResponseEntity<>(loginForm, HttpStatus.OK);
	}		
	
	/**
	 * POST参数获取
	 * 
	 * 方式4：通过 @RequestBody 接收
	 */		
	@PostMapping("/login")
	public ResponseEntity<UserLoginForm> login4(@RequestBody UserLoginForm loginForm) {
		log.info("loginForm = " + loginForm);
		return new ResponseEntity<>(loginForm, HttpStatus.OK);
	}		
}

class Response {
	private String code;
	private String msg;
	
	public Response(String code, String msg) {		
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}

class UserLoginForm {
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