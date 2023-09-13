package life.majiang.community.controller;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import life.majiang.community.provider.dto.AccessTokenDTO;
import life.majiang.community.provider.dto.GithubUser;
import life.majiang.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Github OAuth登录回调.
 */
@Controller
@Slf4j
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /**
     * Github OAuth登录成功后会回调到这里，并且把获得的授权码code和初始化请求上送的state返回
      */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state", required = false) String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        log.info("accessTokenDTO: " + accessTokenDTO);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getGithubUser(accessToken);
        log.info("user: " + githubUser);
        /**
         * 1、某些情况下返回响应为空，此时githubUser为空
         * 2、某些情况下返回响应非空，但报文不是对应用户信息的json，此时githubUser的属性值为空
         */
        if (githubUser != null && githubUser.getId() != null) {
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setBio(githubUser.getBio());
            user.setAvatarUrl(githubUser.getAvatar_url());

            // 插入或更新数据
            userService.createOrUpdate(user);

            // 写cookie、session
            //request.getSession().setAttribute("user", githubUser);
            response.addCookie(new Cookie("token", user.getToken()));
            return "redirect:/";
        } else {
            // 登录失败，重新登录
            return "redirect:/";
        }
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        // 清除session和cookie
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
