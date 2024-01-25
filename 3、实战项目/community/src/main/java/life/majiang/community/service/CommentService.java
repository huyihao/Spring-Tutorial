package life.majiang.community.service;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.enums.NotificationStatusEnum;
import life.majiang.community.enums.NotificationTypeEnum;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.mapper.NotificationMapper;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Comment;
import life.majiang.community.model.Notification;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Transactional
    public void addComment(Comment comment, User commentator) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            // 回复评论
            Comment dbComment = commentMapper.selectById(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            // 回复问题
            Question question = questionMapper.getById(dbComment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);

            // 增加评论数
            commentMapper.updateCommentCount(comment.getParentId(), 1);

            // 增加通知
            createNotify(commentator, dbComment.getCommentator(), question, NotificationTypeEnum.REPLY_COMMENT);
        } else {
            // 回复问题
            Question question = questionMapper.getById(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);

            // 增加评论数
            question.setCommentCount(1);
            questionMapper.updateCommentCount(question);

            // 增加通知
            createNotify(commentator, question.getCreator(), question, NotificationTypeEnum.REPLY_COMMENT);
        }
    }

    private void createNotify(User commentator, Long receiver, Question question, NotificationTypeEnum notificationTypeEnum) {
        Notification notification = new Notification();
        notification.setNotifier(commentator.getId());
        notification.setNotifierName(commentator.getName());
        notification.setReceiver(receiver);
        notification.setOuterId(question.getId());
        notification.setOuterTitle(question.getTitle());
        notification.setType(notificationTypeEnum.getType());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setGmtModified(notification.getGmtCreate());
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByQuestionId(Long  parentId, CommentTypeEnum type) {
        // 根据问题号查询该问题下评论数
        List<Comment> comments = commentMapper.selectByParentId(parentId, type.getType());
        if (comments.size() == 0) {
            return new ArrayList<CommentDTO>();
        }

        // 因为可能有多条评论是同个人评的，所以这里先去重
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);
        List<User> users = userMapper.selectByIds(userIds);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }

    public Integer likeCountIncr(Long commentId) {
        return commentMapper.updateLikeCount(commentId, 1);
    }
}
