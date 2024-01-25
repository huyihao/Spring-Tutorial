package life.majiang.community.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import life.majiang.community.dto.CommentCreateDTO;
import life.majiang.community.dto.CommentDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import life.majiang.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object comment(HttpServletRequest request,
                          @RequestBody CommentCreateDTO commentCreateDTO) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if (StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_NOT_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setCommentator(user.getId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0L);
        comment.setCommentCount(0);

        commentService.addComment(comment, user);

        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(comment, commentDTO);
        commentDTO.setUser(user);
        ResultDTO<CommentDTO> resultDTO = ResultDTO.okOf();
        resultDTO.setData(commentDTO);

        return resultDTO;
    }

    // 查询某个评论下所有的二级评论
    @GetMapping("/subComment/{id}")
    @ResponseBody
    public Object getSubComment(HttpServletRequest request,
                                @PathVariable(value = "id") Long id) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        List<CommentDTO> commentDTOS = commentService.listByQuestionId(id, CommentTypeEnum.COMMENT);
        ResultDTO<List<CommentDTO>> resultDTO = ResultDTO.okOf();
        resultDTO.setData(commentDTOS);
        return resultDTO;
    }

    @GetMapping("/commentLike/{id}")
    @ResponseBody
    public Object like(HttpServletRequest request,
                       @PathVariable(value = "id") Long id) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Integer row = commentService.likeCountIncr(id);
        if (row != 1) {
            return ResultDTO.errorOf(CustomizeErrorCode.LIKE_FAILED);
        }

        return ResultDTO.okOf();
    }

}
