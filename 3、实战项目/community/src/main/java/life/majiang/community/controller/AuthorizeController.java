package life.majiang.community.controller;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@Slf4j
public class AuthorizeController {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUrl;

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClientId(clientId);
        accessTokenDTO.setClientSecret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirectUri(redirectUrl);
        log.info("AccessTokenDTO = " + accessTokenDTO);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = null;
        if (accessToken != null) {
            log.info("accessToken=" + accessToken);
            githubUser = githubProvider.getGithubUser(accessToken);
        }

        if (githubUser != null && githubUser.getId() != null) {
            log.info("Github OAuth succ");

            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setBio(githubUser.getBio());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOrUpdate(user);

            // 设置用户登录会话
            //request.getSession().setAttribute("user", user);
            Cookie cookie = new Cookie("token", user.getToken());
            cookie.setPath("/");
            cookie.setMaxAge(3600 + 8 * 3600);   // 设置超时时间东八区时区要加八小时
            response.addCookie(cookie);

            return "redirect:/";
        } else {
            // 登录失败，重新登录
            log.info("Github OAuth fail");
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
