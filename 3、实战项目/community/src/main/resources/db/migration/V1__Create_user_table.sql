create table CUSER
(
    ID           INTEGER AUTO_INCREMENT primary key,
    ACCOUNT_ID   CHARACTER VARYING(100),
    NAME         CHARACTER VARYING(50),
    TOKEN        CHARACTER VARYING(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT
);

comment on table CUSER is '登录用户表';