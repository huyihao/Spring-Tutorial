package tacos.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Spring Security配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder() {
		return new StandardPasswordEncoder("53cr3t");
	}			
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {			
		/**
		 * 自定义用户认证
		 * 使用Spring Security的UserDetails、UserDetailsService接口
		 */
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());				// 设置密码转码器
	}

	/**
	 * 保护web请求，定义授权规则
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		/**
		 * .authorizeRequests：表示验证请求
		 * .antMatchers(String... antPatterns)：使用 {@link AntPathRequestMatcher} 的匹配规则
		 * 		.antMatchers("/design", "/orders").hasRole("ROLE_USER"): 只有ROLE_USER的用户角色才允许访问"/design"、"/orders"
		 * 		.antMatchers("/", "/**").permitAll(): 其他请求对所有用户均可用
		 */
		http.authorizeRequests()
			.antMatchers("/design").hasRole("USER")
			.antMatchers("/", "/**").permitAll();

        /**
         * 1、formLogin()：指定支持基于表单的身份验证
         * 2、未使用 FormLoginConfigurer#loginPage(String) 指定登录页时，将自动生成一个登录页面，亲测此页面引用的是联网的 bootStrap 的样式，所以断网时，样式会有点怪
         * 3、当用户没有登录、没有权限时默认会自动跳转到登录页面(默认 /login),当登录失败时，默认跳转到 /login?error,登录成功时会放行
         * 4、.defaultSuccessUrl("/design", true)：强制要求用户在登录之后统一跳转到"/design"页面
         */		
		http.formLogin().loginPage("/login");
						//.defaultSuccessUrl("/design", true);
		
        /**
         * 默认开启了注销功能，默认以 "/logout" 路劲表示用户注销请求，
         * 注销成功后，默认跳转到 "/login?logout" 登录页面，自动清除 session，清除记住我功能的 Cookie，HttpSession 无效。
         */
        http.logout().logoutSuccessUrl("/");		
		
        /**
         * Spring Security 的 CSRF 防护默认开启，如果是 POST 请求，则必须验证 Token，如果没有，就会报错 403，无权限访问，即使上面对目标请求路径授权了也不行
         * 要么就是 post 请求时带上 csrf 需要的 token 信息，要么就需要直接关闭 csrf 防护。
         * CsrfConfigurer<H> ignoringAntMatchers(String... antPatterns)：对指定的请求不使用 CSRF 保护，即使它们在 requireCsrfProtectionMatcher（RequestMatcher）} 中匹配。
         * CsrfConfigurer<H> ignoringRequestMatchers(RequestMatcher... requestMatchers): 对指定的请求不使用 CSRF 保护，即使它们在 requireCsrfProtectionMatcher（RequestMatcher）} 中匹配。
         * CsrfConfigurer<H> requireCsrfProtectionMatcher(RequestMatcher requireCsrfProtectionMatcher): 对指定的请求使用 CSRF 保护。默认忽略 GET、HEAD、TRACE、OPTIONS 请求，并处理所有其他请求。
         */		
		http.csrf().disable();
        
        /**
         * 默认开始CSRF保护，H2Database 相关的请求需要携带 CSRF Token 及相关参数，所以访问时候出现了 403 。
         * 配置CSRF保护忽略h2-console
         * 
         * h2-console默认禁止页面展示<iframe>标签，设置同源策略即可
         */
        http.csrf().ignoringAntMatchers("/design/**", "/apiorders/**", "/api/**", "/param/**");        
        http.headers().frameOptions().sameOrigin();
	}	
	
}