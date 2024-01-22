package life.majiang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    SYSTEM_ERROR(2000, "服务太热啦，要不然你稍候再来试试~"),
    QUESTION_NOT_FOUND(2001, "你找到问题不在了，要不要换个试试？"),
    NO_LOGIN(2002, "未登录不能进行评论，请先登录"),
    TARGET_PARAM_NOT_FOUND(2003, "未选中任何问题或评论进行回复"),
    TARGET_PARAM_WRONG(2004, "上送评论类型错误"),
    COMMENT_NOT_FOUND(2005, "你找到评论不在了，要不要换个试试？"),
    COMMENT_NOT_EMPTY(2006, "评论内容不能为空~"),
    LIKE_FAILED(2007, "点赞失败，请确认评论是否存在");

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
