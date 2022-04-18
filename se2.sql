-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: se2_ebank_db
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `balance` bigint DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `time_created` datetime DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'null',999999,'Pro','2000-08-17','Tran Manh Cuong','12345678','0961326301','admin','2022-04-01 00:00:00','admin'),(2,'images/user.png',999999,'Dat noob','2000-01-17','Truong Quoc Dat','12345678','0123456781',NULL,'2022-04-04 11:28:45','newUser1'),(3,'images/user.png',999999,'John Asamalan','2000-06-26','John Asamalan','12345678','0985649854',NULL,'2022-04-04 11:29:20','asamalan'),(4,'images/user.png',999999,'Skill','2000-07-28','Vu Dang Trinh','12345678','0988746545',NULL,'2022-04-04 11:29:48','newUser2'),(5,'null',998999999,'Bank','2000-01-01','Main Bank','12345678','0961326302','admin','2022-04-04 00:00:00','mainBank'),(6,'images/user.jpg',999999,'Test Account User for Mr.Long','2022-04-01','Test Account User for Mr.Long','12345678','0897498546',NULL,'2022-04-12 07:30:21','se2testuser'),(7,'null',1000000,'Test Account Admin for Mr.Long','2022-04-01','Test Account Admin for Mr.Long','12345678','0487484556','admin','2022-04-01 00:00:00','se2testadmin');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gift`
--

DROP TABLE IF EXISTS `gift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gift` (
  `gift_id` int NOT NULL,
  `to_account_id` int DEFAULT NULL,
  PRIMARY KEY (`gift_id`),
  KEY `fk_toAccount_gift` (`to_account_id`),
  CONSTRAINT `FK2it6xbmriosdmpvdiykw36ab5` FOREIGN KEY (`gift_id`) REFERENCES `transaction` (`transaction_id`),
  CONSTRAINT `fk_toAccount_gift` FOREIGN KEY (`to_account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gift`
--

LOCK TABLES `gift` WRITE;
/*!40000 ALTER TABLE `gift` DISABLE KEYS */;
INSERT INTO `gift` VALUES (5,1),(2,5),(4,5),(1,6),(3,7);
/*!40000 ALTER TABLE `gift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invest_type`
--

DROP TABLE IF EXISTS `invest_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invest_type` (
  `invest_type_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `time_created` datetime DEFAULT NULL,
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`invest_type_id`),
  KEY `fk_account_investType` (`account_id`),
  CONSTRAINT `fk_account_investType` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invest_type`
--

LOCK TABLES `invest_type` WRITE;
/*!40000 ALTER TABLE `invest_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `invest_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan` (
  `amount` bigint DEFAULT NULL,
  `duration` int NOT NULL,
  `rate` double NOT NULL,
  `loan_id` int NOT NULL,
  PRIMARY KEY (`loan_id`),
  CONSTRAINT `FK882uh7qgxk0ydop89ck730dv6` FOREIGN KEY (`loan_id`) REFERENCES `invest_type` (`invest_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `normal`
--

DROP TABLE IF EXISTS `normal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `normal` (
  `identity_card` varchar(255) DEFAULT NULL,
  `normal_id` int NOT NULL,
  PRIMARY KEY (`normal_id`),
  CONSTRAINT `FKcjf7yr0l0jkwv3skmekj5168y` FOREIGN KEY (`normal_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `normal`
--

LOCK TABLES `normal` WRITE;
/*!40000 ALTER TABLE `normal` DISABLE KEYS */;
INSERT INTO `normal` VALUES (NULL,2),(NULL,3),(NULL,4),(NULL,6);
/*!40000 ALTER TABLE `normal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `save`
--

DROP TABLE IF EXISTS `save`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `save` (
  `amount` bigint DEFAULT NULL,
  `duration` int NOT NULL,
  `rate` double NOT NULL,
  `save_id` int NOT NULL,
  PRIMARY KEY (`save_id`),
  CONSTRAINT `FKn1ti0vvnnn2l8dd08x39r5t3v` FOREIGN KEY (`save_id`) REFERENCES `invest_type` (`invest_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `save`
--

LOCK TABLES `save` WRITE;
/*!40000 ALTER TABLE `save` DISABLE KEYS */;
/*!40000 ALTER TABLE `save` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_admin`
--

DROP TABLE IF EXISTS `sys_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_admin` (
  `sys_admin_id` int NOT NULL,
  PRIMARY KEY (`sys_admin_id`),
  CONSTRAINT `FKcfwxv0u59neo8k0wej8tuk2lr` FOREIGN KEY (`sys_admin_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_admin`
--

LOCK TABLES `sys_admin` WRITE;
/*!40000 ALTER TABLE `sys_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `amount` bigint NOT NULL,
  `time_created` datetime DEFAULT NULL,
  `account_id` int DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `fk_account_transaction` (`account_id`),
  CONSTRAINT `fk_account_transaction` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,999999,'2022-04-12 07:33:16',5),(2,999,'2022-04-12 07:34:10',7),(3,1000,'2022-04-12 07:34:24',5),(4,9999,'2022-04-14 10:10:57',1),(5,9999,'2022-04-14 10:11:06',5);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-18 15:30:10
