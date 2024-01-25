package life.majiang.community.mapper;

import life.majiang.community.model.Notification;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationMapper {

    @Select("select * from notification where receiver = #{receiver} order by gmt_modified desc limit #{offset}, #{size}")
    public List<Notification> selectByReceiver(@Param("receiver") Long receiver, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from notification where receiver = #{receiver}")
    Integer count(@Param("receiver") Long receiver);

    @Select("select count(1) from notification where receiver = #{receiver} and status = 0")
    Integer unreadCount(@Param("receiver") Long receiver);

    @Insert("insert into notification (notifier, notifier_name, receiver, outer_id, outer_title, type, status, gmt_create, gmt_modified) values (#{notifier}, #{notifierName}, #{receiver}, #{outerId}, #{outerTitle}, #{type}, #{status}, #{gmtCreate}, #{gmtModified})")
    Integer insert(Notification notification);

    @Select("select * from notification where id = #{id}")
    Notification selectById(@Param("id") Long id);

    @Update("update notification set status = #{status} where id = #{id}")
    Integer updateStatus(Notification notification);
}
