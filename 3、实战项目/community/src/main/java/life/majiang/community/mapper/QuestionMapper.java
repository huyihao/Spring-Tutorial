package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, description, gmt_create, gmt_modified, creator, tag) values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void insert(Question question);

    // 查询所有问题列表
    @Select("select * from question")
    List<Question> list();

    // 查询分页问题列表
    @Select("select * from question limit #{offset}, #{size}")
    List<Question> listPage(@Param("offset") Integer offset, @Param("size") Integer size);

    // 获取总记录条数
    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{creator} limit #{offset}, #{size}")
    List<Question> userListPage(@Param("creator") Long creator, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question where creator = #{creator}")
    Integer userCount(@Param("creator") Long creator);

    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") Long id);

    @Update("update question set title = #{title}, description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} where id = #{id}")
    Integer update(Question question);

    @Update("update question set view_count = view_count + 1 where id = #{id}")
    Integer incView(@Param("id") Long id);

    @Update("update question set comment_count = comment_count + #{commentCount} where id = #{id}")
    Integer updateCommentCount(Question question);
}