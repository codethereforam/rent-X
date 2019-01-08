create table test
(
  id             int(11) unsigned auto_increment comment 'ID' primary key,
  name varchar(16)  default ''                not null comment '名称',
  age  int unsigned default 0 not null comment '年龄',
  opt_id         int          default 0                 not null comment '操作人ID',
  opt_name       varchar(64)  default 'system'          not null comment '操作人员名称',
  add_time       timestamp    default CURRENT_TIMESTAMP not null comment '添加时间',
  update_time    timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1)   default 0                 not null comment '标识(是否有效 0有效；-1无效)'
) comment '系统表' charset = utf8mb4;
