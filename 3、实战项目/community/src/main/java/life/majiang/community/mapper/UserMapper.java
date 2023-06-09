package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into cuser (name, account_id, token, gmt_create, gmt_modified, bio) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{bio})")
    void insert(User user);

    @Select("select * from cuser where token = #{token}")
    User selectByToken(@Param("token") String token);
}
