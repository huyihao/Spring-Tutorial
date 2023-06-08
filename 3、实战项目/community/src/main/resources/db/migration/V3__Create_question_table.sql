create table question
(
    id            INT auto_increment primary key,
    title         VARCHAR(50) COMMENT '问题标题',
    description   TEXT COMMENT '问题描述',
    gmt_create    BIGINT COMMENT '创建时间',
    gmt_modified  BIGINT COMMENT '修改时间',
    creator       INT comment '创建者',
    comment_count INT DEFAULT 0 COMMENT '评论数',
    view_count    INT DEFAULT 0 COMMENT '阅读数',
    like_count    INT DEFAULT 0 COMMENT '点赞数',
    tag           VARCHAR(256) COMMENT '标签'
);

