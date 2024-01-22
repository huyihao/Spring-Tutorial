package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into c_user (account_id, name, token, gmt_create, gmt_modified, bio, avatar_url) values (#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{bio}, #{avatarUrl})")
    public void insert(User user);

    @Select("select * from c_user where token = #{token}")
    public User getByToken(@Param("token") String token);

    @Select("select * from c_user where id = #{id}")
    public User getById(@Param("id") long id);

    @Select("select * from c_user where account_id = #{accountId}")
    public User getByAccountId(@Param("accountId") String accountId);

    @Update("update c_user set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, bio = #{bio}, avatar_url = #{avatarUrl} where account_id = #{accountId}")
    public void update(User user);

//    @Select("select * from c_user where id in <foreach></foreach>")
    @Select({"<script>",
                "select * from c_user where id in",
                    "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
                        "#{id}",
                    "</foreach>",
            "</script>"
    })
    public List<User> selectByIds(@Param("ids") List<Long> ids);
}
