package life.majiang.community.controller;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import life.majiang.community.provider.dto.AccessTokenDTO;
import life.majiang.community.provider.dto.GithubUser;
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

/**
 * Github OAuth登录回调
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
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getGithubUser(accessToken);
        log.info("user: " + githubUser);
        /**
         * 1、某些情况下返回响应为空，此时githubUser为空
         * 2、某些情况下返回响应非空，但报文不是对应用户信息的json，此时githubUser的属性值为空
         */
        if (githubUser != null && githubUser.getId() != null) {
            User user = null;

            // 登录成功，先判断该用户是否已在本系统登录过了，登陆过会有记录
            user = userMapper.selectByAccountId(githubUser.getId());

            // 登录成功，且该用户尚未在本系统中登录过，则在表中插入一条记录
            if (user == null) {
                user = new User();
                user.setAccountId(String.valueOf(githubUser.getId()));
                user.setName(githubUser.getName());
                String token = UUID.randomUUID().toString();
                user.setToken(token);
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                user.setBio(githubUser.getBio());
                userMapper.insert(user);
            }

            // 写cookie、session
            //request.getSession().setAttribute("user", githubUser);
            response.addCookie(new Cookie("token", user.getToken()));
            return "redirect:/";
        } else {
            // 登录失败，重新登录
            return "redirect:/";
        }
    }

}
