package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into cuser (name, account_id, token, gmt_create, gmt_modified, bio, avatar_url) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{bio}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from cuser where token = #{token}")
    User selectByToken(@Param("token") String token);

    @Select("select * from cuser where account_id = #{accountId}")
    User selectByAccountId(@Param("accountId") Long accountId);

    @Select("select * from cuser where id = #{id}")
    User selectById(@Param("id") Long id);

    @Update("update cuser set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl} where account_id = #{accountId}")
    void update(User user);
}
