package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into cuser (name, account_id, token, gmt_create, gmt_modified, bio, avatar_url) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{bio}, #{avatarUrl})")
    void insert(User user);

    @Select("select id, name, account_id as accountId, token, gmt_create as gmtCreate, gmt_modified as gmtModified, bio from cuser where token = #{token}")
    User selectByToken(@Param("token") String token);

    @Select("select id, name, account_id as accountId, token, gmt_create as gmtCreate, gmt_modified as gmtModified, bio from cuser where account_id = #{accountId}")
    User selectByAccountId(@Param("accountId") Long accountId);
}
