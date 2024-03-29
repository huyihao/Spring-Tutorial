package life.majiang.community.mbg.dto;

import life.majiang.community.mbg.model.User;
import lombok.Data;

@Data
public class QuestionDTO {

    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;

}
