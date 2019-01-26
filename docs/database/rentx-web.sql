# 用户租赁系统库表

create database rentx;
use rentx;

-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: rentx
-- ------------------------------------------------------
-- Server version	5.7.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
                          `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '类别编号',
                          `name` varchar(32) NOT NULL DEFAULT '' COMMENT '类别名称',
                          `description` varchar(255) NOT NULL DEFAULT '' COMMENT '类别描述',
                          `add_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加人ID',
                          `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                          `update_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新人ID',
                          `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                          `mark` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除标识(是否有效 1有效,0无效)',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

INSERT INTO `category` VALUES (1,'n1','d1',0,'2019-01-23 12:33:48',0,'2019-01-23 12:33:48',1);
INSERT INTO `category` VALUES (2,'n2','d2',0,'2019-01-23 12:33:56',0,'2019-01-23 12:33:56',1);

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
                      `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '出租项编号',
                      `user_id` int(10) unsigned NOT NULL COMMENT '租用者ID',
                      `stuff_id` int(10) unsigned NOT NULL COMMENT '物品编号',
                      `create_time` timestamp NULL DEFAULT NULL COMMENT '租用日期',
                      `rent_day` int(10) unsigned NOT NULL COMMENT '租用天数',
                      `end_time` timestamp NULL DEFAULT NULL COMMENT '归还日期',
                      `apply_time` timestamp NULL DEFAULT NULL COMMENT '申请时间',
                      `status` tinyint(3) unsigned NOT NULL COMMENT '状态（0：申请中；1：不通过；2：租用中；3： 已归还）',
                      `add_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加人ID',
                      `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                      `update_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新人ID',
                      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                      `mark` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除标识(是否有效 1有效,0无效)',
                      PRIMARY KEY (`id`),
                      KEY `idx_user_id` (`user_id`),
                      KEY `idx_stuff_id` (`stuff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出租项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--


--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
                          `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '资源编号',
                          `name` varchar(32) NOT NULL DEFAULT '' COMMENT '资源名称',
                          `description` varchar(50) NOT NULL DEFAULT '' COMMENT '资源描述',
                          `url` varchar(100) NOT NULL DEFAULT '' COMMENT '资源URL',
                          `method` varchar(50) NOT NULL DEFAULT '' COMMENT 'HTTP方法',
                          `add_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加人ID',
                          `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                          `update_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新人ID',
                          `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                          `mark` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除标识(是否有效 1有效,0无效)',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

INSERT INTO `resource` VALUES (6,'','','/tusers','GET',0,'2019-01-23 13:04:11',0,'2019-01-23 13:04:21',1);
INSERT INTO `resource` VALUES (7,'','','/tusers/index','GET',0,'2019-01-23 13:04:11',0,'2019-01-23 13:04:21',1);
INSERT INTO `resource` VALUES (8,'','','/tusers/count','GET',0,'2019-01-23 13:04:11',0,'2019-01-23 13:04:21',1);
INSERT INTO `resource` VALUES (9,'','','/tusers/(\\d+)','GET',0,'2019-01-23 13:04:11',0,'2019-01-23 15:06:56',1);

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
                      `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色编号',
                      `identifier` varchar(32) NOT NULL DEFAULT '' COMMENT '角色标识符',
                      `name` varchar(32) NOT NULL DEFAULT '' COMMENT '角色名称',
                      `description` varchar(100) NOT NULL DEFAULT '' COMMENT '角色描述',
                      `add_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加人ID',
                      `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                      `update_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新人ID',
                      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                      `mark` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除标识(是否有效 1有效,0无效)',
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

INSERT INTO `role` VALUES (1,'ROOT','ROOT用户','',0,'2019-01-23 12:46:01',0,'2019-01-23 12:46:01',1);
INSERT INTO `role` VALUES (2,'LESSOR','出租人','',0,'2019-01-23 12:46:01',0,'2019-01-23 12:46:01',1);
INSERT INTO `role` VALUES (3,'LESSEE','承租人','',0,'2019-01-23 12:46:01',0,'2019-01-23 12:46:01',1);
INSERT INTO `role` VALUES (4,'GUEST','游客用户','',0,'2019-01-23 12:46:01',0,'2019-01-23 12:46:01',1);

--
-- Table structure for table `role_resource`
--

DROP TABLE IF EXISTS `role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_resource` (
                               `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色资源关系编号',
                               `role_id` int(10) unsigned NOT NULL COMMENT '角色ID',
                               `resource_id` int(10) unsigned NOT NULL COMMENT '资源ID',
                               `add_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加人ID',
                               `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                               `update_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新人ID',
                               `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                               `mark` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除标识(是否有效 1有效,0无效)',
                               PRIMARY KEY (`id`),
                               KEY `idx_role_id` (`role_id`),
                               KEY `idx_resource_id` (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='角色资源关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_resource`
--

INSERT INTO `role_resource` VALUES (6,2,6,0,'2019-01-23 13:05:46',0,'2019-01-23 13:05:46',1);
INSERT INTO `role_resource` VALUES (7,2,7,0,'2019-01-23 13:05:46',0,'2019-01-23 13:05:46',1);
INSERT INTO `role_resource` VALUES (8,3,8,0,'2019-01-23 13:05:46',0,'2019-01-23 13:05:46',1);
INSERT INTO `role_resource` VALUES (9,3,9,0,'2019-01-23 13:05:46',0,'2019-01-23 13:05:46',1);
INSERT INTO `role_resource` VALUES (10,3,6,0,'2019-01-23 13:05:46',0,'2019-01-23 13:05:46',1);

--
-- Table structure for table `stuff`
--

DROP TABLE IF EXISTS `stuff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stuff` (
                       `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '物品编号',
                       `category_id` int(10) unsigned NOT NULL COMMENT '类别编号',
                       `name` varchar(32) NOT NULL DEFAULT '' COMMENT '物品名称',
                       `deposit` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '押金(rmb)',
                       `rental` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '租金（rmb/day）',
                       `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '物品状态（0:未租；1:申请租用；2:已租）',
                       `picture_id` char(32) NOT NULL DEFAULT '' COMMENT '图片id',
                       `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '物品所有者ID',
                       `add_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加人ID',
                       `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                       `update_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新人ID',
                       `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                       `mark` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除标识(是否有效 1有效,0无效)',
                       PRIMARY KEY (`id`),
                       KEY `idx_category_id` (`category_id`),
                       KEY `inx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuff`
--


--
-- Table structure for table `tuser`
--

DROP TABLE IF EXISTS `tuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tuser` (
                       `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
                       `name` varchar(16) NOT NULL DEFAULT '' COMMENT '名称',
                       `age` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '年龄',
                       `add_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加人ID',
                       `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                       `update_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新人ID',
                       `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                       `mark` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除标识(是否有效 1有效,0无效)',
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tuser`
--

INSERT INTO `tuser` VALUES (1,'u1',11,0,'2019-01-10 03:00:57',0,'2019-01-10 03:00:57',0);
INSERT INTO `tuser` VALUES (2,'u2',21,0,'2019-01-10 03:00:57',0,'2019-01-10 03:00:57',0);

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
                      `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
                      `username` varchar(20) NOT NULL COMMENT '用户名',
                      `password` char(32) NOT NULL COMMENT '密码',
                      `salt` char(32) NOT NULL COMMENT '盐',
                      `email` varchar(50) NOT NULL COMMENT '邮箱',
                      `sex` tinyint(2) unsigned DEFAULT '2' COMMENT '性别(0:女，1:男，2:不愿透露)',
                      `status` tinyint(1) unsigned DEFAULT '0' COMMENT '状态(1:正常,0:锁定)',
                      `add_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加人ID',
                      `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                      `update_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新人ID',
                      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                      `mark` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除标识(是否有效 1有效,0无效)',
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (1,'u1','{noop}p1','','',2,0,0,'2019-01-23 12:42:08',0,'2019-01-23 12:42:08',1);
INSERT INTO `user` VALUES (2,'u2','{noop}p2','','',2,0,0,'2019-01-23 12:42:29',0,'2019-01-23 12:48:00',1);
INSERT INTO `user` VALUES (3,'u3','{noop}p3','','',2,0,0,'2019-01-23 12:43:09',0,'2019-01-23 12:48:04',1);
INSERT INTO `user` VALUES (4,'u4','{noop}p4','','',2,0,0,'2019-01-23 12:43:09',0,'2019-01-23 12:48:07',1);
INSERT INTO `user` VALUES (5,'root','{noop}root','','',2,0,0,'2019-01-23 12:46:41',0,'2019-01-23 12:48:10',1);
INSERT INTO `user` VALUES (6,'guest','{noop}guest','','',2,0,0,'2019-01-23 12:46:41',0,'2019-01-23 12:48:13',1);

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
                           `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户角色关系编号',
                           `user_id` int(10) unsigned NOT NULL COMMENT '用户ID',
                           `role_id` int(10) unsigned NOT NULL COMMENT '角色ID',
                           `add_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加人ID',
                           `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                           `update_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新人ID',
                           `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                           `mark` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除标识(是否有效 1有效,0无效)',
                           PRIMARY KEY (`id`),
                           KEY `idx_user_id` (`user_id`),
                           KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` VALUES (1,1,2,0,'2019-01-23 12:49:16',0,'2019-01-23 12:49:16',1);
INSERT INTO `user_role` VALUES (2,2,2,0,'2019-01-23 12:49:16',0,'2019-01-23 12:49:16',1);
INSERT INTO `user_role` VALUES (3,3,3,0,'2019-01-23 12:49:16',0,'2019-01-23 12:49:16',1);
INSERT INTO `user_role` VALUES (4,4,3,0,'2019-01-23 12:49:16',0,'2019-01-23 12:49:16',1);
INSERT INTO `user_role` VALUES (5,5,1,0,'2019-01-23 12:49:16',0,'2019-01-23 12:49:16',1);
INSERT INTO `user_role` VALUES (6,6,4,0,'2019-01-23 12:49:16',0,'2019-01-23 12:49:16',1);
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-23 23:35:06