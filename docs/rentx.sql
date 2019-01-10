create table test.user
(
  id             int(11) unsigned auto_increment comment 'ID'
    primary key,
  name           varchar(16)  default ''                not null comment '名称',
  age            int unsigned default 0                 not null comment '年龄',
  add_user_id    int          default 0                 not null comment '添加人ID',
  add_time       timestamp    default CURRENT_TIMESTAMP not null comment '添加时间',
  update_user_id int          default 0                 null comment '更新人ID',
  update_time    timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1)   default 0                 not null comment '标识(是否有效 0有效；-1无效)'
)
  comment '用户表';

INSERT INTO test.user (id, name, age, add_user_id, add_time, update_user_id, update_time, mark) VALUES (1, 'u1', 11, 0, '2019-01-10 11:00:57', 0, '2019-01-10 11:00:57', 0);
INSERT INTO test.user (id, name, age, add_user_id, add_time, update_user_id, update_time, mark) VALUES (2, 'u2', 21, 0, '2019-01-10 11:00:57', 0, '2019-01-10 11:00:57', 0);