ALTER TABLE C_USER ADD avatar_url VARCHAR(100) NULL;

comment on column c_user.avatar_url is '用户头像';