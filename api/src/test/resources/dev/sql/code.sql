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
-- Table structure for table `code`
--

DROP TABLE IF EXISTS `code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '代码Id',
  `problem_id` bigint(20) unsigned NOT NULL COMMENT '题目Id',
  `code` text NOT NULL COMMENT 'Base64压缩代码',
  `status`tinyint(1) DEFAULT NULL COMMENT '通过状态',
  `language` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '语言',
  `time_used` float NOT NULL COMMENT '消耗时间',
  `memory_used` float NOT NULL COMMENT '消耗内存',
  `submit_time` datetime DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `code_fk_1` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户代码提交表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code`
--

LOCK TABLES `code` WRITE;
/*!40000 ALTER TABLE `code` DISABLE KEYS */;
INSERT INTO `code` VALUES (1, 1,'dGVzdDE=', 1, 'Java', 130, 22640, '2018-01-02 12:33:54');
INSERT INTO `code` VALUES (2, 2,'dGVzdDI=', 2, 'Python', 45, 172856, '2018-02-01 10:54:11');
INSERT INTO `code` VALUES (3, 3,'dGVzdDM=', 3, 'C++', 3, 1725, '2018-03-06 01:28:42');
INSERT INTO `code` VALUES (4, 4,'dGVzdDQ=', 4, 'C', 2, 1992, '2018-04-08 13:14:21');
/*!40000 ALTER TABLE `code` ENABLE KEYS */;
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
