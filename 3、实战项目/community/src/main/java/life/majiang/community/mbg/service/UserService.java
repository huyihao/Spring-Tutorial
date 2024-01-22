package life.majiang.community.mbg.service;

import life.majiang.community.mbg.mapper.UserMapper;
import life.majiang.community.mbg.model.User;
import life.majiang.community.mbg.model.UserExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                   .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        User dbUser = users.get(0);
        if (dbUser != null) {
            log.debug("login: exists user");

            // 用户已存在，更新信息即可
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());

            userExample.createCriteria()
                       .andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(dbUser, userExample);
        } else {
            log.debug("login: new user");
            // 用户不存在，插入数据
            userMapper.insert(user);
        }
    }

}
