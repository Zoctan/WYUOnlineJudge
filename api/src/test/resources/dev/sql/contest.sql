-- MySQL dump 10.16  Distrib 10.1.26-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: api_dev
-- ------------------------------------------------------
-- Server version	10.1.26-MariaDB-0+deb9u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `contest`
--

DROP TABLE IF EXISTS `contest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contest` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '比赛Id',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标题',
  `description` text NOT NULL COMMENT '描述',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `permitted` tinyint(1) DEFAULT 1 COMMENT '是否允许加入：0否 | 1是',
  `status` smallint(1) DEFAULT 0 COMMENT '状态：0未开始 | 1已开始 | 2已结束',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_contest_title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='比赛表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contest`
--

LOCK TABLES `contest` WRITE;
/*!40000 ALTER TABLE `contest` DISABLE KEYS */;
INSERT INTO `contest` VALUES (1,'a', 'aaa','2018-07-01 00:00:00','2018-07-02 00:00:00', 1, 0, NULL);
INSERT INTO `contest` VALUES (2,'b', 'bbb','2018-08-01 00:00:00','2018-08-02 00:00:00', 0, 0, NULL);
INSERT INTO `contest` VALUES (3,'c', 'ccc','2018-09-01 00:00:00','2018-09-02 00:00:00', 1, 0, '$2a$10$g9RoFByPg9/O9RR/GKfmX.QCP0eJ1eThjTXq5d/QZFejuaEWMF0yK');
INSERT INTO `contest` VALUES (4,'d', 'ddd','2018-01-01 00:00:00','2018-12-02 00:00:00', 1, 1, NULL);
INSERT INTO `contest` VALUES (5,'e', 'eee','2018-01-01 00:00:00','2018-01-02 00:00:00', 1, 2, NULL);
/*!40000 ALTER TABLE `contest` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-16 20:28:17
