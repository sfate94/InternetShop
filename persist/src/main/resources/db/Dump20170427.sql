-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `User_Name` varchar(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `Password` varchar(20) NOT NULL,
  `User_Role` varchar(20) NOT NULL,
  PRIMARY KEY (`User_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('employee1','','123','EMPLOYEE'),('manager1','','123','MANAGER');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `model` (
  `Model_ID` varchar(50) NOT NULL,
  `Type_ID` int(11) NOT NULL,
  `Model_Name` varchar(150) NOT NULL,
  PRIMARY KEY (`Model_ID`),
  KEY `Type_ID_idx` (`Type_ID`),
  CONSTRAINT `Type_ID` FOREIGN KEY (`Type_ID`) REFERENCES `type_tools` (`Type_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf16;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES ('M01',1,'Bosch GWS 850 CE'),('M02',2,'Bosch PST 650 RE');
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_details` (
  `ID` varchar(50) NOT NULL,
  `Amount` double NOT NULL,
  `Price` double NOT NULL,
  `Quantity` int(11) NOT NULL,
  `ORDER_ID` varchar(50) NOT NULL,
  `Tools_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Tools_ID_idx` (`Tools_ID`),
  KEY `Order_ID_idx` (`ORDER_ID`),
  CONSTRAINT `Order_ID` FOREIGN KEY (`ORDER_ID`) REFERENCES `orders` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Tools_ID` FOREIGN KEY (`Tools_ID`) REFERENCES `tools` (`Tools_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf16;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `ID` varchar(50) NOT NULL,
  `Amount` double NOT NULL,
  `Customer_Address` varchar(255) NOT NULL,
  `Customer_Email` varchar(128) NOT NULL,
  `Customer_Name` varchar(255) NOT NULL,
  `Customer_Phone` varchar(128) NOT NULL,
  `Order_Date` datetime NOT NULL ,
  `Order_Num` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tools`
--

DROP TABLE IF EXISTS `tools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tools` (
  `Tools_ID` varchar(50) NOT NULL,
  `Model_ID` varchar(50) NOT NULL,
  `Type_ID` int(11) NOT NULL,
  `Cost` int(11) NOT NULL,
  `Length` int(11) NOT NULL,
  `Height` int(11) NOT NULL,
  `Weight` int(11) NOT NULL,
  `Power` int(11) NOT NULL,
  `Speed` int(11) NOT NULL,
  `Image` longblob NOT NULL,
  PRIMARY KEY (`Tools_ID`),
  KEY `Model_ID_idx` (`Model_ID`),
  KEY `Model_IDI_idx` (`Model_ID`),
  KEY `Type_IDI_idx` (`Type_ID`),
  KEY `Model_ID` (`Model_ID`),
  KEY `Model_ID_2` (`Model_ID`),
  KEY `Model` (`Model_ID`),
  KEY `Type_ID` (`Type_ID`),
  CONSTRAINT `Model` FOREIGN KEY (`Model_ID`) REFERENCES `model` (`Model_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Type` FOREIGN KEY (`Type_ID`) REFERENCES `type_tools` (`Type_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf16;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `type_tools`
--

DROP TABLE IF EXISTS `type_tools`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_tools` (
  `Type_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TypeName` varchar(150) NOT NULL,
  PRIMARY KEY (`Type_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf16;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_tools`
--

LOCK TABLES `type_tools` WRITE;
/*!40000 ALTER TABLE `type_tools` DISABLE KEYS */;
INSERT INTO `type_tools` VALUES (1,'Bolgarka'),(2,'Lobzik');
/*!40000 ALTER TABLE `type_tools` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-27 20:04:52
