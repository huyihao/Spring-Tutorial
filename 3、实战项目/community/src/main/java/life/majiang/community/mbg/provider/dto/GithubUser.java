package life.majiang.community.mbg.provider.dto;

import lombok.Data;

@Data
public class GithubUser {

    private Long id;
    private String avatarUrl;
    private String name;
    private String bio;

}
