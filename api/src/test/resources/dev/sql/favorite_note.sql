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
-- Table structure for table `favorite_note`
--

DROP TABLE IF EXISTS `favorite_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_note` (
  `favorite_id` bigint(20) unsigned NOT NULL COMMENT '收藏夹Id',
  `note_id` bigint(20) unsigned NOT NULL COMMENT '笔记Id',
  PRIMARY KEY (`favorite_id`, `note_id`),
  KEY `favorite_id` (`favorite_id`),
  CONSTRAINT `favorite_note_fk_1` FOREIGN KEY (`favorite_id`) REFERENCES `favorite` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favorite_note_fk_2` FOREIGN KEY (`note_id`) REFERENCES `note` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏夹笔记表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_note`
--

LOCK TABLES `favorite_note` WRITE;
/*!40000 ALTER TABLE `favorite_note` DISABLE KEYS */;
INSERT INTO `favorite_note` VALUES (4, 1);
INSERT INTO `favorite_note` VALUES (4, 2);
INSERT INTO `favorite_note` VALUES (4, 3);
/*!40000 ALTER TABLE `favorite_note` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-16 19:26:13
