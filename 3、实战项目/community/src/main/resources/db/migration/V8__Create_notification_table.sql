create table notification
(
    id           bigint auto_increment primary key,
    notifier     bigint not null,
    receiver     bigint not null,
    outer_id      bigint not null,
    type         int    not null,
    statue       int    not null,
    gmt_create   bigint not null,
    gmt_modified bigint not null
);

comment on table notification is '通知表';
comment on column notification.id is '通知id';
comment on column notification.notifier is '通知者';
comment on column notification.receiver is '接收者';
comment on column notification.outer_id is '通知内容id';
comment on column notification.type is '通知类型';
comment on column notification.statue is '通知状态';
comment on column notification.gmt_create is '创建时间';
comment on column notification.gmt_modified is '更新时间';