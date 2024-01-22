create table c_user
(
    id           long auto_increment primary key,
    account_id   varchar(100),
    name         varchar(50),
    token        char(36),
    gmt_create   bigint,
    gmt_modified bigint
);

comment on table c_user is '用户信息表';
comment on column c_user.id is '用户id';
comment on column c_user.account_id is 'github账号ID';
comment on column c_user.name is '用户名';
comment on column c_user.token is '登录token';
comment on column c_user.gmt_create is '创建时间';
comment on column c_user.gmt_modified is '修改时间';