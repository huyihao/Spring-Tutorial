package life.majiang.community.utils;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class LoginUtils {

    @Autowired
    UserMapper userMapper;

    public void userSessionInit(HttpServletRequest request) {
        // 若用户已登录并记录session，直接访问视图
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") != null) {
            return ;
        }

        // 若用户未记录会话，则尝试根据cookies中记录的token查表并记录session
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return ;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                UserExample userExample = new UserExample();
                userExample.createCriteria()
                           .andTokenEqualTo(token);
                List<User> users = userMapper.selectByExample(userExample);
                //User user = userMapper.selectByToken(token);
                if (users.size() != 0)
                request.getSession().setAttribute("user", users.get(0));
                break;
            }
        }
    }

}
