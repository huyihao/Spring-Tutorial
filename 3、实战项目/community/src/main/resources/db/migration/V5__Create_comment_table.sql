create table COMMENT
(
    id           bigint auto_increment,
    parent_id    bigint not null,
    type         int    not null,
    commentator  int    not null,
    like_count   bigint default 0,
    gmt_create   bigint not null,
    gmt_modified bigint not null
);

comment on table COMMENT is '评论表';
comment on column COMMENT.id is '评论id';
comment on column COMMENT.parent_id is '父id';
comment on column COMMENT.type is '评论类型';
comment on column COMMENT.commentator is '评论人id';
comment on column COMMENT.like_count is '点赞数';
comment on column COMMENT.gmt_create is '创建时间';
comment on column COMMENT.gmt_modified is '更新时间';

