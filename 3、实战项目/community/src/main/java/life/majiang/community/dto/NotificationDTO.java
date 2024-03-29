package life.majiang.community.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long notifier;
    private String notifierName;
    private Long outerId;
    private String outerTitle;
    private Integer type;
    private String typeName;
    private Integer status;
    private Long gmtCreate;
}
