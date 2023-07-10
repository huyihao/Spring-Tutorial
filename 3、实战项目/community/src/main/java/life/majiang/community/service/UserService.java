package life.majiang.community.service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.selectByAccountId(user.getAccountId());
        if (dbUser != null) {
            log.debug("login: exists user");
            // 用户已存在，更新信息即可
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            userMapper.update(dbUser);
        } else {
            log.debug("login: new user");
            // 用户不存在，插入数据
            userMapper.insert(user);
        }
    }
}
