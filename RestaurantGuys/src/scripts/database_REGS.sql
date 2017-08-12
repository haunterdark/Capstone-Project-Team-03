CREATE DATABASE  IF NOT EXISTS `capstone` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `capstone`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: capstone
-- ------------------------------------------------------
-- Server version	5.6.27-log

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
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement` (
  `advertisement_id` varchar(7) NOT NULL,
  `advertisement_image` varchar(200) DEFAULT NULL,
  `advertisement_name` varchar(45) DEFAULT 'N/A',
  `advertisement_start` varchar(45) DEFAULT 'N/A',
  `advertisement_end` varchar(45) DEFAULT 'N/A',
  `advertisement_status` int(11) DEFAULT '1',
  `restaurant_id` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`advertisement_id`),
  UNIQUE KEY `ads_id_UNIQUE` (`advertisement_id`),
  KEY `fk_resid_idx` (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
INSERT INTO `advertisement` VALUES ('QC_0001','upload/advertisement/2011_lamborghini_gallardo_spyder-1440x900.jpg','Gà rán chiên giòn cay mới ra mắt','2016-04-06','2016-04-16',0,'NH_0001'),('QC_0002','upload/advertisement/2013_lamborghini_aventador_by_novitec_torado-1366x768.jpg','Ốc Hương xào mè không tỏi','2016-04-06','2016-04-09',1,'NH_0001'),('QC_0003','upload/advertisement/2014_hamann_lamborghini_aventador-1366x768.jpg','Cua rang muối không mặn như muối sắp ra mắt','2016-04-06','2016-04-15',1,'NH_0002'),('QC_0004','upload/advertisement/2015_novitec_torado_lamborghini_huracan_n_largo-1366x768.jpg','Quảng Cáo Mùa Xuân','2016-04-06','2016-04-07',1,'NH_0001'),('QC_0005','upload/advertisement/13147607_1014787225266570_3372388650586243013_o.jpg','Cua Rang ','2016-05-15','2016-05-16',1,'NH_0001'),('QC_0006','upload/advertisement/comnepcuon.png','Xôi nếp cuộn thịt nướng','2016-05-15','2016-05-16',1,'NH_0001');
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campaign`
--

DROP TABLE IF EXISTS `campaign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `campaign` (
  `campaign_id` varchar(7) NOT NULL,
  `campaign_image` varchar(200) DEFAULT NULL,
  `campaign_name` varchar(150) DEFAULT 'N/A',
  `campaign_value` int(11) DEFAULT NULL,
  `campaign_start` varchar(45) DEFAULT 'N/A',
  `campaign_end` varchar(45) DEFAULT 'N/A',
  `campaign_status` int(11) DEFAULT NULL,
  `restaurant_id` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`campaign_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaign`
--

LOCK TABLES `campaign` WRITE;
/*!40000 ALTER TABLE `campaign` DISABLE KEYS */;
INSERT INTO `campaign` VALUES ('CA_0001',NULL,'Giam gia dac biet',30,'2016-04-07','2016-04-09',0,'NH_0001'),('CA_0002','upload/campaign/13164180_1234964576533938_4845504832091912344_n.png','Giảm giá khi ăn cơm sườn',20,'2016-05-23','2016-05-25',2,'NH_0001');
/*!40000 ALTER TABLE `campaign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Sang Trọng'),(2,'Buffet'),(3,'Ăn Chay'),(4,'Dessert');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Hồ Chí Minh'),(2,'Hà Nội'),(3,'Đà Nẵng'),(4,'Hải Phòng'),(5,'Cần Thơ'),(6,'An Giang'),(7,'Bà Rịa - Vũng Tàu'),(8,'Bắc Giang'),(9,'Bắc Kạn'),(10,'Bạc Liêu'),(11,'Bắc Ninh'),(12,'Bến Tre'),(13,'Bình Định'),(14,'Bình Dương'),(15,'Bình Phước'),(16,'Bình Thuận'),(17,'Cà Mau'),(18,'Cao Bằng'),(19,'Đắk Lắk'),(20,'Đắk Nông'),(21,'Điện Biên'),(22,'Đồng Nai'),(23,'Đồng Tháp'),(24,'Gia Lai'),(25,'Hà Giang'),(26,'Hà Nam'),(27,'Hà Tĩnh'),(28,'Hải Dương'),(29,'Hậu Giang'),(30,'Hòa Bình'),(31,'Hưng Yên'),(32,'Khánh Hòa'),(33,'Kiên Giang'),(34,'Kon Tum'),(35,'Lai Châu'),(36,'Lâm Đồng'),(37,'Lạng Sơn'),(38,'Lào Cai'),(39,'Long An'),(40,'Nam Định'),(41,'Nghệ An'),(42,'Ninh Bình'),(43,'Ninh Thuận'),(44,'Phú Thọ'),(45,'Phú Yên'),(46,'Quảng Bình'),(47,'Quảng Nam'),(48,'Quảng Ngãi'),(49,'Quảng Ninh'),(50,'Quảng Trị'),(51,'Sóc Trăng'),(52,'Sơn La'),(53,'Tây Ninh'),(54,'Thái Bình'),(55,'Thái Nguyên'),(56,'Thanh Hóa'),(57,'Thừa Thiên Huế'),(58,'Tiền Giang'),(59,'Trà Vinh'),(60,'Tuyên Quang'),(61,'Vĩnh Long'),(62,'Vĩnh Phúc'),(63,'Yên Bái');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Việt Nam'),(2,'Nhật Bản'),(3,'Mỹ'),(4,'Á - Âu'),(5,'Hàn Quốc'),(6,'Trung Hoa'),(7,'Đức'),(8,'Ý'),(9,'Pháp'),(10,'Tây Ban Nha'),(11,'Thái Lan'),(12,'Ấn Độ'),(13,'Quốc tế'),(14,'Malaysia');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon` (
  `coupon_id` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `campaign_id` varchar(7) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `coupon_createdate` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `coupon_status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`coupon_id`),
  UNIQUE KEY `coupon_id_UNIQUE` (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_id` varchar(40) NOT NULL,
  `customer_image` varchar(200) DEFAULT NULL,
  `customer_name` varchar(45) DEFAULT 'N/A',
  `customer_email` varchar(45) DEFAULT 'N/A',
  `customer_address` varchar(75) DEFAULT 'N/A',
  `city_id` int(11) DEFAULT NULL,
  `district_id` int(11) DEFAULT NULL,
  `customer_phone` varchar(45) DEFAULT 'N/A',
  `customer_status` int(11) DEFAULT '1',
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_id_UNIQUE` (`customer_id`),
  KEY `fk_city_idx` (`city_id`),
  KEY `fk_district_id_idx` (`district_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('000000000000000','upload/customer_avatar/people-512.png','N/A','N/A','N/A',NULL,NULL,'15555215554',1),('355098065098993','upload/customer_avatar/people-512.png','N/A','N/A','N/A',NULL,NULL,'0919996900',1),('KH_0001','upload/customer_avatar/people-512.png','thanh','thanh@gmail.com','158 Lê Lợi',1,1,'0954123654',1),('KH_0002','upload/customer_avatar/people-512.png','danh','danh@gmail.com','1 Nguyễn Khắc Nhu',1,3,'01245666631',1),('KH_0003','upload/customer_avatar/people-512.png','son','son@gmail.com','12/24A Võ Văn Kiệt',1,2,'0911545343',1),('KH_0004','upload/customer_avatar/people-512.png','quyen','quyen@gmail.com','12 Trịnh Đình Trọng',1,14,'01254566858',2),('null','upload/customer_avatar/people-512.png','N/A','N/A','N/A',NULL,NULL,'15555215554',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_restaurant_relationship`
--

DROP TABLE IF EXISTS `customer_restaurant_relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_restaurant_relationship` (
  `restaurant_id` varchar(7) NOT NULL,
  `customer_id` varchar(40) NOT NULL,
  PRIMARY KEY (`restaurant_id`,`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_restaurant_relationship`
--

LOCK TABLES `customer_restaurant_relationship` WRITE;
/*!40000 ALTER TABLE `customer_restaurant_relationship` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_restaurant_relationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `district` (
  `district_id` int(11) NOT NULL AUTO_INCREMENT,
  `district_name` varchar(45) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`district_id`),
  KEY `district_idx` (`city_id`),
  CONSTRAINT `district` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=682 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,'Quận 1',1),(2,'Quận 2',1),(3,'Quận 3',1),(4,'Quận 4',1),(5,'Quận 5',1),(6,'Quận 6',1),(7,'Quận 7',1),(8,'Quận 8',1),(9,'Quận 9',1),(10,'Quận 10',1),(11,'Quận 11',1),(12,'Quận 12',1),(13,'Quận Gò Vấp',1),(14,'Quận Tân Bình',1),(15,'Quận Tân Phú',1),(16,'Quận Bình Thạnh',1),(17,'Quận Phú Nhuận',1),(18,'Quận Thủ Đức',1),(19,'Quận Bình Tân',1),(20,'Huyện Bình Chánh',1),(21,'Huyện Củ Chi',1),(22,'Huyện Hóc Môn',1),(23,'Huyện Nhà Bè',1),(24,'Huyện Cần Giờ',1),(25,'Quận Ba Đình',2),(26,'Quận Hoàn Kiếm',2),(27,'Quận Hai Bà Trưng',2),(28,'Quận Đống Đa',2),(29,'Quận Tây Hồ',2),(30,'Quận Cầu Giấy',2),(31,'Quận Thanh Xuân',2),(32,'Quận Hoàng Mai',2),(33,'Quận Long Biên',2),(34,'Huyện Từ Liêm',2),(35,'Huyện Thanh Trì',2),(36,'Huyện Gia Lâm',2),(37,'Huyện Đông Anh',2),(38,'Huyện Sóc Sơn',2),(39,'Quận Hà Đông',2),(40,'Thị xã Sơn Tây',2),(41,'Huyện Ba Vì',2),(42,'Huyện Phúc Thọ',2),(43,'Huyện Thạch Thất',2),(44,'Huyện Quốc Oai',2),(45,'Huyện Chương Mỹ',2),(46,'Huyện Đan Phượng',2),(47,'Huyện Hoài Đức',2),(48,'Huyện Thanh Oai',2),(49,'Huyện Mỹ Đức',2),(50,'Huyện Ứng Hoà',2),(51,'Huyện Thường Tín',2),(52,'Huyện Phú Xuyên',2),(53,'Huyện Mê Linh',2),(54,'Quận Hải Châu',3),(55,'Quận Thanh Khê',3),(56,'Quận Sơn Trà',3),(57,'Quận Ngũ Hành Sơn',3),(58,'Quận Liên Chiểu',3),(59,'Huyện Hoà Vang',3),(60,'Quận Cẩm Lệ',3),(61,'Quận Hồng Bàng',4),(62,'Quận Lê Chân',4),(63,'Quận Ngô Quyền',4),(64,'Quận Kiến An',4),(65,'Quận Hải An',4),(66,'Quận Đồ Sơn',4),(67,'Huyện An Lão',4),(68,'Huyện Kiến Thuỵ',4),(69,'Huyện Thủy Nguyên',4),(70,'Huyện An Dương',4),(71,'Huyện Tiên Lãng',4),(72,'Huyện Vĩnh Bảo',4),(73,'Huyện Cát Hải',4),(74,'Huyện Bạch Long Vĩ',4),(75,'Quận Dương Kinh',4),(76,'Quận Ninh Kiều',5),(77,'Quận Bình Thuỷ',5),(78,'Quận Cái Răng',5),(79,'Quận Ô Môn',5),(80,'Huyện Phong Điền',5),(81,'Huyện Cờ Đỏ',5),(82,'Huyện Vĩnh Thạnh',5),(83,'Huyện Thốt Nốt',5),(84,'Thành phố Long Xuyên',6),(85,'Thị xã Châu Đốc',6),(86,'Huyện An Phú',6),(87,'Huyện Tân Châu',6),(88,'Huyện Phú Tân',6),(89,'Huyện Tịnh Biên',6),(90,'Huyện Tri Tôn',6),(91,'Huyện Châu Phú',6),(92,'Huyện Chợ Mới',6),(93,'Huyện Châu Thành',6),(94,'Huyện Thoại Sơn',6),(95,'Thành phố Vũng Tàu',7),(96,'Thị xã Bà Rịa',7),(97,'Huyện Xuyên Mộc',7),(98,'Huyện Long Điền',7),(99,'Huyện Côn Đảo',7),(100,'Huyện Tân Thành',7),(101,'Huyện Châu Đức',7),(102,'Huyện Đất Đỏ',7),(103,'Thành phố Bắc Giang',8),(104,'Huyện Yên Thế',8),(105,'Huyện Lục Ngạn',8),(106,'Huyện Sơn Động',8),(107,'Huyện Lục Nam',8),(108,'Huyện Tân Yên',8),(109,'Huyện Hiệp Hoà',8),(110,'Huyện Lạng Giang',8),(111,'Huyện Việt Yên',8),(112,'Huyện Yên Dũng',8),(113,'Thị xã Bắc Kạn',9),(114,'Huyện Chợ Đồn',9),(115,'Huyện Bạch Thông',9),(116,'Huyện Na Rì',9),(117,'Huyện Ngân Sơn',9),(118,'Huyện Ba Bể',9),(119,'Huyện Chợ Mới',9),(120,'Thị xã Bạc Liêu',9),(121,'Huyện Vĩnh Lợi',10),(122,'Huyện Hồng Dân',10),(123,'Huyện Giá Rai',10),(124,'Huyện Phước Long',10),(125,'Huyện Đông Hải',10),(126,'Huyện Hoà Bình',10),(127,'Thành phố Bắc Ninh',11),(128,'Huyện Yên Phong',11),(129,'Huyện Quế Võ',11),(130,'Huyện Tiên Du',11),(131,'Huyện Từ Sơn',11),(132,'Huyện Thuận Thành',11),(133,'Huyện Gia Bình',11),(134,'Huyện Lương Tài',11),(135,'Thị xã Bến Tre',12),(136,'Huyện Châu Thành',12),(137,'Huyện Chợ Lách',12),(138,'Huyện Mỏ Cày',12),(139,'Huyện Giồng Trôm',12),(140,'Huyện Bình Đại',12),(141,'Huyện Ba Tri',12),(142,'Huyện Thạnh Phú',12),(143,'Thành phố Quy Nhơn',13),(144,'Huyện An Lão',13),(145,'Huyện Hoài Ân',13),(146,'Huyện Hoài Nhơn',13),(147,'Huyện Phù Mỹ',13),(148,'Huyện Phù Cát',13),(149,'Huyện Vĩnh Thạnh',13),(150,'Huyện Tây Sơn',13),(151,'Huyện Vân Canh',13),(152,'Huyện An Nhơn',13),(153,'Huyện Tuy Phước',13),(154,'Thị xã Thủ Dầu Một',14),(155,'Huyện Bến Cát',14),(156,'Huyện Tân Uyên',14),(157,'Huyện Thuận An',14),(158,'Huyện Dĩ An',14),(159,'Huyện Phú Giáo',14),(160,'Huyện Dầu Tiếng',14),(161,'Thị xã Đồng Xoài',15),(162,'Huyện Đồng Phú',15),(163,'Huyện Chơn Thành	',15),(164,'Huyện Bình Long',15),(165,'Huyện Lộc Ninh',15),(166,'Huyện Bù Đốp',15),(167,'Huyện Phước Long',15),(168,'Huyện Bù Đăng',15),(169,'Thành phố Phan Thiết',16),(170,'Huyện Tuy Phong',16),(171,'Huyện Bắc Bình',16),(172,'Huyện Hàm Thuận Bắc',16),(173,'Huyện Hàm Thuận Nam',16),(174,'Huyện Hàm Tân',16),(175,'Huyện Đức Linh',16),(176,'Huyện Tánh Linh',16),(177,'Huyện đảo Phú Quý',16),(178,'Thị xã LaGi',16),(179,'Thành phố Cà Mau',17),(180,'Huyện Thới Bình',17),(181,'Huyện U Minh',17),(182,'Huyện Trần Văn Thời',17),(183,'Huyện Cái Nước',17),(184,'Huyện Đầm Dơi',17),(185,'Huyện Ngọc Hiển',17),(186,'Huyện Năm Căn',17),(187,'Huyện Phú Tân',17),(188,'Thị xã Cao Bằng',18),(189,'Huyện Bảo Lạc',18),(190,'Huyện Thông Nông',18),(191,'Huyện Hà Quảng',18),(192,'Huyện Trà Lĩnh',18),(193,'Huyện Trùng Khánh',18),(194,'Huyện Nguyên Bình',18),(195,'Huyện Hoà An',18),(196,'Huyện Quảng Uyên',18),(197,'Huyện Thạch An',18),(198,'Huyện Hạ Lang',18),(199,'Huyện Bảo Lâm',18),(200,'Huyện Phục Hoà',18),(201,'Thành phố Buôn Ma Thuột',19),(202,'Huyện Ea H Leo',19),(203,'Huyện Krông Buk',19),(204,'Huyện Krông Năng',19),(205,'Huyện Ea Súp',19),(206,'Huyện Cư M gar',19),(207,'Huyện Krông Pắc',19),(208,'Huyện Ea Kar',19),(209,'Huyện M`Đrăk',19),(210,'Huyện Krông Ana',19),(211,'Huyện Krông Bông',19),(212,'Huyện Lăk',19),(213,'Huyện Buôn Đôn',19),(214,'Huyện Cư Kuin',19),(215,'Thị xã Gia Nghĩa',20),(216,'Huyện Dăk RLấp',20),(217,'Huyện Dăk Mil',20),(218,'Huyện Cư Jút',20),(219,'Huyện Dăk Song',20),(220,'Huyện Krông Nô',20),(221,'Huyện Dăk GLong',20),(222,'Thành phố Điện Biên Phủ',20),(223,'Thị xã Mường Lay',21),(224,'Huyện Điện Biên',21),(225,'Huyện Tuần Giáo',21),(226,'Huyện Mường Chà',21),(227,'Huyện Tủa Chùa',21),(228,'Huyện Điện Biên Đông',21),(229,'Huyện Mường Nhé',21),(230,'Huyện Mường Ảng',21),(231,'Thành phố Biên Hoà',22),(232,'Huyện Vĩnh Cửu',22),(233,'Huyện Tân Phú',22),(234,'Huyện Định Quán',22),(235,'Huyện Thống Nhất',22),(236,'Thị xã Long Khánh',22),(237,'Huyện Xuân Lộc',22),(238,'Huyện Long Thành',22),(239,'Huyện Nhơn Trạch',22),(240,'Huyện Trảng Bom',22),(241,'Huyện Cẩm Mỹ',22),(242,'Thành phố Cao Lãnh',23),(243,'Thị xã Sa Đéc',23),(244,'Huyện Tân Hồng',23),(245,'Huyện Hồng Ngự',23),(246,'Huyện Tam Nông',23),(247,'Huyện Thanh Bình',23),(248,'Huyện Cao Lãnh',23),(249,'Huyện Lấp Vò',23),(250,'Huyện Tháp Mười',23),(251,'Huyện Lai Vung',23),(252,'	Huyện Châu Thành',23),(253,'Thành phố Pleiku',24),(254,'Huyện Chư Păh',24),(255,'Huyện Mang Yang',24),(256,'Huyện Kbang',24),(257,'Thị xã An Khê',24),(258,'Huyện Kông Chro',24),(259,'Huyện Đức Cơ',24),(260,'Huyện Chưprông',24),(261,'Huyện Chư Sê',24),(262,'Huyện Ayunpa',24),(263,'Huyện Krông Pa',24),(264,'Huyện Ia Grai',24),(265,'Huyện Đăk Đoa',24),(266,'Huyện Ia Pa',24),(267,'Huyện Đăk Pơ',24),(268,'Huyện Phú Thiện',24),(269,'Thành phố Hà Giang',25),(270,'Huyện Đồng Văn',25),(271,'Huyện Mèo Vạc',25),(272,'Huyện Yên Minh',25),(273,'Huyện Quản Bạ',25),(274,'Huyện Vị Xuyên',25),(275,'Huyện Bắc Mê',25),(276,'Huyện Hoàng Su Phì',25),(277,'Huyện Xín Mần',25),(278,'Huyện Bắc Quang',25),(279,'Huyện Quang Bình',25),(280,'Thị xã Phủ Lý',26),(281,'Huyện Duy Tiên',26),(282,'Huyện Kim Bảng',26),(283,'Huyện Lý Nhân',26),(284,'Huỵện Thanh Liêm',26),(285,'Huyện Bình Lục',26),(286,'Thành phố Hà Tĩnh',27),(287,'Thị xã Hồng Lĩnh',27),(288,'Huyện Hương Sơn',27),(289,'Huyện Đức Thọ',27),(290,'Huyện Nghi Xuân',27),(291,'Huyện Can Lộc',27),(292,'Huyện Hương Khê',27),(293,'Huyện Thạch Hà',27),(294,'Huyện Cẩm Xuyên',27),(295,'Huyện Kỳ Anh',27),(296,'Huyện Vũ Quang',27),(297,'Huyện Lộc Hà',27),(298,'Thành phố Hải Dương',28),(299,'Huyện Chí Linh',28),(300,'Huyện Nam Sách',28),(301,'Huyện Kinh Môn',28),(302,'Huyện Gia Lộc',28),(303,'Huyện Tứ Kỳ',28),(304,'Huyện Thanh Miện',28),(305,'Huyện Ninh Giang',28),(306,'Huyện Cẩm Giàng',28),(307,'Huyện Thanh Hà',28),(308,'Huyện Kim Thành',28),(309,'Huyện Bình Giang',28),(310,'Thành phố Vị Thanh',29),(311,'Huyện Vị Thuỷ',29),(312,'Huyện Long Mỹ',29),(313,'Huyện Phụng Hiệp',29),(314,'Huyện Châu Thành',29),(315,'Huyện Châu Thành A',29),(316,'Thị xã Ngã Bảy',29),(317,'Thành phố Hoà Bình',30),(318,'Huyện Đà Bắc',30),(319,'Huyện Mai Châu',30),(320,'Huyện Tân Lạc',30),(321,'Huyện Lạc Sơn',30),(322,'Huyện Kỳ Sơn',30),(323,'Huyện Lưương Sơn',30),(324,'Huyện Kim Bôi',30),(325,'Huyện Lạc Thuỷ',30),(326,'Huyện Yên Thuỷ',30),(327,'Huyện Cao Phong',30),(328,'Thị xã Hưng Yên',31),(329,'Huyện Kim Động',31),(330,'Huyện Ân Thi',31),(331,'Huyện Khoái Châu',31),(332,'Huyện Yên Mỹ',31),(333,'Huyện Tiên Lữ',31),(334,'Huyện Phù Cừ',31),(335,'Huyện Mỹ Hào',31),(336,'Huyện Văn Lâm',31),(337,'Huyện Văn Giang',31),(338,'Thành phố Nha Trang',32),(339,'Huyện Vạn Ninh',32),(340,'Huyện Ninh Hoà',32),(341,'Huyện Diên Khánh',32),(342,'Huyện Khánh Vĩnh',32),(343,'Thị xã Cam Ranh',32),(344,'Huyện Khánh Sơn',32),(345,'Huyện Trường Sa',32),(346,'Huyện Cam Lâm',32),(347,'Thành phố Rạch Giá',33),(348,'Thị xã Hà Tiên',33),(349,'Huyện Kiên Lương',33),(350,'Huyện Hòn Đất',33),(351,'Huyện Tân Hiệp',33),(352,'Huyện Châu Thành',33),(353,'Huyện Giồng Riềng',33),(354,'Huyện Gò Quao',33),(355,'Huyện An Biên',33),(356,'Huyện An Minh',33),(357,'Huyện Vĩnh Thuận',33),(358,'Huyện Phú Quốc',33),(359,'Huyện Kiên Hải',33),(360,'Huyện U minh Thượng',33),(361,'Thị xã KonTum',34),(362,'Huyện Đăk Glei',34),(363,'Huyện Ngọc Hồi',34),(364,'Huyện Đăk Tô',34),(365,'Huyện Sa Thầy',34),(366,'Huyện Kon Plong',34),(367,'Huyện Đăk Hà',34),(368,'Huyện Kon Rộy',34),(369,'Huyện Tu Mơ Rông',34),(370,'Thị xã Lai Châu',35),(371,'Huyện Tam Đường',35),(372,'Huyện Phong Thổ',35),(373,'Huyện Sìn Hồ',35),(374,'Huyện Mường Tè',35),(375,'Huyện Than Uyên',35),(376,'Thành phố Đà Lạt',36),(377,'Thị xã Bảo Lộc',36),(378,'Huyện Đức Trọng',36),(379,'Huyện Di Linh',36),(380,'Huyện Đơn Dương',36),(381,'Huyện Lạc Dương',36),(382,'Huyện Đạ Huoai',36),(383,'Huyện Đạ Tẻh',36),(384,'Huyện Cát Tiên',36),(385,'Huyện Lâm Hà',36),(386,'Huyện Bảo Lâm',36),(387,'Huyện Đam Rông',36),(388,'Thành phố Lạng Sơn',37),(389,'Huyện Tràng Định',37),(390,'Huyện Bình Gia',37),(391,'Huyện Văn Lãng',37),(392,'Huyện Bắc Sơn',37),(393,'Huyện Văn Quan',37),(394,'Huyện Cao Lộc',37),(395,'Huyện Lộc Bình',37),(396,'Huyện Chi Lăng',37),(397,'Huyện Đình Lập',37),(398,'Huyện Hữu Lũng',37),(399,'Thành phố Lào Cai',38),(400,'Huyện Xi Ma Cai',38),(401,'Huyện Bát Xát',38),(402,'Huyện Bảo Thắng',38),(403,'Huyện Sa Pa',38),(404,'Huyện Văn Bàn',38),(405,'Huyện Bảo Yên',38),(406,'Huyện Bắc Hà',38),(407,'Huyện Mường Khương',38),(408,'Thị xã Tân An',39),(409,'Huyện Vĩnh Hưng',39),(410,'Huyện Mộc Hoá',39),(411,'Huyện Tân Thạnh',39),(412,'Huyện Thạnh Hoá',39),(413,'Huyện Đức Huệ',39),(414,'Huyện Đức Hoà',39),(415,'Huyện Bến Lức',39),(416,'Huyện Thủ Thừa',39),(417,'Huyện Châu Thành',39),(418,'Huyện Tân Trụ',39),(419,'Huyện Cần Đước',39),(420,'Huyện Cần Giuộc',39),(421,'Huyện Tân Hưng',39),(422,'Thành phố Nam Định',40),(423,'Huyện Mỹ Lộc',40),(424,'Huyện Xuân Trường',40),(425,'Huyện Giao Thủy',40),(426,'Huyện Ý Yên',40),(427,'Huyện Vụ Bản',40),(428,'Huyện Nam Trực',40),(429,'Huyện Trực Ninh',40),(430,'Huyện Nghĩa Hưng',40),(431,'Huyện Hải Hậu',40),(432,'Thành phố Vinh',41),(433,'Thị xã Cửa Lò',41),(434,'Huyện Quỳ Châu',41),(435,'Huyện Quỳ Hợp',41),(436,'Huyện Nghĩa Đàn',41),(437,'Huyện Quỳnh Lưu',41),(438,'Huyện Kỳ Sơn',41),(439,'Huyện Tương Dương',41),(440,'Huyện Con Cuông',41),(441,'Huyện Tân Kỳ',41),(442,'Huyện Yên Thành',41),(443,'Huyện Diễn Châu',41),(444,'Huyện Anh Sơn',41),(445,'Huyện Đô Lương',41),(446,'Huyện Thanh Chương',41),(447,'Huyện Nghi Lộc',41),(448,'Huyện Nam Đàn',41),(449,'Huyện Hưng Nguyên',41),(450,'Huyện Quế Phong',41),(451,'Thành phố Ninh Bình',42),(452,'Thị xã Tam Điệp',42),(453,'Huyện Nho Quan',42),(454,'Huyện Gia Viễn',42),(455,'Huyện Hoa Lư',42),(456,'Huyện Yên Mô',42),(457,'Huyện Kim Sơn',42),(458,'Huyện Yên Khánh',42),(459,'Thành phố Phan Rang-Tháp Chàm',43),(460,'Huyện Ninh Sơn',43),(461,'Huyện Ninh Phước',43),(462,'Huyện Bác Ái',43),(463,'Huyện Thuận Bắc',43),(464,'Huyện Ninh Hải',43),(465,'Thành phố Việt Trì',44),(466,'Thị xã Phú Thọ',44),(467,'Huyện Đoan Hùng',44),(468,'Huyện Thanh Ba',44),(469,'Huyện Hạ Hoà',44),(470,'Huyện Cẩm Khê',44),(471,'Huyện Yên Lập',44),(472,'Huyện Thanh Sơn',44),(473,'Huyện Phù Ninh',44),(474,'Huyện Lâm Thao',44),(475,'Huyện Tam Nông',44),(476,'Huyện Thanh Thủy',44),(477,'Huyện Tân Sơn',44),(478,'Thị xã Tuy Hoà',45),(479,'Huyện Đồng Xuân',45),(480,'Huyện Sông Cầu',45),(481,'Huyện Tuy An',45),(482,'Huyện Sơn Hoà',45),(483,'Huyện Sông Hinh',45),(484,'Huyện Đông Hoà',45),(485,'Huyện Phú Hoà',45),(486,'Huyện Tây Hoà',45),(487,'Thành phố Đồng Hới',46),(488,'Huyện Tuyên Hoá',46),(489,'Huyện Minh Hoá',46),(490,'Huyện Quảng Trạch',46),(491,'Huyện Bố Trạch',46),(492,'Huyện Quảng Ninh',46),(493,'Huyện Lệ Thuỷ',46),(494,'Thành phố Tam Kỳ',47),(495,'Thị xã Hội An',47),(496,'Huyện Duy Xuyên',47),(497,'Huyện Điện Bàn',47),(498,'Huyện Đại Lộc',47),(499,'Huyện Quế Sơn',47),(500,'Huyện Hiệp Đức',47),(501,'Huyện Thăng Bình',47),(502,'Huyện Núi Thành',47),(503,'Huyện Tiên Phước',47),(504,'Huyện Bắc Trà My',47),(505,'Huyện Đông Giang',47),(506,'Huyện Nam Giang',47),(507,'Huyện Phước Sơn',47),(508,'Huyện Nam Trà My',47),(509,'Huyện Tây Giang',47),(510,'Huyện Phú Ninh',47),(511,'Thành phố Quảng Ngãi',48),(512,'Huyện Lý Sơn',48),(513,'Huyện Bình Sơn',48),(514,'Huyện Trà Bồng',48),(515,'Huyện Sơn Tịnh',48),(516,'Huyện Sơn Hà',48),(517,'Huyện Tư Nghĩa',48),(518,'Huyện Nghĩa Hành',48),(519,'Huyện Minh Long',48),(520,'Huyện Mộ Đức',48),(521,'Huyện Đức Phổ',48),(522,'Huyện Ba Tơ',48),(523,'Huyện Sơn Tây',48),(524,'Huyện Tây Trà',48),(525,'Thành phố Hạ Long',49),(526,'Thị xã Cẩm Phả',49),(527,'Thị xã Uông Bí',49),(528,'Thị xã Móng Cái',49),(529,'Huyện Bình Liêu',49),(530,'Huyện Đầm Hà',49),(531,'Huyện Hải Hà',49),(532,'Huyện Tiên Yên',49),(533,'Huyện Ba Chẽ',49),(534,'Huyện Đông Triều',49),(535,'Huyện Yên Hưng',49),(536,'Huyện Hoành Bồ',49),(537,'Huyện Vân Đồn',49),(538,'Huyện Cô Tô',49),(539,'Thị xã Đông Hà',50),(540,'Thị xã Quảng Trị',50),(541,'Huyện Vĩnh Linh',50),(542,'Huyện Gio Linh',50),(543,'Huyện Cam Lộ',50),(544,'Huyện Triệu Phong',50),(545,'Huyện Hải Lăng',50),(546,'Huyện Hướng Hoá',50),(547,'Huyện Đăk Rông',50),(548,'Huyện đảo Cồn Cỏ',50),(549,'Thành phố Sóc Trăng',51),(550,'Huyện Kế Sách',51),(551,'Huyện Mỹ Tú',51),(552,'Huyện Mỹ Xuyên',51),(553,'Huyện Thạnh Trị',51),(554,'Huyện Long Phú',51),(555,'Huyện Vĩnh Châu',51),(556,'Huyện Cù Lao Dung',51),(557,'Huyện Ngã Năm',51),(558,'Huyện Châu Thành',51),(559,'Huyện Trần Đề',51),(560,'Thị xã Sơn La',52),(561,'Huyện Quỳnh Nhai',52),(562,'Huyện Mường La',52),(563,'Huyện Thuận Châu',52),(564,'Huyện Bắc Yên',52),(565,'Huyện Phù Yên',52),(566,'Huyện Mai Sơn',52),(567,'Huyện Yên Châu',52),(568,'Huyện Sông Mã',52),(569,'Huyện Mộc Châu',52),(570,'Huyện Sốp Cộp',52),(571,'Thị xã Tây Ninh',53),(572,'Huyện Tân Biên',53),(573,'Huyện Tân Châu',53),(574,'Huyện Dương Minh Châu',53),(575,'Huyện Châu Thành',53),(576,'Huyện Hoà Thành',53),(577,'Huyện Bến Cầu',53),(578,'Huyện Gò Dầu',53),(579,'Huyện Trảng Bàng',53),(580,'Thành phố Thái Bình',54),(581,'Huyện Quỳnh Phụ',54),(582,'Huyện Hưng Hà',54),(583,'Huyện Đông Hưng',54),(584,'Huyện Vũ Thư',54),(585,'Huyện Kiến Xương',54),(586,'Huyện Tiền Hải',54),(587,'Huyện Thái Thuỵ',54),(588,'Thành phố Thái Nguyên',55),(589,'Thị xã Sông Công',55),(590,'Huyện Định Hoá',55),(591,'Huyện Phú Lương',55),(592,'Huyện Võ Nhai',55),(593,'Huyện Đại Từ',55),(594,'Huyện Đồng Hỷ',55),(595,'Huyện Phú Bình',55),(596,'Huyện Phổ Yên',55),(597,'Thành phố Thanh Hoá',56),(598,'Thị xã Bỉm Sơn',56),(599,'Thị xã Sầm Sơn',56),(600,'Huyện Quan Hoá',56),(601,'Huyện Quan Sơn',56),(602,'Huyện Mường Lát',56),(603,'Huyện Bá Thước',56),(604,'Huyện Thường Xuân',56),(605,'Huyện Như Xuân',56),(606,'Huyện Như Thanh',56),(607,'Huyện Lang Chánh',56),(608,'Huyện Ngọc Lặc',56),(609,'Huyện Thạch Thành',56),(610,'Huyện Cẩm Thủy',56),(611,'Huyện Thọ Xuân',56),(612,'Huyện Vĩnh Lộc',56),(613,'Huyện Thiệu Hoá',56),(614,'Huyện Triệu Sơn',56),(615,'Huyện Nông Cống',56),(616,'Huyện Đông Sơn',56),(617,'Huyện Hà Trung',56),(618,'Huyện Hoằng Hoá',56),(619,'Huyện Nga Sơn',56),(620,'Huyện Hậu Lộc',56),(621,'Huyện Quảng Xương',56),(622,'Huyện Tĩnh Gia',56),(623,'Huyện Yên Định',56),(624,'Thành phố Huế',57),(625,'Huyện Phong Điền',57),(626,'Huyện Quảng Điền',57),(627,'Huyện Hương Trà',57),(628,'Huyện Phú Vang',57),(629,'Huyện Hương Thuỷ',57),(630,'Huyện Phú Lộc',57),(631,'Huyện Nam Đông',57),(632,'Huyện A Lưới',57),(633,'Thành phố Mỹ Tho',58),(634,'Thị xã Gò Công',58),(635,'Huyện Cái Bè',58),(636,'Huyện Cai Lậy',58),(637,'Huyện Châu Thành',58),(638,'Huyện Chợ Gạo',58),(639,'Huyện Gò Công Tây',58),(640,'Huyện Gò Công Đông',58),(641,'Huyện Tân Phước',58),(642,'Thị xã Trà Vinh',59),(643,'Huyện Càng Long',59),(644,'Huyện Cầu Kè',59),(645,'Huyện Tiểu Cần',59),(646,'Huyện Châu Thành',59),(647,'Huyện Trà Cú',59),(648,'Huyện Cầu Ngang',59),(649,'Huyện Duyên Hải',59),(650,'Thành phố Tuyên Quang',60),(651,'Huyện Na Hang',60),(652,'Huyện Chiêm Hoá',60),(653,'Huyện Hàm Yên',60),(654,'Huyện Yên Sơn',60),(655,'Huyện Sơn Dương',60),(656,'Thị xã Vĩnh Long',61),(657,'Huyện Long Hồ',61),(658,'Huyện Mang Thít',61),(659,'Huyện Bình Minh',61),(660,'Huyện Tam Bình',61),(661,'Huyện Trà Ôn',61),(662,'Huyện Vũng Liêm',61),(663,'Huyện Bình Tân',61),(664,'Thành phố Vĩnh Yên',62),(665,'Huyện Tam Dương',62),(666,'Huyện Lập Thạch',62),(667,'Huyện Vĩnh Tường',62),(668,'Huyện Yên Lạc',62),(669,'Huyện Bình Xuyên',62),(670,'Huyện Mê Linh',62),(671,'Thị xã Phúc Yên',62),(672,'Huyện Tam Đảo',62),(673,'Thành phố Yên Bái',63),(674,'Thị xã Nghĩa Lộ',63),(675,'Huyện Văn Yên',63),(676,'Huyện Yên Bình',63),(677,'Huyện Mù Cang Chải',63),(678,'Huyện Văn Chấn',63),(679,'Huyện Trấn Yên',63),(680,'Huyện Trạm Tấu',63),(681,'Huyện Lục Yên',63);
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion` (
  `promotion_id` varchar(7) NOT NULL,
  `promotion_image` varchar(200) DEFAULT NULL,
  `promotion_name` varchar(45) DEFAULT 'N/A',
  `promotion_start` varchar(45) DEFAULT 'N/A',
  `promotion_end` varchar(45) DEFAULT 'N/A',
  `promotion_status` int(11) DEFAULT NULL,
  `restaurant_id` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`promotion_id`),
  UNIQUE KEY `promotion_id_UNIQUE` (`promotion_id`),
  KEY `fk_resid_Pro_idx` (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` VALUES ('PR_0001',NULL,'Khuyến mãi mua một gà lớn tặng ly nước lớn','2016-04-06','2016-04-16',1,'NH_0001'),('PR_0002',NULL,'Khuyến mãi mua một tặng một','2016-04-06','2016-04-10',1,'NH_0001'),('PR_0003',NULL,'Khuyễn mãi thêm một combo khi mua 2 gà rán','2016-04-06','2016-04-11',1,'NH_0002'),('PR_0004','upload/promotion/InuYasha.full.1706079.jpg','Hà Nội','2016-04-27','2016-04-27',0,'NH_0001'),('PR_0005','upload/promotion/secondarytile.png','Việt Nam','2016-04-27','2016-04-28',0,'NH_0001'),('PR_0006','upload/promotion/13139002_1303159379697398_1038041266085345626_n.jpg','Tặng 20','2016-05-24','2016-05-26',1,'NH_0001');
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `reservation_id` varchar(7) NOT NULL,
  `reservation_customername` varchar(45) DEFAULT 'N/A',
  `reservation_customerphone` varchar(45) DEFAULT 'N/A',
  `reservation_content` varchar(150) DEFAULT 'N/A',
  `reservation_time` datetime DEFAULT NULL,
  `reservation_status` varchar(45) DEFAULT 'N/A',
  PRIMARY KEY (`reservation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant` (
  `restaurant_id` varchar(7) NOT NULL,
  `restaurant_image` varchar(200) DEFAULT NULL,
  `restaurant_name` varchar(45) DEFAULT 'N/A',
  `restaurant_address` varchar(75) DEFAULT 'N/A',
  `city_id` int(11) DEFAULT NULL,
  `district_id` int(11) DEFAULT NULL,
  `restaurant_email` varchar(45) DEFAULT 'N/A',
  `restaurant_phone` varchar(45) DEFAULT 'N/A',
  `restaurant_username` varchar(45) DEFAULT 'N/A',
  `restaurant_status` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`restaurant_id`),
  UNIQUE KEY `restaurant_id_UNIQUE` (`restaurant_id`),
  KEY `fk_username_idx` (`restaurant_username`),
  KEY `fk_city_idx` (`city_id`),
  KEY `fk_district_idx` (`district_id`),
  KEY `fk_category_idx` (`category_id`),
  KEY `fk_country_idx` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES ('NH_0001','upload/restaurant_avatar/nhahangthanhnien.jpg','Nhà hàng Thanh Niên','11 Nguyễn Văn Chiêm',1,11,'thanhnienrestaurant@yahoo.com','0838225909','nhthanhnien',1,1,4),('NH_0002','upload/restaurant_avatar/bannercongu.jpg','Nhà Hàng Cổ Ngư','278 VÕ THỊ SÁU, P. 7',1,3,'congurest@gmail.com','083932 5802','nhcongu',1,3,1),('NH_0003','upload/restaurant_avatar/hanoideli.jpg','Nhà Hàng Hà Nội Deli','51 Lý Thường Kiệt',2,26,'hanoideli@gmail.com','0904461151','nhhanoideli',1,3,14),('NH_0004','upload/restaurant_avatar/hat-Chef-512.png','Nhà Hàng Koreana','106 Linh Lang, P. Cống Vị',2,25,'koreana@vnn.vn','0838313907','nhkoreana',1,1,5),('NH_0005','upload/restaurant_avatar/hat-Chef-512.png','Nhà Hàng Cá Ngựa','201 Trường Chinh',2,28,'info@ngocphatdalathotel.com','0466627755','nhcangua',1,1,1),('NH_0006','upload/restaurant_avatar/hat-Chef-512.png',' NHÀ HÀNG TIỆC CƯỚI HOÀNG LONG II','24 Khuông Việt, P. Phú Trung',1,15,'info@tieccuoihoanglong.com.vn','0838604477','nhhoanglong2',1,2,1),('NH_0007','upload/restaurant_avatar/hat-Chef-512.png','Nhà Hàng Hai Con Bò','N/A',NULL,NULL,'haiconbo@gmail.com','0912426987','N/A',2,NULL,NULL);
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','e10adc3949ba59abbe56e057f20f883e',1),('nhcangua','e10adc3949ba59abbe56e057f20f883e',1),('nhcongu','e10adc3949ba59abbe56e057f20f883e',1),('nhhanoideli','e10adc3949ba59abbe56e057f20f883e',1),('nhhoanglong2','e10adc3949ba59abbe56e057f20f883e',1),('nhkoreana','e10adc3949ba59abbe56e057f20f883e',1),('nhthanhnien','e10adc3949ba59abbe56e057f20f883e',1),('user','e10adc3949ba59abbe56e057f20f883e',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `user_role_id_UNIQUE` (`user_role_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_user_idx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'admin','ROLE_ADMIN'),(2,'user','ROLE_USER'),(3,'nhhanoideli','ROLE_USER'),(4,'nhthanhnien','ROLE_USER'),(5,'nhcongu','ROLE_USER'),(6,'nhkoreana','ROLE_USER'),(7,'nhcangua','ROLE_USER'),(8,'nhhoanglong2','ROLE_USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-16 13:38:26
