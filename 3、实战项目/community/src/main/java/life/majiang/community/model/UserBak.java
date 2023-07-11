package life.majiang.community.model;

import lombok.Data;

@Data
public class UserBak {
    private Long id;
    private String name;
    private Long accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
}
