package tacos;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import tacos.web.WebConfig;

@WebMvcTest(WebConfig.class)			// 测试视图控制器
//@WebMvcTest(HomeController.class)				// 针对HomeController的web测试
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;					// 注入MockMvc	
	
	@Test
	public void testHomePage() throws Exception {
		mockMvc.perform(get("/"))				// 发起对 "/" 的GET
			.andExpect(status().isOk())			// 期待得到HTTP 200
			.andExpect(view().name("home"))		// 期待得到home视图
			.andExpect(content().string(containsString("Welcome to...")));	// 期待包含"Welcome to..."
	}
	
}
