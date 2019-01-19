create database rentx;
use rentx;

-- MySQL dump 10.13  Distrib 5.7.24, for Linux (i686)
--
-- Host: 127.0.0.1    Database: rentx
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--


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

INSERT INTO `tuser` (`id`, `name`, `age`, `add_user_id`, `add_time`, `update_user_id`, `update_time`, `mark`) VALUES (1,'u1',11,0,'2019-01-10 03:00:57',0,'2019-01-10 03:00:57',0);
INSERT INTO `tuser` (`id`, `name`, `age`, `add_user_id`, `add_time`, `update_user_id`, `update_time`, `mark`) VALUES (2,'u2',21,0,'2019-01-10 03:00:57',0,'2019-01-10 03:00:57',0);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-19 20:56:16
