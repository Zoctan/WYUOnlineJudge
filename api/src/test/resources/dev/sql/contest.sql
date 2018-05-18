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
  `permitted` tinyint(1) DEFAULT '1' COMMENT '是否允许加入：0否 | 1是',
  `status` smallint(1) DEFAULT '0' COMMENT '状态：0未开始 | 1已开始 | 2已结束',
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
INSERT INTO `contest` VALUES (1,'2018级C语言考试','<img src="http://p8x4fj91m.bkt.clouddn.com/exam.png" style="width: 100%;height: 100%;"><p>考试形式：闭卷</p><p>考试地点：综合实验楼201</p><p>考试安排：统考</p><p>考试内容：书内重点','2018-07-01 14:45:00','2018-07-01 15:45:00',0,0,NULL);
INSERT INTO `contest` VALUES (2,'滴滴Di-Tech算法大赛校内选拔赛','<img src="https://img-hxy021.didistatic.com/static/ditech/2017/11/10/chinese_1507550686120_1510259595481.jpg" style="width: 100%;height: 100%;"><p>滴滴Di-Tech算法大赛是由滴滴主办、面向全球技术人才的系列赛事。通过开放海量的真实出行数据，用最前沿热门的研究课题，向全球征集更聪明的解决方案，优秀成果有机会将其应用于滴滴的产品端，用科技颠覆全世界数以亿计用户的出行体验。第三届大赛总奖池100万人民币，竞赛主题是“智慧信号灯”，诚邀全球的算法天才们走进智慧交通的世界，寻找“拥堵”这一世界性难题的解决方案。</p>','2018-08-01 09:00:00','2018-08-02 09:00:00',1,0,'$2a$10$g9RoFByPg9/O9RR/GKfmX.QCP0eJ1eThjTXq5d/QZFejuaEWMF0yK');
INSERT INTO `contest` VALUES (3,'2018级数据结构考试','<img src="http://p8x4fj91m.bkt.clouddn.com/exam.png" style="width: 100%;height: 100%;"><p>考试形式：闭卷</p><p>考试地点：综合实验楼202</p><p>考试安排：统考</p><p>考试内容：书内重点','2018-09-01 08:15:00','2018-09-01 09:45:00',0,0,'$2a$10$g9RoFByPg9/O9RR/GKfmX.QCP0eJ1eThjTXq5d/QZFejuaEWMF0yK');
INSERT INTO `contest` VALUES (4,'校内ACM培训赛','<img src="http://p8x4fj91m.bkt.clouddn.com/training.jpg" style="width: 100%;height: 100%;">为第二届ACM比赛做准备的培训赛','2018-01-01 08:15:00','2018-07-01 08:15:00',0,1,NULL);
INSERT INTO `contest` VALUES (5,'第一届校内ACM比赛','<img src="http://p8x4fj91m.bkt.clouddn.com/ACM.jpg" style="width: 100%;height: 100%;">ACM/ICPC程序设计大赛有着悠久的历史，是国际上最受认可最具权威的程序设计大赛。计算机学院将展开ACM程序设计大赛，并为参赛选手开展培训讲座。通过比赛发掘校内高水平人才，为即将来临的广东省ACM程序设计大赛做好准备，以展现我校学生在程序设计方面的能力，为我校增添荣誉；同时也借此机会向广大同学介绍我校的ACM赛事，让有意深造者加入ACM团队，并为广东省ACM程序设计大赛输送人才。','2018-01-01 09:00:00','2018-01-01 12:00:00',1,2,NULL);
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

-- Dump completed on 2018-05-18 12:11:43
