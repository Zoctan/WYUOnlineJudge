-- MySQL dump 10.16  Distrib 10.1.32-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: api_dev
-- ------------------------------------------------------
-- Server version	10.1.32-MariaDB

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
-- Table structure for table `carousel`
--

DROP TABLE IF EXISTS `carousel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carousel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '轮播Id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '创建用户Id',
  `image` text NOT NULL COMMENT '轮播图片',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标题',
  `description` text NOT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_carousel_title` (`title`),
  CONSTRAINT `carousel_fk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='首页轮播表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carousel`
--

LOCK TABLES `carousel` WRITE;
/*!40000 ALTER TABLE `carousel` DISABLE KEYS */;
INSERT INTO `carousel` VALUES (1,2,'http://p8x4fj91m.bkt.clouddn.com/20180525_1.jpg','蓝桥招聘-互联网新实力招聘专场','xaaa','2018-05-01 12:00:00');
INSERT INTO `carousel` VALUES (2,2,'http://p8x4fj91m.bkt.clouddn.com/20180525_2.jpg','找凳de就是你','xbbb','2018-05-01 09:15:00');
INSERT INTO `carousel` VALUES (3,2,'http://p8x4fj91m.bkt.clouddn.com/20180525_3.jpg','有爱有科技-有爱有未来','xccccc','2018-05-01 11:25:00');
INSERT INTO `carousel` VALUES (4,2,'http://p8x4fj91m.bkt.clouddn.com/20180525_4.jpg','睿创世界-乐享未来','xdddd','2018-05-01 07:13:00');
/*!40000 ALTER TABLE `carousel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-18 12:11:43
