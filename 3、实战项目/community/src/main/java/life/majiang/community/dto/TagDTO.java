package life.majiang.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private String categoryId;
    private String categoryName;
    private List<String> tags;
}
