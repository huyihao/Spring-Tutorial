package life.majiang.community.dto;

import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {

    private Long id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;

}
