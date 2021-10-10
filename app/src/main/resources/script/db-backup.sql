-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sch_user
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
-- Current Database: `sch_user`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sch_user` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sch_user`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `contact_no` varchar(255) DEFAULT NULL,
  `epf_no` varchar(255) DEFAULT NULL,
  `join_date` datetime NOT NULL,
  `entity_id` int(11) NOT NULL,
  `user_role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`entity_id`),
  UNIQUE KEY `UK_lo565olv8by15v9qgmqq8ajh8` (`contact_no`),
  UNIQUE KEY `UK_bcvnqdyovgaif6848m8ayi8wq` (`epf_no`),
  KEY `FK_pyu153aa0dw1iveq002987wj7` (`user_role_id`),
  CONSTRAINT `FK_5erqahobd364ynkrpytjso31t` FOREIGN KEY (`entity_id`) REFERENCES `sch_entity`.`entity` (`id`),
  CONSTRAINT `FK_pyu153aa0dw1iveq002987wj7` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('0718761179','1002411','2020-04-02 00:00:00',1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`),
  CONSTRAINT `FK_it77eq964jhfqtu54081ebtio` FOREIGN KEY (`role_id`) REFERENCES `sch_entity`.`role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1),(2),(3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sch_user'
--

--
-- Dumping routines for database 'sch_user'
--

--
-- Current Database: `sch_restaurant_mgmt`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sch_restaurant_mgmt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sch_restaurant_mgmt`;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m06cjup2iyokwpphtiuij56so` (`status`),
  KEY `FK_9xujac7ov11xmaijj1y43ypq7` (`type_id`),
  CONSTRAINT `FK_9xujac7ov11xmaijj1y43ypq7` FOREIGN KEY (`type_id`) REFERENCES `restaurant_type` (`id`),
  CONSTRAINT `FK_m06cjup2iyokwpphtiuij56so` FOREIGN KEY (`status`) REFERENCES `restaurant_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_status`
--

DROP TABLE IF EXISTS `restaurant_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dwreo07uwnq74ti2318si7hy7` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_status`
--

LOCK TABLES `restaurant_status` WRITE;
/*!40000 ALTER TABLE `restaurant_status` DISABLE KEYS */;
INSERT INTO `restaurant_status` VALUES (1,'Available'),(2,'Reserved');
/*!40000 ALTER TABLE `restaurant_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_type`
--

DROP TABLE IF EXISTS `restaurant_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1yc0iy8lm0ljoujdabyxi8m4s` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_type`
--

LOCK TABLES `restaurant_type` WRITE;
/*!40000 ALTER TABLE `restaurant_type` DISABLE KEYS */;
INSERT INTO `restaurant_type` VALUES (2,'Dining Room'),(1,'Table');
/*!40000 ALTER TABLE `restaurant_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sch_restaurant_mgmt'
--

--
-- Dumping routines for database 'sch_restaurant_mgmt'
--

--
-- Current Database: `sch_banquet_hall_mgmt`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sch_banquet_hall_mgmt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sch_banquet_hall_mgmt`;

--
-- Table structure for table `banquet_hall`
--

DROP TABLE IF EXISTS `banquet_hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banquet_hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity` smallint(6) NOT NULL,
  `name` varchar(50) NOT NULL,
  `status` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_a8ygruw5mkulj4ouvl11r53x3` (`name`),
  KEY `FK_8td2fgjnylxj6dvfnvkxqljph` (`status`),
  KEY `FK_sjqb1ylq870kyx667sqnb46fp` (`type_id`),
  CONSTRAINT `FK_8td2fgjnylxj6dvfnvkxqljph` FOREIGN KEY (`status`) REFERENCES `banquet_hall_status` (`id`),
  CONSTRAINT `FK_sjqb1ylq870kyx667sqnb46fp` FOREIGN KEY (`type_id`) REFERENCES `banquet_hall_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banquet_hall`
--

LOCK TABLES `banquet_hall` WRITE;
/*!40000 ALTER TABLE `banquet_hall` DISABLE KEYS */;
INSERT INTO `banquet_hall` VALUES (1,300,'Grand Ballroom',1,1),(2,120,'Royal Ballroom',1,2),(3,250,'Tuscany Ballroom',2,1);
/*!40000 ALTER TABLE `banquet_hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banquet_hall_status`
--

DROP TABLE IF EXISTS `banquet_hall_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banquet_hall_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cv0mjks570qje21pvh3539fap` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banquet_hall_status`
--

LOCK TABLES `banquet_hall_status` WRITE;
/*!40000 ALTER TABLE `banquet_hall_status` DISABLE KEYS */;
INSERT INTO `banquet_hall_status` VALUES (1,'Available'),(2,'In Repair');
/*!40000 ALTER TABLE `banquet_hall_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banquet_hall_type`
--

DROP TABLE IF EXISTS `banquet_hall_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banquet_hall_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e249fsepyer1r02tko9j9apjl` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banquet_hall_type`
--

LOCK TABLES `banquet_hall_type` WRITE;
/*!40000 ALTER TABLE `banquet_hall_type` DISABLE KEYS */;
INSERT INTO `banquet_hall_type` VALUES (1,'Conference Hall'),(3,'Other'),(2,'Reception Hall');
/*!40000 ALTER TABLE `banquet_hall_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sch_banquet_hall_mgmt'
--

--
-- Dumping routines for database 'sch_banquet_hall_mgmt'
--

--
-- Current Database: `sch_entity`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sch_entity` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sch_entity`;

--
-- Table structure for table `entity`
--

DROP TABLE IF EXISTS `entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(250) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `nic` varchar(12) NOT NULL,
  `password` varchar(60) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bgrwkjs79qo5s27ec7hatc4un` (`status`),
  CONSTRAINT `FK_bgrwkjs79qo5s27ec7hatc4un` FOREIGN KEY (`status`) REFERENCES `entity_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entity`
--

LOCK TABLES `entity` WRITE;
/*!40000 ALTER TABLE `entity` DISABLE KEYS */;
INSERT INTO `entity` VALUES (1,'Nugegoda','hashans95@gmail.com','HaShaN','952903683V','$2a$10$b/EswFOoLX81i/zcgRmvHOR//oUX4fTBz9lgnefxqAP0gbSVCSeNG','hashan',1);
/*!40000 ALTER TABLE `entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entity_status`
--

DROP TABLE IF EXISTS `entity_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entity_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m8w32c2ent5j5q335dttucu12` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entity_status`
--

LOCK TABLES `entity_status` WRITE;
/*!40000 ALTER TABLE `entity_status` DISABLE KEYS */;
INSERT INTO `entity_status` VALUES (1,'Active'),(3,'Blocked'),(4,'Deleted'),(2,'Inactive');
/*!40000 ALTER TABLE `entity_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_a7ujv987la0i7a0o91ueevchc` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'APP','App Permission'),(2,'BANQUET_HALL_MGMT','Banquet Hall Management'),(3,'CUSTOMER_MGMT','Customer Management'),(4,'EVENT_MGMT','Event Management'),(5,'FOOD_MGMT','Food Management'),(6,'PAYMENT','Payment'),(7,'RESERVATION','Reservation'),(8,'RESTAURANT_MGMT','Restaurant Management'),(9,'USER_MGMT','User Management');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (2,'Accountant'),(1,'Admin'),(3,'Receptionist');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fn4pldu982p9u158rpk6nho5k` (`permission_id`),
  KEY `FK_j89g87bvih4d6jbxjcssrybks` (`role_id`),
  CONSTRAINT `FK_fn4pldu982p9u158rpk6nho5k` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `FK_j89g87bvih4d6jbxjcssrybks` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1,2),(2,1,1),(3,1,3),(4,2,1),(5,3,1),(6,4,1),(7,5,1),(8,6,1),(9,7,1),(10,8,1),(11,9,1);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sch_entity'
--

--
-- Dumping routines for database 'sch_entity'
--

--
-- Current Database: `sch_food_mgmt`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sch_food_mgmt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sch_food_mgmt`;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `price` decimal(13,2) NOT NULL,
  `category_id` int(11) NOT NULL,
  `cuisine_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qkhr2yo38c1g9n5ss0jl7gxk6` (`name`),
  KEY `FK_hvg80vdashud5mcr5y1l5omdk` (`category_id`),
  KEY `FK_jhk2ssgcixr54mmvl6955y1k8` (`cuisine_id`),
  CONSTRAINT `FK_hvg80vdashud5mcr5y1l5omdk` FOREIGN KEY (`category_id`) REFERENCES `food_category` (`id`),
  CONSTRAINT `FK_jhk2ssgcixr54mmvl6955y1k8` FOREIGN KEY (`cuisine_id`) REFERENCES `food_cuisine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'Yangzhou Fried Rice',560.00,1,1),(2,'Sweet Corn Chicken Soup',235.00,1,2),(3,'Cheese and Chicken Carbonara',650.00,2,3),(4,'Crab Rice Noodles',890.00,3,4);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_category`
--

DROP TABLE IF EXISTS `food_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m50tuveb7r41ja4ik00f8s10x` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_category`
--

LOCK TABLES `food_category` WRITE;
/*!40000 ALTER TABLE `food_category` DISABLE KEYS */;
INSERT INTO `food_category` VALUES (2,'Noodles'),(3,'Pasta'),(1,'Rice');
/*!40000 ALTER TABLE `food_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_cuisine`
--

DROP TABLE IF EXISTS `food_cuisine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_cuisine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hihxvpwbt9da9e7phl0klhpet` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_cuisine`
--

LOCK TABLES `food_cuisine` WRITE;
/*!40000 ALTER TABLE `food_cuisine` DISABLE KEYS */;
INSERT INTO `food_cuisine` VALUES (1,'American'),(2,'Chinese'),(3,'Italian'),(4,'Japanese');
/*!40000 ALTER TABLE `food_cuisine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_menu`
--

DROP TABLE IF EXISTS `food_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `status_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t1x0up3uh5tsghldp9p8qorhb` (`name`),
  KEY `FK_sysgr5anydnynj3wfpuu7vwfv` (`status_id`),
  CONSTRAINT `FK_sysgr5anydnynj3wfpuu7vwfv` FOREIGN KEY (`status_id`) REFERENCES `food_menu_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_menu`
--

LOCK TABLES `food_menu` WRITE;
/*!40000 ALTER TABLE `food_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `food_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_menu_food`
--

DROP TABLE IF EXISTS `food_menu_food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_menu_food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `food_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tqk27o5dm2ju6gejbeecassy7` (`food_id`),
  KEY `FK_14cuvnoqfqujgmnrt5prr8c8o` (`menu_id`),
  CONSTRAINT `FK_14cuvnoqfqujgmnrt5prr8c8o` FOREIGN KEY (`menu_id`) REFERENCES `food_menu` (`id`),
  CONSTRAINT `FK_tqk27o5dm2ju6gejbeecassy7` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_menu_food`
--

LOCK TABLES `food_menu_food` WRITE;
/*!40000 ALTER TABLE `food_menu_food` DISABLE KEYS */;
/*!40000 ALTER TABLE `food_menu_food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_menu_status`
--

DROP TABLE IF EXISTS `food_menu_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_menu_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hic6iufpr1fmsp15fc5mjbiu3` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_menu_status`
--

LOCK TABLES `food_menu_status` WRITE;
/*!40000 ALTER TABLE `food_menu_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `food_menu_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sch_food_mgmt'
--

--
-- Dumping routines for database 'sch_food_mgmt'
--

--
-- Current Database: `sch_reservation`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sch_reservation` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sch_reservation`;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reservation_no` varchar(13) NOT NULL,
  `reserved_on` datetime NOT NULL,
  `customer_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_itn59jge219max0h3hrs9l3t6` (`reservation_no`),
  KEY `FK_69tigr4wbkrwhfef2l1mp6vgu` (`customer_id`),
  KEY `FK_888gqv4vx0ud6tnhw5ll5n49x` (`event_id`),
  KEY `FK_errm7xkewfb2403h30gkpqco` (`status`),
  KEY `FK_c643qlxsja0j7ua88nrvdhqew` (`type_id`),
  KEY `FK_recqnfjcp370rygd9hjjxjtg` (`user_id`),
  CONSTRAINT `FK_69tigr4wbkrwhfef2l1mp6vgu` FOREIGN KEY (`customer_id`) REFERENCES `sch_customer_mgmt`.`customer` (`id`),
  CONSTRAINT `FK_888gqv4vx0ud6tnhw5ll5n49x` FOREIGN KEY (`event_id`) REFERENCES `sch_event_mgmt`.`event` (`id`),
  CONSTRAINT `FK_c643qlxsja0j7ua88nrvdhqew` FOREIGN KEY (`type_id`) REFERENCES `reservation_type` (`id`),
  CONSTRAINT `FK_errm7xkewfb2403h30gkpqco` FOREIGN KEY (`status`) REFERENCES `reservation_status` (`id`),
  CONSTRAINT `FK_recqnfjcp370rygd9hjjxjtg` FOREIGN KEY (`user_id`) REFERENCES `sch_user`.`user` (`entity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,'RES6460208577','2019-08-26 13:00:52',1,1,1,1,1),(2,'RES6460208578','2019-08-27 14:30:52',1,1,1,1,1),(3,'RES6460208579','2019-08-28 14:00:52',1,1,1,1,1),(4,'RES6460208580','2019-08-29 14:00:52',1,1,2,1,1),(5,'RES6460208581','2019-08-30 14:00:52',1,1,1,1,1),(6,'RES6460208582','2019-08-31 14:00:52',1,1,2,1,1),(7,'RES6460208583','2019-08-31 14:00:52',1,1,3,1,1),(8,'RES6460208584','2019-09-01 14:00:52',1,1,3,1,1),(9,'RES6460208585','2019-09-05 14:00:52',1,1,2,1,1),(10,'RES6460208586','2019-09-06 14:00:52',1,1,2,1,1),(11,'RES6460208587','2019-09-08 14:00:52',1,1,1,1,1),(12,'RES6460208588','2019-09-10 14:00:52',1,1,1,1,1),(13,'RES6460208589','2019-09-10 14:00:52',1,1,1,1,1),(14,'RES6460208590','2019-09-11 14:00:52',1,1,3,1,1),(15,'RES6460208591','2019-09-28 14:00:52',1,1,1,1,1),(16,'RES6460208592','2019-09-28 14:00:52',1,1,2,1,1),(17,'RES6460208593','2019-10-01 14:00:52',1,1,2,1,1),(18,'RES6460208594','2019-10-02 14:00:52',1,1,1,1,1);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_status`
--

DROP TABLE IF EXISTS `reservation_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2jvhsnhrg1k5frpgvqjcu9n5t` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_status`
--

LOCK TABLES `reservation_status` WRITE;
/*!40000 ALTER TABLE `reservation_status` DISABLE KEYS */;
INSERT INTO `reservation_status` VALUES (3,'Canceled'),(2,'Confirm'),(1,'Reserved');
/*!40000 ALTER TABLE `reservation_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_type`
--

DROP TABLE IF EXISTS `reservation_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t0qb0jppb0pms748mpfrl7d4g` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_type`
--

LOCK TABLES `reservation_type` WRITE;
/*!40000 ALTER TABLE `reservation_type` DISABLE KEYS */;
INSERT INTO `reservation_type` VALUES (1,'Banquet Hall'),(2,'Restaurant');
/*!40000 ALTER TABLE `reservation_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sch_reservation'
--

--
-- Dumping routines for database 'sch_reservation'
--

--
-- Current Database: `sch_payment`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sch_payment` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sch_payment`;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(13,2) NOT NULL,
  `paid_on` datetime NOT NULL,
  `reference` varchar(13) NOT NULL,
  `accept_by` int(11) NOT NULL,
  `pay_method` int(11) NOT NULL,
  `reservation_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_s82vwq3ea8kpsp0s1n8f5r5c6` (`reference`),
  KEY `FK_awimosdo6piokq6facs348c2o` (`accept_by`),
  KEY `FK_494vsim8dy4sllrrhmueb8mpy` (`pay_method`),
  KEY `FK_3llq7oxcs9j7vlujfdf16jmu` (`reservation_id`),
  KEY `FK_75mumyojfg35lh6icphaxyo0l` (`status`),
  CONSTRAINT `FK_3llq7oxcs9j7vlujfdf16jmu` FOREIGN KEY (`reservation_id`) REFERENCES `sch_reservation`.`reservation` (`id`),
  CONSTRAINT `FK_494vsim8dy4sllrrhmueb8mpy` FOREIGN KEY (`pay_method`) REFERENCES `payment_method` (`id`),
  CONSTRAINT `FK_75mumyojfg35lh6icphaxyo0l` FOREIGN KEY (`status`) REFERENCES `payment_status` (`id`),
  CONSTRAINT `FK_awimosdo6piokq6facs348c2o` FOREIGN KEY (`accept_by`) REFERENCES `sch_user`.`user` (`entity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,50000.00,'2019-08-26 13:00:52','INV6460208577',1,1,1,2),(2,100993.00,'2019-08-26 13:00:52','INV9607139158',1,1,1,1),(3,296483.00,'2019-08-26 13:00:52','INV8079201389',1,1,1,1),(4,133254.00,'2019-08-26 13:00:52','INV8334475647',1,1,1,2),(5,468712.00,'2019-08-26 13:00:52','INV5411748857',1,1,1,1),(6,492440.00,'2019-08-26 13:00:52','INV3184974069',1,1,1,1),(7,216773.00,'2019-08-26 13:00:52','INV6799557173',1,1,1,2),(8,239882.00,'2019-08-26 13:00:52','INV4781339293',1,1,1,1),(9,214087.00,'2019-08-26 13:00:52','INV2631996650',1,1,1,1),(10,165481.00,'2019-08-26 13:00:52','INV0183061916',1,1,1,1),(11,19442.00,'2019-08-26 13:00:52','INV0957778837',1,1,1,1),(12,444773.00,'2019-08-26 13:00:52','INV5954247557',1,1,1,1),(13,405470.00,'2019-08-26 13:00:52','INV4230352541',1,1,1,1),(14,258005.00,'2019-08-26 13:00:52','INV5871854272',1,1,1,1),(15,420986.00,'2019-08-26 13:00:52','INV8306276336',1,1,1,1),(16,432067.00,'2019-08-26 13:00:52','INV2801130671',1,1,1,1),(17,288159.00,'2019-08-26 13:00:52','INV8896731728',1,1,1,1),(18,186200.00,'2019-08-26 13:00:52','INV4251214183',1,1,1,1);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_drvond376oipl4k39o8kor2e4` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'Cash'),(4,'Cheque'),(2,'Credit Card'),(3,'Debit Card'),(5,'Fund Transfer');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_refund`
--

DROP TABLE IF EXISTS `payment_refund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_refund` (
  `refund_on` datetime NOT NULL,
  `payment_id` int(11) NOT NULL,
  `refund_by` int(11) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FK_k8bmvjqx23jn6khwtnsrpi55e` (`refund_by`),
  CONSTRAINT `FK_k8bmvjqx23jn6khwtnsrpi55e` FOREIGN KEY (`refund_by`) REFERENCES `sch_user`.`user` (`entity_id`),
  CONSTRAINT `FK_kitwyqcilnxpgcsgty7huh8f3` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_refund`
--

LOCK TABLES `payment_refund` WRITE;
/*!40000 ALTER TABLE `payment_refund` DISABLE KEYS */;
INSERT INTO `payment_refund` VALUES ('2019-08-31 11:00:52',1,1),('2019-08-31 11:00:52',4,1),('2019-08-31 11:00:52',7,1);
/*!40000 ALTER TABLE `payment_refund` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_status`
--

DROP TABLE IF EXISTS `payment_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_s1b8xa32w50stydbe7nmjgbin` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_status`
--

LOCK TABLES `payment_status` WRITE;
/*!40000 ALTER TABLE `payment_status` DISABLE KEYS */;
INSERT INTO `payment_status` VALUES (2,'Refund'),(1,'Success');
/*!40000 ALTER TABLE `payment_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sch_payment'
--

--
-- Dumping routines for database 'sch_payment'
--

--
-- Current Database: `sch_customer_mgmt`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sch_customer_mgmt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sch_customer_mgmt`;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(200) NOT NULL,
  `email` varchar(140) NOT NULL,
  `name` varchar(50) NOT NULL,
  `nic` varchar(12) NOT NULL,
  `status` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9f9s04ewlgee4787703vrbbpw` (`status`),
  KEY `FK_fws421k3l8uqhop5tobr748yk` (`type_id`),
  CONSTRAINT `FK_9f9s04ewlgee4787703vrbbpw` FOREIGN KEY (`status`) REFERENCES `customer_status` (`id`),
  CONSTRAINT `FK_fws421k3l8uqhop5tobr748yk` FOREIGN KEY (`type_id`) REFERENCES `customer_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'colombo','hashans95@gmail.com','Mr.Hashan Sandeepa','9820368310V',1,1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_contact`
--

DROP TABLE IF EXISTS `customer_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_contact` (
  `contact_number` varchar(15) NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`customer_id`,`contact_number`),
  CONSTRAINT `FK_2ursb7k9kw8uu1onpbhxdip4n` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_contact`
--

LOCK TABLES `customer_contact` WRITE;
/*!40000 ALTER TABLE `customer_contact` DISABLE KEYS */;
INSERT INTO `customer_contact` VALUES ('0778761179',1);
/*!40000 ALTER TABLE `customer_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_status`
--

DROP TABLE IF EXISTS `customer_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2w3vp8g0r9j6yhtua0ay1x073` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_status`
--

LOCK TABLES `customer_status` WRITE;
/*!40000 ALTER TABLE `customer_status` DISABLE KEYS */;
INSERT INTO `customer_status` VALUES (1,'Active'),(2,'Inactive');
/*!40000 ALTER TABLE `customer_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_type`
--

DROP TABLE IF EXISTS `customer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dw90up7ynsi5fvrpisb8pq499` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_type`
--

LOCK TABLES `customer_type` WRITE;
/*!40000 ALTER TABLE `customer_type` DISABLE KEYS */;
INSERT INTO `customer_type` VALUES (2,'Co-Operative Customer'),(1,'Private Customer');
/*!40000 ALTER TABLE `customer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sch_customer_mgmt'
--

--
-- Dumping routines for database 'sch_customer_mgmt'
--

--
-- Current Database: `sch_event_mgmt`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sch_event_mgmt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sch_event_mgmt`;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `event_from` datetime NOT NULL,
  `event_to` datetime NOT NULL,
  `customer_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_73msi7wt09o49e8slup786pbb` (`customer_id`),
  KEY `FK_40nj7kwrqh9evjndwgh2r7d55` (`type_id`),
  KEY `FK_i5kyf6e4q4rish7glpbcr9khb` (`location_id`),
  KEY `FK_11rxegky1k3dx3cwk5t5ciu2s` (`status`),
  CONSTRAINT `FK_11rxegky1k3dx3cwk5t5ciu2s` FOREIGN KEY (`status`) REFERENCES `event_status` (`id`),
  CONSTRAINT `FK_40nj7kwrqh9evjndwgh2r7d55` FOREIGN KEY (`type_id`) REFERENCES `event_type` (`id`),
  CONSTRAINT `FK_73msi7wt09o49e8slup786pbb` FOREIGN KEY (`customer_id`) REFERENCES `sch_customer_mgmt`.`customer` (`id`),
  CONSTRAINT `FK_i5kyf6e4q4rish7glpbcr9khb` FOREIGN KEY (`location_id`) REFERENCES `sch_banquet_hall_mgmt`.`banquet_hall` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'Gayan\'s Wedding','2019-09-04 08:00:00','2019-09-04 17:00:00',1,1,1,1);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_status`
--

DROP TABLE IF EXISTS `event_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_j6qrnoqxew9hbx7hwth3yoli8` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_status`
--

LOCK TABLES `event_status` WRITE;
/*!40000 ALTER TABLE `event_status` DISABLE KEYS */;
INSERT INTO `event_status` VALUES (3,'Canceled'),(4,'Deleted'),(2,'Done'),(1,'Pending');
/*!40000 ALTER TABLE `event_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_type`
--

DROP TABLE IF EXISTS `event_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_th4tw406pfapsyck46jkm0t4u` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_type`
--

LOCK TABLES `event_type` WRITE;
/*!40000 ALTER TABLE `event_type` DISABLE KEYS */;
INSERT INTO `event_type` VALUES (3,'Cooperate Event'),(2,'Party'),(1,'Wedding');
/*!40000 ALTER TABLE `event_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sch_event_mgmt'
--

--
-- Dumping routines for database 'sch_event_mgmt'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-03 19:13:21
