-- 用户租赁系统库表

create database rentx;
use rentx;

-- ####################################################

create table category
(
  id             int unsigned auto_increment comment '类别编号'
    primary key,
  name           varchar(32)         default ''                not null comment '类别名称',
  description    varchar(255)        default ''                not null comment '类别描述',
  parent_id      int unsigned        default 0                 not null comment '父类别编号（0代表是根类别）',
  level          tinyint(2) unsigned default 1                 not null comment ' 类别层次（只能为1或2或3）',
  status         tinyint(1) unsigned default 1                 not null comment '状态(1:启用, 0:禁用)',
  add_user_id    int(10)             default 0                 not null comment '添加人ID',
  add_time       timestamp           default CURRENT_TIMESTAMP not null comment '添加时间',
  update_user_id int(10)             default 0                 not null comment '更新人ID',
  update_time    timestamp           default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1) unsigned default 1                 not null comment '删除标识(是否有效 1有效,0无效)'
)
  comment '类别' charset = utf8mb4;

create index idx_parent_id
  on category (parent_id);

INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (1, '服装', '涵盖所有服装', 0, 1, 1, 0, '2019-01-26 13:39:15', 0, '2019-01-26 13:39:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (2, '男装', '涵盖所有男装', 1, 2, 1, 0, '2019-01-26 13:39:15', 0, '2019-01-26 13:39:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (3, '女装', '涵盖所有女装', 1, 2, 1, 0, '2019-01-26 13:39:15', 0, '2019-01-26 13:39:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (4, 'T恤', '涵盖所有女士T恤', 3, 3, 1, 0, '2019-01-26 13:39:15', 0, '2019-01-26 13:39:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (5, '毛衣', '涵盖所有男士毛衣', 2, 3, 1, 0, '2019-01-26 13:39:15', 0, '2019-01-26 13:39:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (6, '短裙', '涵盖所有女士短裙', 3, 3, 1, 0, '2019-01-26 13:39:15', 0, '2019-01-26 13:39:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (7, '家用电器', '涵盖所有家用电器', 0, 1, 1, 0, '2019-01-26 13:39:15', 0, '2019-01-26 13:39:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (8, '电脑办公', '涵盖所有电脑办公', 0, 1, 0, 0, '2019-01-26 13:39:15', 0, '2019-01-26 16:31:48', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (9, '大家电', '涵盖所有大家电', 7, 2, 1, 0, '2019-01-26 13:39:15', 0, '2019-01-26 13:39:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (10, '厨房电器', '涵盖所有厨房电器', 7, 2, 1, 0, '2019-01-26 13:39:15', 0, '2019-01-26 13:39:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (11, '音乐', '涵盖所有音乐相关', 0, 1, 1, 0, '2019-01-26 13:39:15', 0, '2019-01-26 13:39:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (12, 'aaa', 'aaa', 0, 1, 1, 0, '2019-01-26 13:39:15', 0, '2019-01-26 13:39:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (13, 'bbb', 'bbb', 0, 1, 1, 0, '2019-01-26 13:39:15', 0, '2019-01-26 13:39:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (14, 'ccc', 'ccc', 0, 1, 1, 0, '2019-01-26 13:39:15', 0, '2019-03-07 00:09:15', 1);
INSERT INTO rentx.category (id, name, description, parent_id, level, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (32, '衬衫不开心', '衬衫desc', 2, 3, 1, 0, '2019-01-27 18:34:38', 0, '2019-03-10 16:12:52', 1);
create table item
(
  id             int unsigned auto_increment comment '出租项编号'
    primary key,
  user_id        int unsigned                                  not null comment '租用者ID',
  stuff_id       int unsigned                                  not null comment '物品编号',
  approval_time  datetime                                      null comment '审批时间',
  pay_time       datetime                                      null comment '支付时间',
  rent_day       int unsigned                                  not null comment '租用天数',
  end_time       datetime                                      null comment '归还时间',
  apply_time     datetime                                      null comment '申请时间',
  status         tinyint unsigned                              not null comment '状态（0：申请中；1：不通过；2:待支付；3：租用中；4： 已归还）',
  add_date       date                default '1970-01-01'      not null comment '添加日期',
  add_user_id    int(10)             default 0                 not null comment '添加人ID',
  add_time       datetime            default CURRENT_TIMESTAMP not null comment '添加时间',
  update_user_id int(10)             default 0                 not null comment '更新人ID',
  update_time    datetime            default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1) unsigned default 1                 not null comment '删除标识(是否有效 1有效,0无效)'
)
  comment '出租项' charset = utf8mb4;

create index idx_stuff_id
  on item (stuff_id);

create index idx_user_id
  on item (user_id);

INSERT INTO rentx.item (id, user_id, stuff_id, approval_time, pay_time, rent_day, end_time, apply_time, status, add_date, add_user_id, add_time, update_user_id, update_time, mark) VALUES (9, 3, 2, '2019-03-17 13:04:53', '2019-03-17 13:07:30', 5, '2019-03-17 13:08:05', '2019-03-17 13:04:29', 4, '2019-03-17', 3, '2019-03-17 13:04:29', -1, '2019-03-17 13:08:05', 1);
INSERT INTO rentx.item (id, user_id, stuff_id, approval_time, pay_time, rent_day, end_time, apply_time, status, add_date, add_user_id, add_time, update_user_id, update_time, mark) VALUES (10, 3, 2, null, null, 2, null, '2019-03-17 13:23:46', 0, '2019-03-17', 3, '2019-03-17 13:23:46', 3, '2019-03-17 13:23:46', 1);
INSERT INTO rentx.item (id, user_id, stuff_id, approval_time, pay_time, rent_day, end_time, apply_time, status, add_date, add_user_id, add_time, update_user_id, update_time, mark) VALUES (11, 3, 4, '2019-03-17 13:28:44', '2019-03-17 13:57:26', 2, null, '2019-03-17 13:28:25', 3, '2019-03-17', 3, '2019-03-17 13:28:25', 0, '2019-03-17 13:57:26', 1);
INSERT INTO rentx.item (id, user_id, stuff_id, approval_time, pay_time, rent_day, end_time, apply_time, status, add_date, add_user_id, add_time, update_user_id, update_time, mark) VALUES (12, 3, 3, null, null, 6, null, '2019-03-17 13:54:22', 0, '2019-03-17', 3, '2019-03-17 13:54:22', 3, '2019-03-17 13:54:22', 1);
create table menu
(
  id             int unsigned auto_increment comment '菜单编号'
    primary key,
  pid            int unsigned                                  not null comment '父ID，根菜单pid为0',
  name           varchar(32)         default ''                not null comment '菜单名称',
  url            varchar(100)        default ''                not null comment '菜单URL',
  add_user_id    int(10)             default 0                 not null comment '添加人ID',
  add_time       datetime            default CURRENT_TIMESTAMP not null comment '添加时间',
  update_user_id int(10)             default 0                 not null comment '更新人ID',
  update_time    datetime            default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1) unsigned default 1                 not null comment '删除标识(是否有效 1有效,0无效)'
)
  comment '菜单' charset = utf8mb4;

INSERT INTO rentx.menu (id, pid, name, url, add_user_id, add_time, update_user_id, update_time, mark) VALUES (1, 0, '租用', 'rent-in-group', 0, '2019-03-16 17:38:57', 0, '2019-03-16 17:41:38', 1);
INSERT INTO rentx.menu (id, pid, name, url, add_user_id, add_time, update_user_id, update_time, mark) VALUES (2, 0, '出租', 'rent-out-group', 0, '2019-03-16 17:38:57', 0, '2019-03-16 17:41:38', 1);
INSERT INTO rentx.menu (id, pid, name, url, add_user_id, add_time, update_user_id, update_time, mark) VALUES (3, 0, '公共', 'common-group', 0, '2019-03-16 17:38:57', 0, '2019-03-16 17:41:38', 1);
INSERT INTO rentx.menu (id, pid, name, url, add_user_id, add_time, update_user_id, update_time, mark) VALUES (4, 1, '开始租用', '/stuffs/in', 0, '2019-03-16 17:41:38', 0, '2019-03-16 17:41:38', 1);
INSERT INTO rentx.menu (id, pid, name, url, add_user_id, add_time, update_user_id, update_time, mark) VALUES (5, 1, '我的租用', '/items/in', 0, '2019-03-16 17:41:38', 0, '2019-03-16 17:41:38', 1);
INSERT INTO rentx.menu (id, pid, name, url, add_user_id, add_time, update_user_id, update_time, mark) VALUES (7, 2, '开始出租', '/stuffs/out/start', 0, '2019-03-16 17:41:38', 0, '2019-03-16 17:41:38', 1);
INSERT INTO rentx.menu (id, pid, name, url, add_user_id, add_time, update_user_id, update_time, mark) VALUES (8, 2, '我的出租', '/stuffs/out', 0, '2019-03-16 17:41:38', 0, '2019-03-16 17:41:38', 1);
INSERT INTO rentx.menu (id, pid, name, url, add_user_id, add_time, update_user_id, update_time, mark) VALUES (9, 3, '聊天', '/chat/index', 0, '2019-03-16 17:41:38', 0, '2019-03-16 17:41:38', 1);
INSERT INTO rentx.menu (id, pid, name, url, add_user_id, add_time, update_user_id, update_time, mark) VALUES (10, 3, '搜索', '/stuffs/search', 0, '2019-03-16 17:41:38', 0, '2019-03-16 17:41:38', 1);
create table resource
(
  id             int unsigned auto_increment comment '资源编号'
    primary key,
  name           varchar(32)         default ''                not null comment '资源名称',
  description    varchar(50)         default ''                not null comment '资源描述',
  url            varchar(100)        default ''                not null comment '资源URL',
  method         varchar(50)         default ''                not null comment 'HTTP方法',
  add_user_id    int(10)             default 0                 not null comment '添加人ID',
  add_time       timestamp           default CURRENT_TIMESTAMP not null comment '添加时间',
  update_user_id int(10)             default 0                 not null comment '更新人ID',
  update_time    timestamp           default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1) unsigned default 1                 not null comment '删除标识(是否有效 1有效,0无效)'
)
  comment '资源' charset = utf8mb4;

INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (1, '获取所有category VO', '', '/categories', 'GET', 0, '2019-03-16 14:16:08', 0, '2019-03-16 14:16:08', 1);
INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (2, '进入聊天界面', '', '/chat/index', 'GET', 0, '2019-03-16 14:19:57', 0, '2019-03-16 14:19:57', 1);
INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (3, '首页', '', '/', 'GET', 0, '2019-03-16 14:21:21', 0, '2019-03-16 14:21:21', 1);
INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (4, '我的租用', '', '/items/in', 'GET', 0, '2019-03-16 14:24:36', 0, '2019-03-16 14:24:36', 1);
INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (5, '取消申请租用', '', '/items/(\\d+)/cancel-apply', 'POST', 0, '2019-03-16 14:25:52', 0, '2019-03-16 14:25:52', 1);
INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (6, '开始租用页面', '', '/stuffs/in', 'GET', 0, '2019-03-16 14:29:19', 0, '2019-03-16 14:29:19', 1);
INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (7, '开始出租页面', '', '/stuffs/out/start', 'GET', 0, '2019-03-16 14:37:39', 0, '2019-03-16 14:37:39', 1);
INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (8, '我的出租页面', '', '/stuffs/out', 'GET', 0, '2019-03-16 14:37:39', 0, '2019-03-16 14:37:39', 1);
INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (9, '开始出租提交', '', '/stuffs/out', 'POST', 0, '2019-03-16 14:37:39', 0, '2019-03-16 14:37:39', 1);
INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (10, '取消出租', '', '/stuffs/(\\d+)/cancel-rent', 'POST', 0, '2019-03-16 14:37:39', 0, '2019-03-16 14:37:39', 1);
INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (11, '租用', '', '/stuffs/(\\d+)/rent', 'POST', 0, '2019-03-16 14:37:39', 0, '2019-03-16 14:37:39', 1);
INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (12, '跳转到搜索页面', '', '/stuffs/search', 'GET', 0, '2019-03-16 14:37:39', 0, '2019-03-16 14:37:39', 1);
INSERT INTO rentx.resource (id, name, description, url, method, add_user_id, add_time, update_user_id, update_time, mark) VALUES (13, '搜索', '', '/stuffs/search', 'POST', 0, '2019-03-16 14:37:39', 0, '2019-03-16 14:37:39', 1);
create table role
(
  id             int unsigned auto_increment comment '角色编号'
    primary key,
  identifier     varchar(32)         default ''                not null comment '角色标识符',
  name           varchar(32)         default ''                not null comment '角色名称',
  description    varchar(100)        default ''                not null comment '角色描述',
  add_user_id    int(10)             default 0                 not null comment '添加人ID',
  add_time       timestamp           default CURRENT_TIMESTAMP not null comment '添加时间',
  update_user_id int(10)             default 0                 not null comment '更新人ID',
  update_time    timestamp           default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1) unsigned default 1                 not null comment '删除标识(是否有效 1有效,0无效)'
)
  comment '角色' charset = utf8mb4;

INSERT INTO rentx.role (id, identifier, name, description, add_user_id, add_time, update_user_id, update_time, mark) VALUES (1, 'ROOT', 'ROOT用户', '', 0, '2019-01-23 20:46:01', 0, '2019-01-23 20:46:01', 1);
INSERT INTO rentx.role (id, identifier, name, description, add_user_id, add_time, update_user_id, update_time, mark) VALUES (2, 'LESSOR', '出租人', '', 0, '2019-01-23 20:46:01', 0, '2019-01-23 20:46:01', 1);
INSERT INTO rentx.role (id, identifier, name, description, add_user_id, add_time, update_user_id, update_time, mark) VALUES (3, 'LESSEE', '承租人', '', 0, '2019-01-23 20:46:01', 0, '2019-01-23 20:46:01', 1);
INSERT INTO rentx.role (id, identifier, name, description, add_user_id, add_time, update_user_id, update_time, mark) VALUES (4, 'GUEST', '游客用户', '', 0, '2019-01-23 20:46:01', 0, '2019-01-23 20:46:01', 1);
create table role_menu
(
  id             int unsigned auto_increment comment '角色菜单关系编号'
    primary key,
  role_id        int unsigned                                  not null comment '角色ID',
  menu_id        int unsigned                                  not null comment '菜单ID',
  add_user_id    int(10)             default 0                 not null comment '添加人ID',
  add_time       datetime            default CURRENT_TIMESTAMP not null comment '添加时间',
  update_user_id int(10)             default 0                 not null comment '更新人ID',
  update_time    datetime            default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1) unsigned default 1                 not null comment '删除标识(是否有效 1有效,0无效)'
)
  comment '角色菜单关系' charset = utf8mb4;

create index idx_menu_id
  on role_menu (menu_id);

create index idx_role_id
  on role_menu (role_id);

INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (1, 2, 2, 0, '2019-03-16 17:43:30', 0, '2019-03-16 17:43:30', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (2, 2, 3, 0, '2019-03-16 17:43:30', 0, '2019-03-16 17:43:30', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (3, 2, 7, 0, '2019-03-16 17:43:30', 0, '2019-03-16 17:43:30', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (4, 2, 8, 0, '2019-03-16 17:43:30', 0, '2019-03-16 17:43:30', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (5, 2, 9, 0, '2019-03-16 17:43:30', 0, '2019-03-16 17:43:30', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (6, 2, 10, 0, '2019-03-16 17:43:30', 0, '2019-03-16 17:43:30', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (7, 3, 1, 0, '2019-03-16 17:44:12', 0, '2019-03-16 17:44:12', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (8, 3, 3, 0, '2019-03-16 17:44:12', 0, '2019-03-16 17:44:12', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (9, 3, 4, 0, '2019-03-16 17:44:12', 0, '2019-03-16 17:44:12', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (10, 3, 5, 0, '2019-03-16 17:44:12', 0, '2019-03-16 17:44:12', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (11, 3, 9, 0, '2019-03-16 17:44:12', 0, '2019-03-16 17:44:12', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (12, 3, 10, 0, '2019-03-16 17:44:12', 0, '2019-03-16 17:44:12', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (13, 4, 1, 0, '2019-03-16 17:44:58', 0, '2019-03-16 17:44:58', 1);
INSERT INTO rentx.role_menu (id, role_id, menu_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (14, 4, 4, 0, '2019-03-16 17:44:58', 0, '2019-03-16 17:44:58', 1);
create table role_resource
(
  id             int unsigned auto_increment comment '角色资源关系编号'
    primary key,
  role_id        int unsigned                                  not null comment '角色ID',
  resource_id    int unsigned                                  not null comment '资源ID',
  add_user_id    int(10)             default 0                 not null comment '添加人ID',
  add_time       timestamp           default CURRENT_TIMESTAMP not null comment '添加时间',
  update_user_id int(10)             default 0                 not null comment '更新人ID',
  update_time    timestamp           default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1) unsigned default 1                 not null comment '删除标识(是否有效 1有效,0无效)'
)
  comment '角色资源关系' charset = utf8mb4;

create index idx_resource_id
  on role_resource (resource_id);

create index idx_role_id
  on role_resource (role_id);

INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (1, 2, 1, 0, '2019-03-16 14:18:23', 0, '2019-03-16 14:18:23', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (2, 2, 2, 0, '2019-03-16 14:20:45', 0, '2019-03-16 14:20:45', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (3, 3, 2, 0, '2019-03-16 14:20:45', 0, '2019-03-16 14:20:45', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (4, 2, 3, 0, '2019-03-16 14:21:50', 0, '2019-03-16 14:21:50', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (5, 3, 3, 0, '2019-03-16 14:21:50', 0, '2019-03-16 14:21:50', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (6, 4, 3, 0, '2019-03-16 14:21:50', 0, '2019-03-16 14:21:50', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (7, 3, 4, 0, '2019-03-16 14:24:43', 0, '2019-03-16 14:24:43', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (8, 3, 5, 0, '2019-03-16 14:26:44', 0, '2019-03-16 14:26:44', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (9, 3, 6, 0, '2019-03-16 14:29:59', 0, '2019-03-16 14:29:59', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (10, 4, 6, 0, '2019-03-16 14:30:46', 0, '2019-03-16 14:30:46', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (11, 2, 7, 0, '2019-03-16 14:37:43', 0, '2019-03-16 14:37:43', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (12, 2, 8, 0, '2019-03-16 14:37:43', 0, '2019-03-16 14:37:43', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (13, 2, 9, 0, '2019-03-16 14:37:43', 0, '2019-03-16 14:37:43', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (14, 2, 10, 0, '2019-03-16 14:37:43', 0, '2019-03-16 14:37:43', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (15, 3, 11, 0, '2019-03-16 14:37:43', 0, '2019-03-16 14:37:43', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (16, 2, 12, 0, '2019-03-16 14:37:43', 0, '2019-03-16 14:37:43', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (17, 3, 12, 0, '2019-03-16 14:37:43', 0, '2019-03-16 14:37:43', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (18, 2, 13, 0, '2019-03-16 14:37:43', 0, '2019-03-16 14:37:43', 1);
INSERT INTO rentx.role_resource (id, role_id, resource_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (19, 3, 13, 0, '2019-03-16 14:37:43', 0, '2019-03-16 14:37:43', 1);
create table stuff
(
  id             int unsigned auto_increment comment '物品编号'
    primary key,
  category_id    int unsigned                                  not null comment '类别编号',
  name           varchar(32)         default ''                not null comment '物品名称',
  description    varchar(255)        default ''                not null comment '物品描述',
  deposit        decimal(8, 2)       default 0.00              not null comment '押金(rmb)',
  rental         decimal(8, 2)       default 0.00              not null comment '租金（rmb/day）',
  status         tinyint unsigned    default 0                 not null comment '物品状态（0:未租；1:申请租用；2:已租;3:不出租）',
  picture_id     char(32)            default ''                not null comment '图片id',
  user_id        int unsigned        default 0                 not null comment '物品所有者ID',
  add_user_id    int(10)             default 0                 not null comment '添加人ID',
  add_time       timestamp           default CURRENT_TIMESTAMP not null comment '添加时间',
  update_user_id int(10)             default 0                 not null comment '更新人ID',
  update_time    timestamp           default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1) unsigned default 1                 not null comment '删除标识(是否有效 1有效,0无效)'
)
  comment '物品' charset = utf8mb4;

create index idx_category_id
  on stuff (category_id);

create index inx_user_id
  on stuff (user_id);

INSERT INTO rentx.stuff (id, category_id, name, description, deposit, rental, status, picture_id, user_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (2, 5, '男式毛衣001<script>alert6()</script>', '男式毛衣001 暖和', 300.00, 20.00, 1, '', 1, 0, '2019-02-09 22:26:02', 3, '2019-03-17 13:23:46', 1);
INSERT INTO rentx.stuff (id, category_id, name, description, deposit, rental, status, picture_id, user_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (3, 32, '男士衬衫001不开心', '男士衬衫001 帅气 哈哈哈', 500.00, 50.00, 1, '', 1, 0, '2019-02-09 22:26:02', 3, '2019-03-17 13:54:22', 1);
INSERT INTO rentx.stuff (id, category_id, name, description, deposit, rental, status, picture_id, user_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (4, 4, '女士T恤001', '女士T恤001 描述bb 哈哈', 200.00, 30.00, 2, '', 1, 0, '2019-02-13 19:57:57', -1, '2019-03-17 13:28:45', 1);
INSERT INTO rentx.stuff (id, category_id, name, description, deposit, rental, status, picture_id, user_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (5, 5, '男士毛衣aaa', 'aaaaaa 男士毛衣aaa', 1000.00, 30.00, 0, '', 1, 1, '2019-03-17 12:54:51', 1, '2019-03-17 12:54:51', 1);
create table user
(
  id             int unsigned auto_increment comment 'ID'
    primary key,
  username       varchar(20)                                   not null comment '用户名',
  password       char(60)                                      not null comment '密码',
  email          varchar(50)                                   not null comment '邮箱',
  sex            tinyint(2) unsigned default 2                 null comment '性别(0:女，1:男，2:不愿透露)',
  status         tinyint(1) unsigned default 1                 null comment '状态(1:正常,0:锁定)',
  add_user_id    int(10)             default 0                 not null comment '添加人ID',
  add_time       timestamp           default CURRENT_TIMESTAMP not null comment '添加时间',
  update_user_id int(10)             default 0                 not null comment '更新人ID',
  update_time    timestamp           default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1) unsigned default 1                 not null comment '删除标识(是否有效 1有效,0无效)'
)
  comment '用户表' charset = utf8mb4;

INSERT INTO rentx.user (id, username, password, email, sex, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (1, 'u1', '$2a$10$Mm59cQzWYgYcS7ym95W5qOXehcIZ4siNiLqkwQUqWEmI/s1gK7/f.', '', 2, 1, 0, '2019-01-23 20:42:08', 0, '2019-03-16 11:16:02', 1);
INSERT INTO rentx.user (id, username, password, email, sex, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (2, 'u2', '$2a$10$lWj2m62Dcbt/yfltRHGvf.Z0vhslZ4HewF9px7UoNl1i86YCGyz5m', '', 2, 1, 0, '2019-01-23 20:42:29', 0, '2019-03-03 21:10:19', 1);
INSERT INTO rentx.user (id, username, password, email, sex, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (3, 'u3', '$2a$10$AtIpCLurCusuN/VmaZNrPOdfNC4z69F0B8sldvLnuJhKG1GwTIVuu', '', 2, 1, 0, '2019-01-23 20:43:09', 0, '2019-03-03 21:10:19', 1);
INSERT INTO rentx.user (id, username, password, email, sex, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (4, 'u4', '$2a$10$0Exak1pgYcxvJuacCKqteO8uRsfHBXdyC9Lkep8VlDyvYKhLibdzK', '', 2, 1, 0, '2019-01-23 20:43:09', 0, '2019-03-03 21:10:19', 1);
INSERT INTO rentx.user (id, username, password, email, sex, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (5, 'root', '$2a$10$fUctrKyPpyU5Mrj72MJLR.D2sUZBeOiuCCOaegvoziUjs8xyyZ//O', '', 2, 1, 0, '2019-01-23 20:46:41', 0, '2019-03-03 21:10:19', 1);
INSERT INTO rentx.user (id, username, password, email, sex, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (6, 'guest', '$2a$10$dyGpMt8tBxDPTTOZxVxnU.REjI.VPK1fkfNMigpAkhoqxbzHMK/2C', '', 2, 1, 0, '2019-01-23 20:46:41', 0, '2019-03-03 21:10:19', 1);
INSERT INTO rentx.user (id, username, password, email, sex, status, add_user_id, add_time, update_user_id, update_time, mark) VALUES (7, 'admin', '$2a$10$iqCe6BmasY86kl1bhVh13.N1vf6VTb4y2et4q5jvC2cIrmI4A4bua', '', 2, 1, 0, '2019-03-16 20:23:42', 0, '2019-03-16 20:24:15', 1);
create table user_role
(
  id             int unsigned auto_increment comment '用户角色关系编号'
    primary key,
  user_id        int unsigned                                  not null comment '用户ID',
  role_id        int unsigned                                  not null comment '角色ID',
  add_user_id    int(10)             default 0                 not null comment '添加人ID',
  add_time       timestamp           default CURRENT_TIMESTAMP not null comment '添加时间',
  update_user_id int(10)             default 0                 not null comment '更新人ID',
  update_time    timestamp           default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
  mark           tinyint(1) unsigned default 1                 not null comment '删除标识(是否有效 1有效,0无效)'
)
  comment '用户角色关系' charset = utf8mb4;

create index idx_role_id
  on user_role (role_id);

create index idx_user_id
  on user_role (user_id);

INSERT INTO rentx.user_role (id, user_id, role_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (1, 1, 2, 0, '2019-01-23 20:49:16', 0, '2019-01-23 20:49:16', 1);
INSERT INTO rentx.user_role (id, user_id, role_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (2, 2, 2, 0, '2019-01-23 20:49:16', 0, '2019-01-23 20:49:16', 1);
INSERT INTO rentx.user_role (id, user_id, role_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (3, 3, 3, 0, '2019-01-23 20:49:16', 0, '2019-01-23 20:49:16', 1);
INSERT INTO rentx.user_role (id, user_id, role_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (4, 4, 3, 0, '2019-01-23 20:49:16', 0, '2019-01-23 20:49:16', 1);
INSERT INTO rentx.user_role (id, user_id, role_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (5, 5, 1, 0, '2019-01-23 20:49:16', 0, '2019-01-23 20:49:16', 1);
INSERT INTO rentx.user_role (id, user_id, role_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (6, 6, 4, 0, '2019-01-23 20:49:16', 0, '2019-01-23 20:49:16', 1);
INSERT INTO rentx.user_role (id, user_id, role_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (7, 7, 2, 0, '2019-03-03 21:20:21', 0, '2019-03-16 20:26:54', 1);
INSERT INTO rentx.user_role (id, user_id, role_id, add_user_id, add_time, update_user_id, update_time, mark) VALUES (8, 7, 3, 0, '2019-03-16 20:24:27', 0, '2019-03-16 20:26:54', 1);