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
-- Table structure for table `contest_problem`
--

DROP TABLE IF EXISTS `contest_problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contest_problem` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '题目Id',
  `contest_id` bigint(20) unsigned NOT NULL COMMENT '比赛Id',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标题',
  `description` text NOT NULL COMMENT '描述',
  `accepted` bigint(20) unsigned DEFAULT '0' COMMENT '通过数',
  `submitted` bigint(20) unsigned DEFAULT '0' COMMENT '提交数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_contest_problem_title` (`title`),
  CONSTRAINT `contest_problem_fk_1` FOREIGN KEY (`contest_id`) REFERENCES `contest` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='比赛题目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contest_problem`
--

LOCK TABLES `contest_problem` WRITE;
/*!40000 ALTER TABLE `contest_problem` DISABLE KEYS */;
INSERT INTO `contest_problem` VALUES (1,4,'蛇爬树','<p>在一棵长为n的树上，有很多小蛇在爬。</p><p>每次，小蛇若存在于距离地面x的位置，下一步则会爬到x+1的位置。</p><p>其中有k对关键点，形如ai,bi，表示一条小蛇若当时在ai，则下一步到达的点为bi。</p><p>总共有Q条小蛇，每条小蛇初始时在地面。</p><p>第i条小蛇初始时每天会行动si步，每次后退都会令小蛇更加渴望胜利，若当天该小蛇后退过，则接下来每天小蛇的速度将会乘以2，即每天行动当前步数*2步。若有2天后退过，则小蛇的速度将会是si*4，若有3天后退过，则小蛇的速度将会是si*8，依此类推。</p><p>求每条小蛇到达树顶时所花了多少天。</p><strong>Input</strong><p>第一行两个数n,k(1<=n<=10^9,1<=k<=200000)。</p><p>接下来k行，每行两个数ai,bi表示每一对关键点。(1<=ai,bi<=n)</p><p>接下来一行一个数Q，表示有Q条小蛇。(1<=Q<=200000)</p><p>接下来Q行，每行一个数si。(1<=si<=10^9)</p><p>保证小蛇可以到达树顶。</p><strong>Output</strong><p>Q行表示答案。</p><strong>Input</strong><pre>\n7 3\n1 3\n4 2\n2 5\n2\n1\n2\n</pre><strong>Output</strong><pre>6\n3\n</pre>',0,0);
INSERT INTO `contest_problem` VALUES (2,4,'多边形划分','<p>给一个共有n个点的凸多边形，求一条将该多边形划分为面积和周长都相等的两部分的直线。</p><strong>Input</strong><p>第一行一个正整数n，表示多边形的点数。（n <= 40000）</p><p>接下来的n行,第i+1行，每行两个实数xi,yi,表示凸多边形的一个点的坐标，点按照逆时针或顺时针的顺序给出。</p><p>其中n，|xi|,|yi|<=40000。</p><strong>Output</strong><p>如果存在这样的直线，将这条直线与凸多边形的两个交点的坐标分两行输出。你所求的直线必须与多边形有两个交点，且分多边形的两部分周长或面积相差都不能大于10^-3。</p><p>如果不存在，输出"impossible"（不含引号）。</p><strong>Input</strong><pre>\n4\n0 0\n3 0\n3 3\n0 3\n</pre><strong>Output</strong><pre>\n1 0\n2 3\n</pre>',0,0);
INSERT INTO `contest_problem` VALUES (3,4,'栈的代价和','<p>将1-n按顺序入栈，且可以在任意“栈非空的时刻”弹出栈顶元素，以此种方法得到的出栈序列（弹出的栈顶元素依次组成的序列）即为合法的出栈序列。</p><p>两个出栈序列是不同的，当且仅当两个序列中存在至少一位不相同的元素。</p><p>定义一个出栈序列的代价为cost，则cost为“每次有元素入栈时，将全部栈内元素的标号求和”的和。例如，当n=4时，某个操作序列为“1入 2入 2出 3入 3出 1出 4入 4出”，则有“1入时cost+=1，2入时cost+=(1+2)，3入时cost+=(1+3)，4入时cost+=4”。</p><p>求全部不同的合法出栈序列的cost的和，答案对1000000007（10^9+7）取模。</p><strong>Input</strong><p>一个数表示n（n<=5*10^8）。</p><strong>Output</strong><p>一个数表示答案，答案对1000000007（10^9+7）取模。</p><strong>Input</strong><pre>\n2\n</pre><strong>Output</strong><pre>\n7\n</pre>',0,0);
INSERT INTO `contest_problem` VALUES (4,4,'左偏树计数','<p>有一天滑稽意外接触到了一种叫左偏树的数据结构，他很好奇有n个点的左偏树个数有多少（这里指无标号树个数）。</p><p>滑稽很聪明一下就做出来了，突然发现这个个数，好大，所以只需要你输出方案数模一个数p的值即可。</p><strong>Input</strong><p>每行两个数n(1<=n<=1000),p(1<=1,000,000,000)。</p><strong>Output</strong><p>一个整数表示答案。</p><strong>Input</strong><pre>\n233 2333\n</pre><strong>Output</strong><pre>\n1562\n</pre>',0,0);
INSERT INTO `contest_problem` VALUES (5,4,'xx','xx',0,0);
/*!40000 ALTER TABLE `contest_problem` ENABLE KEYS */;
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
