package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {

    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private String content;
    private Long likeCount;
    private Integer commentCount;
    private Long gmtCreate;
    private Long gmtModified;
    private User user;

}
