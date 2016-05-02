-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sample
-- ------------------------------------------------------
-- Server version	5.5.40-log

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
-- Current Database: `sample`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sample` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `sample`;

--
-- Table structure for table `assigntable`
--

DROP TABLE IF EXISTS `assigntable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assigntable` (
  `Id` int(2) NOT NULL,
  `Task_Id` int(3) NOT NULL,
  `User_Id` int(2) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assigntable`
--

LOCK TABLES `assigntable` WRITE;
/*!40000 ALTER TABLE `assigntable` DISABLE KEYS */;
INSERT INTO `assigntable` VALUES (10,5,3),(11,5,2),(12,6,3),(13,6,2),(14,7,4),(15,7,2),(16,8,2),(17,8,3),(18,8,4),(19,8,2),(20,9,3),(21,9,4),(22,9,2),(23,10,3),(24,10,4),(25,10,2);
/*!40000 ALTER TABLE `assigntable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissiontable`
--

DROP TABLE IF EXISTS `permissiontable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissiontable` (
  `Permission_Id` int(2) NOT NULL AUTO_INCREMENT,
  `PermissionName` varchar(30) NOT NULL,
  `Action` varchar(15) NOT NULL,
  PRIMARY KEY (`Permission_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissiontable`
--

LOCK TABLES `permissiontable` WRITE;
/*!40000 ALTER TABLE `permissiontable` DISABLE KEYS */;
INSERT INTO `permissiontable` VALUES (1,'Register User','RegisterUser'),(2,'Display Task','DisplayTask');
/*!40000 ALTER TABLE `permissiontable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permissiontable`
--

DROP TABLE IF EXISTS `role_permissiontable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permissiontable` (
  `Id` int(3) NOT NULL,
  `Role_Id` int(2) NOT NULL,
  `Permission_Id` int(2) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Role_Id_idx` (`Role_Id`),
  KEY `Permission_Id_idx` (`Permission_Id`),
  CONSTRAINT `Permission_Id` FOREIGN KEY (`Permission_Id`) REFERENCES `permissiontable` (`Permission_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Role_Id` FOREIGN KEY (`Role_Id`) REFERENCES `roletable` (`Role_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permissiontable`
--

LOCK TABLES `role_permissiontable` WRITE;
/*!40000 ALTER TABLE `role_permissiontable` DISABLE KEYS */;
INSERT INTO `role_permissiontable` VALUES (1,1,2),(3,1,1),(4,2,2);
/*!40000 ALTER TABLE `role_permissiontable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roletable`
--

DROP TABLE IF EXISTS `roletable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roletable` (
  `Role_Id` int(2) NOT NULL,
  `RoleName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Role_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roletable`
--

LOCK TABLES `roletable` WRITE;
/*!40000 ALTER TABLE `roletable` DISABLE KEYS */;
INSERT INTO `roletable` VALUES (1,'Admin'),(2,'User');
/*!40000 ALTER TABLE `roletable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `Task_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` text,
  `Description` text,
  `StartDate` text,
  `EndDate` text,
  `CreatedDate` text,
  PRIMARY KEY (`Task_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasktable`
--

DROP TABLE IF EXISTS `tasktable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasktable` (
  `Task_Id` int(3) NOT NULL AUTO_INCREMENT,
  `Title` text NOT NULL,
  `Description` text,
  `StartDate` text NOT NULL,
  `EndDate` text NOT NULL,
  `CreatedDate` text,
  `CreatedBy` int(2) NOT NULL,
  PRIMARY KEY (`Task_Id`),
  KEY `CreatedBy_idx` (`CreatedBy`),
  CONSTRAINT `CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `usertable` (`User_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasktable`
--

LOCK TABLES `tasktable` WRITE;
/*!40000 ALTER TABLE `tasktable` DISABLE KEYS */;
INSERT INTO `tasktable` VALUES (9,'task1','task1','2016-04-05','2016-04-06','2016-04-06 11:57:18.0',2),(10,'aaa','aaa','2013-02-31','2013-02-31','2016-04-06 13:11:46.0',2);
/*!40000 ALTER TABLE `tasktable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertable`
--

DROP TABLE IF EXISTS `usertable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertable` (
  `User_Id` int(2) NOT NULL AUTO_INCREMENT,
  `UserName` text NOT NULL,
  `Password` varchar(16) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Role_Id` int(2) NOT NULL,
  PRIMARY KEY (`User_Id`),
  KEY `UserRole_Id_idx` (`Role_Id`),
  CONSTRAINT `UserRole_Id` FOREIGN KEY (`Role_Id`) REFERENCES `roletable` (`Role_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertable`
--

LOCK TABLES `usertable` WRITE;
/*!40000 ALTER TABLE `usertable` DISABLE KEYS */;
INSERT INTO `usertable` VALUES (1,'shikha','shikha','shahshikha@gmail.com',1),(2,'shivani','shivani','shahshivani@gmail.com',2),(3,'aaa','aaa','aaa',2),(4,'bbb','bbb','bbb@bbb.com',2);
/*!40000 ALTER TABLE `usertable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worklogtable`
--

DROP TABLE IF EXISTS `worklogtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worklogtable` (
  `WorkLog_Id` int(11) NOT NULL AUTO_INCREMENT,
  `StartTime` text,
  `TotalHours` text,
  `TotalMinutes` text,
  `Task_Id` int(3) DEFAULT NULL,
  `User_Id` int(2) DEFAULT NULL,
  `CreatedDate` datetime NOT NULL,
  PRIMARY KEY (`WorkLog_Id`),
  KEY `Task_Id_idx` (`Task_Id`),
  KEY `User_Id_idx` (`User_Id`),
  CONSTRAINT `Task_Id` FOREIGN KEY (`Task_Id`) REFERENCES `tasktable` (`Task_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `User_Id` FOREIGN KEY (`User_Id`) REFERENCES `usertable` (`User_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worklogtable`
--

LOCK TABLES `worklogtable` WRITE;
/*!40000 ALTER TABLE `worklogtable` DISABLE KEYS */;
INSERT INTO `worklogtable` VALUES (5,'11 00','3','00',9,3,'2016-04-06 11:58:00'),(6,'11 15','2','15',9,2,'2016-04-06 11:58:29'),(7,'10 h 00 m ','3','00',10,3,'2016-04-06 13:12:07'),(8,'11 h 15 m ','2','15',9,2,'2016-04-06 13:13:42');
/*!40000 ALTER TABLE `worklogtable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `sampledb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sampledb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sampledb`;

--
-- Table structure for table `assigntask`
--

DROP TABLE IF EXISTS `assigntask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assigntask` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `AssignTask_Id` int(3) DEFAULT NULL,
  `AssignUser_Id` int(2) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `AssignTask_Id_idx` (`AssignTask_Id`),
  KEY `AssignUser_Id_idx` (`AssignUser_Id`),
  CONSTRAINT `AssignTask_Id` FOREIGN KEY (`AssignTask_Id`) REFERENCES `task` (`Task_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `AssignUser_Id` FOREIGN KEY (`AssignUser_Id`) REFERENCES `user` (`User_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assigntask`
--

LOCK TABLES `assigntask` WRITE;
/*!40000 ALTER TABLE `assigntask` DISABLE KEYS */;
INSERT INTO `assigntask` VALUES (10,28,1);
/*!40000 ALTER TABLE `assigntask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `Permission_Id` int(2) NOT NULL AUTO_INCREMENT,
  `PermissionName` varchar(30) DEFAULT NULL,
  `Action` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`Permission_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'Register User','RegisterUser'),(2,'Display Task','DisplayTask');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `Role_Id` int(2) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Role_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `Id` int(3) NOT NULL AUTO_INCREMENT,
  `Role_Id` int(2) DEFAULT NULL,
  `Permission_Id` int(2) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Permission_Id_idx` (`Permission_Id`),
  KEY `Role_Id_idx` (`Role_Id`),
  CONSTRAINT `Permission_Id` FOREIGN KEY (`Permission_Id`) REFERENCES `permission` (`Permission_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Role_Id` FOREIGN KEY (`Role_Id`) REFERENCES `role` (`Role_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1,1),(2,2,2);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `Task_Id` int(3) NOT NULL AUTO_INCREMENT,
  `Title` text,
  `Description` text,
  `StartDate` text,
  `EndDate` text,
  `CreatedDate` text,
  `CreatedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`Task_Id`),
  KEY `CreatedBy_idx` (`CreatedBy`),
  CONSTRAINT `CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `user` (`User_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (13,'hhh','hhh','hhh','hhh','04-12-2016 12:38:18',1),(20,'abc','ccc','31/6/2016','31/6/2016','04-12-2016 01:41:15',1),(28,'abc','ccc','31/6/2016','31/6/2016','04-12-2016 02:22:12',1);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `User_Id` int(2) NOT NULL,
  `UserName` text,
  `Password` varchar(16) DEFAULT NULL,
  `Email` varchar(30) DEFAULT NULL,
  `Role_Id` int(2) DEFAULT NULL,
  PRIMARY KEY (`User_Id`),
  KEY `UserRole_Id_idx` (`Role_Id`),
  CONSTRAINT `UserRole_Id` FOREIGN KEY (`Role_Id`) REFERENCES `role` (`Role_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'shikha','shikha','shahshikha45@yahoo.in',2),(2,'shivani','shivani','shahshivani23@yahoo.com',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worklog`
--

DROP TABLE IF EXISTS `worklog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worklog` (
  `WorkLog_Id` int(11) NOT NULL AUTO_INCREMENT,
  `StartTime` text,
  `TotalHours` text,
  `TotalMinutes` text,
  `Task_Id` int(3) DEFAULT NULL,
  `User_Id` int(2) DEFAULT NULL,
  `CreatedDate` text,
  PRIMARY KEY (`WorkLog_Id`),
  KEY `Task_Id_idx` (`Task_Id`),
  KEY `User_Id_idx` (`User_Id`),
  CONSTRAINT `Task_Id` FOREIGN KEY (`Task_Id`) REFERENCES `task` (`Task_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `User_Id` FOREIGN KEY (`User_Id`) REFERENCES `user` (`User_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worklog`
--

LOCK TABLES `worklog` WRITE;
/*!40000 ALTER TABLE `worklog` DISABLE KEYS */;
INSERT INTO `worklog` VALUES (1,'1015','3','15',13,1,'04-12-2016 02:54:43'),(2,'1110','8','15',13,2,'04-12-2016 03:27:24'),(3,'1200','3','15',28,1,'04-12-2016 04:52:32');
/*!40000 ALTER TABLE `worklog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-12 16:54:43
