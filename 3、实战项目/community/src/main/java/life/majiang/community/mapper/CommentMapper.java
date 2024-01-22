package life.majiang.community.mapper;

import life.majiang.community.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into comment(parent_id, type, commentator, content, like_count, comment_count, gmt_create, gmt_modified) values (#{parentId}, #{type}, #{commentator}, #{content}, #{likeCount}, #{commentCount}, #{gmtCreate}, #{gmtModified})")
    public Integer insert(Comment comment);

    @Select("select * from comment where id = #{id}")
    Comment selectById(@Param("id") Long id);

    @Select("select * from comment where parent_id = #{parentId} and type = #{type} order by gmt_create desc")
    List<Comment> selectByParentId(@Param("parentId") Long parentId, @Param("type") Integer type);

    @Update("update comment set comment_count = comment_count + #{commentCount} where id = #{id}")
    Integer updateCommentCount(@Param("id") Long parentId, @Param("commentCount") Integer commentCount);

    @Update("update comment set like_count = like_count + #{likeCount} where id = #{id}")
    Integer updateLikeCount(@Param("id") Long id, @Param("likeCount") Integer likeCount);

}
