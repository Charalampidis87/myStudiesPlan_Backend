-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: mystudiesplandb
-- Version: 2022-2023
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `advisor`
--
DROP SCHEMA IF EXISTS `mystudiesplandb`;

CREATE SCHEMA IF NOT EXISTS `mystudiesplandb`;

USE `mystudiesplandb`;

DROP TABLE IF EXISTS `advisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `advisor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `active` int(11) NOT NULL,
  `active_year` int(11) NOT NULL,
  `active_examination` int(11) NOT NULL,
  `years` int(11) NOT NULL DEFAULT '4',
  PRIMARY KEY (`id`),
  KEY `advisor_user_fk_idx` (`user`),
  CONSTRAINT `advisor_user_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advisor`
--

-- LOCK TABLES `advisor` WRITE;
-- /*!40000 ALTER TABLE `advisor` DISABLE KEYS */;
-- INSERT INTO `advisor` VALUES (1,2,1,2,0,4),(2,2,0,1,0,4);
-- /*!40000 ALTER TABLE `advisor` ENABLE KEYS */;
-- UNLOCK TABLES;LOCK TABLES `advisor` WRITE;
-- /*!40000 ALTER TABLE `advisor` DISABLE KEYS */;
-- INSERT INTO `advisor` VALUES (1,2,1,2,0,4),(2,2,0,1,0,4);
-- /*!40000 ALTER TABLE `advisor` ENABLE KEYS */;
-- UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `ects` int(11) NOT NULL,
  `semester` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `direction` varchar(45) NOT NULL,
  `theory` int(11) DEFAULT NULL,
  `extra` int(11) DEFAULT NULL,
  `lab` int(11) DEFAULT NULL,
  `code` varchar(45) NOT NULL,
  `double_exam` tinyint(4) NOT NULL DEFAULT '0',
  `offered` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (7,'Γραμμική Άλγεβρα',6,1,'ΥΜ','',3,2,0,'Κ03',0,1),(8,'Διακριτά Μαθηματικά',7,1,'ΥΜ','',4,2,0,'Κ09',0,1),(9,'Εισαγωγή στον Προγραμματισμό',7,1,'ΥΜ','',3,1,2,'Κ04',0,1),(10,'Λογική Σχεδίαση',6,1,'ΥΜ','',3,1,0,'Κ02',0,1),(11,'Ανάλυση Ι',8,2,'ΥΜ','',4,2,0,'Κ01',0,1),(12,'Ηλεκτρομαγνητισμός, Οπτική, Σύγχρονη Φυσική',8,2,'ΥΜ','',4,4,0,'Κ12',0,1),(13,'Δομές Δεδομένων και Τεχνικές Προγραμματισμού',7,2,'ΥΜ','',3,1,1,'Κ08',0,1),(14,'Αρχιτεκτονική Υπολογιστών Ι',7,2,'ΥΜ','',3,1,1,'Κ14',0,1),(15,'Ανάλυση ΙΙ',8,3,'ΥΜ','',4,2,0,'Κ06',0,1),(16,'Πιθανότητες και Στατιστική',6,3,'ΥΜ','',3,1,0,'Κ13',0,1),(17,'Αντικειμενοστραφής Προγραμματισμός ',8,3,'ΥΜ','',3,1,2,'Κ10',0,1),(18,'Σήματα και Συστήματα',6,3,'ΥΜ','',3,1,0,'Κ11',0,1),(19,'Αλγόριθμοι και Πολυπλοκότητα',8,4,'ΥΜ','',4,2,0,'Κ17',0,1),(20,'Σχεδίαση και Χρήση Βάσεων Δεδομένων',7,4,'ΥΜ','',3,1,1,'Κ29',0,1),(21,'Συστήματα Επικοινωνιών',7,4,'ΥΜ','',3,1,1,'Κ21',0,1),(22,'Δίκτυα Επικοινωνιών Ι',6,4,'ΥΜ','',3,1,0,'Κ16',0,1),(23,'Λειτουργικά Συστήματα',8,5,'ΥΜ','',4,0,0,'Κ22',0,1),(24,'Προγραμματισμός Συστήματος',8,6,'ΥΜ','',4,0,0,'Κ24',0,1),(25,'Εργαστήριο Λογικής Σχεδίασης',2,1,'ΕΡ','',0,0,2,'Κ02ε',0,1),(26,'Εργαστήριο Κυκλωμάτων και Συστημάτων',2,3,'ΕΡ','',0,0,2,'Κ11ε',0,1),(27,'Εργαστήριο Δικτύων Επικοινωνιών Ι',2,4,'ΕΡ','',0,0,2,'Κ16ε',0,1),(28,'Αριθμητική Ανάλυση',6,5,'ΕΥΜ','Α',3,1,1,'Κ15',0,1),(29,'Υλοποίηση Συστημάτων Βάσεων Δεδομένων',6,5,'ΕΥΜ','Α',3,1,0,'Κ18',0,1),(30,'Αρχιτεκτονική Υπολογιστών ΙΙ',6,5,'ΕΥΜ','Β',3,1,1,'Κ30',0,1),(31,'Δίκτυα Επικοινωνιών ΙΙ',6,5,'ΕΥΜ','Β',3,1,1,'Κ33',0,1),(32,'Ψηφιακή Επεξεργασία Σήματος',6,5,'ΕΥΜ','Β',4,0,1,'Κ32',0,1),(33,'Μαθηματικά Πληροφορικής',6,6,'ΕΥΜ','Α',4,1,0,'Κ20α',0,1),(34,'Θεωρία Υπολογισμού',6,6,'ΕΥΜ','Α',3,1,0,'Κ25',0,1),(35,'Μεταγλωττιστές',6,6,'ΕΥΜ','Α',4,1,0,'Κ31',0,1),(36,'Ηλεκτρονική',6,6,'ΕΥΜ','Β',3,1,0,'Κ19',0,1),(37,'Διαχείριση Δικτύων',6,6,'ΕΥΜ','Β',3,1,0,'Κ34',0,1),(38,'Θεωρία Πληροφορίας και Κωδίκων',6,6,'ΕΥΜ','Β',3,1,0,'Κ35',0,1),(39,'Ανάπτυξη Λογισμικού για Αλγοριθμικά Προβλήματα',8,7,'Project','Α',1,0,5,'Κ23γ',0,1),(40,'Ανάπτυξη Λογισμικού για Πληροφοριακά Συστήματα',8,7,'Project','Α',1,0,5,'Κ23α',0,1),(41,'Ανάπτυξη Λογισμικού για Συστήματα Δικτύων και Τηλεπικοινωνιών',8,7,'Project','Β',3,0,3,'Κ23β',0,1),(42,'Ανάπτυξη Υλικού-Λογισμικού για Ενσωματωμένα Συστήματα',8,8,'Project','Β',3,0,3,'Κ23δ',0,1),(43,'Εισαγωγή στην Πληροφορική και στις Τηλεπικοινωνίες',2,1,'ΓΠ','',2,0,0,'ΓΠ7',0,1),(44,'Δομή και Θεσμοί της Ευρωπαϊκής Ένωσης',2,7,'ΓΠ','',2,0,0,'ΓΠ3',0,1),(45,'Διοίκηση Έργων και Τεχνικές Παρουσίασης και Συγγραφή Επιστημονικών Εκθέσεων',2,8,'ΓΠ','',2,0,0,'ΓΠ5',0,1),(46,'Αρχές Γλωσσών Προγραμματισμού',6,5,'ΠΜ','Α',3,1,0,'ΘΠ01',0,1),(47,'Γραφικά Ι',6,5,'ΠΜ','ΑΒ',3,0,1,'ΘΠ02',0,1),(48,'Τεχνητή Νοημοσύνη I',6,5,'ΠΜ','Α',3,1,0,'ΥΣ02',0,1),(49,'Σχεδίαση Ψηφιακών Συστημάτων - VHDL',6,5,'ΠΜ','Β',3,0,1,'ΥΣ03',0,1),(50,'Παράλληλα Συστήματα',6,5,'ΠΜ','ΑΒ',3,1,0,'ΘΠ04',0,1),(51,'Κύματα, Κυματοδηγοί, Κεραίες',6,5,'ΠΜ','Β',3,0,1,'ΕΠ05',0,1),(52,'Τηλεπικοινωνιακά Δίκτυα',6,5,'ΠΜ','Β',3,0,1,'ΕΠ20',0,1),(53,'Εφαρμοσμένα Μαθηματικά',6,2,'ΠΜ','Β',3,1,0,'Κ20β',0,1),(54,'Προηγμένα Θέματα Αλγορίθμων',6,7,'ΠΜ','Α',3,1,0,'ΘΠ12',0,1),(55,'Επιστημ/κοί Υπολογισμοί (Αριθμητική Γραμμική Άλγεβρα)',6,6,'ΠΜ','Α',3,1,0,'ΘΠ03',0,1),(56,'Θεωρία Αριθμών',6,6,'ΠΜ','',3,1,0,'ΘΠ08',0,1),(57,'Τεχνικές Εξόρυξης Δεδομένων',6,6,'ΠΜ','Α',4,0,0,'ΥΣ11',0,1),(58,'Λογικός Προγραμματισμός',6,6,'ΠΜ','Α',3,1,0,'ΥΣ05',0,1),(59,'Ανάλυση/Σχεδίαση Συστημάτων Λογισμικού',6,6,'ΠΜ','Α',3,'0',1,'ΥΣ04',0,1),(60,'Τεχνολογίες Εφαρμογών Διαδικτύου',6,6,'ΠΜ','ΑΒ',2,1,1,'ΥΣ14',0,1),(61,'Εργαστήριο Ηλεκτρονικής',6,6,'ΠΜ','Β',0,1,3,'Κ19ε',0,1),(62,'Ασύρματα Δίκτυα Αισθητήρων',6,6,'ΠΜ','Β',3,1,0,'ΥΣ18',0,1),(63,'Επεξεργασία Στοχαστικών Σημάτων',6,6,'ΠΜ','Β',3,1,0,'ΕΠ07',0,1),(64,'Αναγνώριση Προτύπων – Μηχανική Μάθηση',6,6,'ΠΜ','ΑΒ',3,1,0,'ΕΠ08',0,1),(65,'Αλγοριθμική Επιχειρησιακή Έρευνα',6,7,'ΠΜ','ΑΒ',3,1,0,'ΘΠ09',0,1),(66,'Κρυπτογραφία',6,8,'ΠΜ','Α',3,1,0,'ΘΠ05',0,1),(67,'Γραφικά ΙΙ',4,7,'ΠΜ','',3,0,0,'ΘΠ07',0,0),(68,'Θεωρία Γραφημάτων',6,8,'ΓΠ','Α',3,1,0,'ΘΠ10',0,1),(69,'Προηγμένοι Επιστημονικοί Υπολογισμοί',6,7,'ΠΜ','',3,1,0,'ΘΠ18',0,1),(70,'Επικοινωνία Ανθρώπου Μηχανής',6,7,'ΠΜ','Α',3,1,0,'ΥΣ08',0,1),(71,'Αλγόριθμοι Βιοπληροφορικής',6,7,'ΠΜ','',3,1,0,'ΘΠ17',0,0),(72,'Πληροφοριακά Συστήματα',6,7,'ΠΜ','Α',2,2,0,'ΥΣ07',0,0),(73,'Ειδικά Θέματα Υπολογιστικών Συστημάτων και Εφαρμογών',4,7,'ΠΜ','',3,0,0,'ΥΣ16',0,1),(74,'Ηλεκτρονική Διακυβέρνηση',4,7,'ΠΜ','',3,0,0,'ΥΣ17',0,1),(75,'Συστήματα ΨΕΣ σε Πραγματικό Χρόνο',6,7,'ΠΜ','Β',2,0,2,'ΕΠ11',0,1),(76,'Οπτικές Επικοινωνίες και Οπτικά Δίκτυα',6,7,'ΠΜ','Β',3,1,0,'ΕΠ16',0,1),(77,'Ψηφιακές Επικοινωνίες',6,7,'ΠΜ','Β',3,1,0,'ΕΠ04',0,1),(78,'Ασύρματες Ζεύξεις',6,8,'ΠΜ','',3,0,1,'ΕΠ13',0,1),(79,'Υπολογιστική Πολυπλοκότητα',6,7,'ΠΜ','Α',3,1,0,'ΘΠ20',0,1),(80,'Υπολογιστική Γεωμετρία',6,8,'ΠΜ','Α',3,1,0,'ΘΠ11',0,1),(81,'Παράλληλοι Αλγόριθμοι',6,8,'ΠΜ','',3,1,0,'ΘΠ19',0,1),(82,'Γραμμική & Μη Γραμμική Βελτιστοποίηση',4,8,'ΠΜ','',3,0,0,'ΘΠ14',0,0),(83,'Τεχνητή Νοημοσύνη ΙΙ',6,7,'ΠΜ','Α',3,1,0,'ΥΣ19',0,1),(84,'Επεξεργασία Ομιλίας και Φυσικής Γλώσσας',6,8,'ΠΜ','',3,0,1,'ΕΠ19',0,1),(85,'Προστασία και Ασφάλεια Υπολογ/κών Συστημάτων',6,8,'ΠΜ','Α',3,1,0,'ΥΣ13',0,1),(86,'Τεχνολογία Λογισμικού',6,8,'ΠΜ','Α',3,1,0,'ΥΣ09',0,1),(87,'Σχεδίαση VLSI Κυκλωμάτων',6,7,'ΠΜ','Β',3,0,1,'ΕΠ01',0,1),(88,'Φωτονική',6,8,'ΠΜ','',3,0,1,'ΕΠ12',0,1),(89,'Επεξεργασία Εικόνας',6,8,'ΠΜ','Β',3,0,1,'ΕΠ10',0,1),(90,'Μουσική Πληροφορική',4,8,'ΠΜ','Β',2,1,0,'ΕΠ21',0,1),(91,'Ανάλυση Εικόνας & Τεχνητή Όραση',6,8,'ΠΜ','',3,0,1,'ΕΠ23',0,0),(92,'Διδακτική της Πληροφορικής',6,7,'ΠΜ','',2,0,2,'ΥΣ10',0,1),(93,'Ιστορία της Πληροφορικής και των Τηλεπικοινωνιών',4,8,'ΠΜ','',3,0,0,'ΥΣ20',0,1),(94,'Καινοτομία και Επιχειρηματικότητα',4,8,'ΠΜ','',2,1,0,'ΥΣ12',0,1),(95,'Συστήματα Κινητών και Προσ/κών Επικοινωνιών',6,7,'ΠΜ','Β',3,1,0,'ΕΠ18',0,1),(96,'Μικροοικονομική Ανάλυση',4,8,'ΠΜ','',3,0,0,'ΕΠ24',0,1),(97,'Ειδ. Θέματα Θεωρητικής Πληροφορικής',4,7,'ΠΜ','',3,0,0,'ΘΠ16',0,1),(98,'Αλγόριθμοι-Θεμελιώσεις Μηχανικής Μάθησης',6,6,'ΠΜ','Α',4,0,0,'ΘΠ22',0,1),(99,'Ειδ. Θέμ. Επικοινωνιών και Επεξεργ. Σήματος',4,7,'ΠΜ','',3,0,0,'ΕΠ22',0,1),(100,'Πτυχιακή Εργασία Ι',8,7,'ΠΕ','',0,0,0,'-',0,1),(101,'Πτυχιακή Εργασία ΙΙ',8,8,'ΠΕ','',0,0,0,'-',0,1),(102,'Πρακτική Άσκηση Ι',8,7,'ΠΑ','',0,0,0,'-',0,1),(103,'Πρακτική Άσκηση ΙΙ',8,8,'ΠΑ','',0,0,0,'-',0,1),(104,'Ενισχυτική Μηχανική Μάθηση και Στοχαστικά Παίγνια',6,7,'ΠΜ','',4,0,0,'ΕΠ25',0,1),(105,'Τεχνολογίες της Πληροφορίας και των Επικοινωνιών στη Μάθηση (Πληροφορική & Εκπαίδευση)',6,7,'ΠΜ','',2,0,2,'ΥΣ15',0,1),(106,'Ψηφιακή Προσβασιμότητα & Υποστηρικτικές Τεχνολογίες Πληροφορικής',6,7,'ΠΜ','',2,0,2,'ΥΣ22',0,1),(107,'Σημασιολογία Γλωσσών Προγραμματισμού',6,8,'ΠΜ','Α',4,0,0,'ΘΠ21',0,1),(108,'Σχολική Τάξη και Μικροδιδασκαλία',6,8,'ΠΜ','',2,0,2,'ΥΣ21',0,1),(109,'Υπολογιστική Θεωρία Μηχανικής Μάθησης',6,8,'ΠΜ','Α',4,0,0,'ΘΠ23',0,1);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_has_lab_professor`
--

DROP TABLE IF EXISTS `course_has_lab_professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course_has_lab_professor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course` int(11) NOT NULL,
  `lab_professor` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `chlp_course_fk_idx` (`course`),
  CONSTRAINT `chlp_course_fk` FOREIGN KEY (`course`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping data for table `course_has_lab_professor`
--

LOCK TABLES `course_has_lab_professor` WRITE;
/*!40000 ALTER TABLE `course_has_lab_professor` DISABLE KEYS */;
INSERT INTO `course_has_lab_professor` VALUES (2,13,'Σαράντης Πασκαλής'),(3,17,'Σπυρίδων Ξεργιάς'),(4,17,'Αθανασία Κολοβού'),(5,9,'Ιωάννης Χαμόδρακας'),(6,23,'Κωνσταντίνος Κολομβάτσος');
/*!40000 ALTER TABLE `course_has_lab_professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_has_prerequisites`
--

DROP TABLE IF EXISTS `course_has_prerequisites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course_has_prerequisites` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course` int(11) NOT NULL,
  `prerequisite` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `chp_prerequisite_fk_idx` (`prerequisite`),
  KEY `chp_course_fk_idx` (`course`),
  CONSTRAINT `chp_course_fk` FOREIGN KEY (`course`) REFERENCES `course` (`id`),
  CONSTRAINT `chp_prerequisite_fk` FOREIGN KEY (`prerequisite`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_has_prerequisites`
--

LOCK TABLES `course_has_prerequisites` WRITE;
/*!40000 ALTER TABLE `course_has_prerequisites` DISABLE KEYS */;
INSERT INTO `course_has_prerequisites` VALUES (1,23,13),(2,24,13),(3,30,14),(4,32,18),(5,31,22),(6,33,8),(7,35,13),(8,37,22),(9,39,19),(10,41,22),(13,40,29),(14,46,13),(15,48,13),(16,49,10),(17,51,12),(18,52,22),(21,54,19),(23,56,8),(24,57,29),(26,62,23),(27,63,18),(28,64,16),(29,65,19),(30,66,19),(31,67,47),(32,68,33),(35,71,19),(36,72,20),(37,75,18),(38,76,12),(39,77,21),(40,78,12),(41,79,34),(45,83,48),(46,82,48),(47,84,18),(48,85,23),(49,87,36),(50,88,36),(53,91,32),(54,95,22),(55,42,49),(56,47,13),(57,60,17),(58,70,20),(59,74,17),(60,108,92),(61,108,105),(62,86,17);
/*!40000 ALTER TABLE `course_has_prerequisites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_has_professor`
--

DROP TABLE IF EXISTS `course_has_professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course_has_professor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course` int(11) NOT NULL,
  `professor` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `chp_course_fk_idx` (`course`),
  CONSTRAINT `chpr_course_fk` FOREIGN KEY (`course`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_has_professor`
--

LOCK TABLES `course_has_professor` WRITE;
/*!40000 ALTER TABLE `course_has_professor` DISABLE KEYS */;
INSERT INTO `course_has_professor` VALUES (1,7,'Α. Γιαννοπούλου'),(2,8,'Χρ. Τζάμος'),(3,8,'Ι. Χαμόδρακας'),(4,9,'Π. Σταματόπουλος'),(5,9,'Ι. Χαμόδρακας'),(6,9,'Μ. Κυριακάκος'),(7,9,'Ν. Περδικοπάνης'),(8,10,'Α. Πασχάλης'),(9,10,'Δ. Βασιλόπουλος'),(10,43,'Μ. Ρούσσου'),(11,25,'Α. Πασχάλης'),(12,25,'Δ. Βασιλόπουλος'),(13,11,'Δ. Χελιώτης'),(14,17,'Δ. Αλεξανδράκης'),(15,17,'Α. Κολοβού'),(16,17,'Σ. Ξεργιάς'),(17,17,'Ν. Περδικαπάνης'),(18,26,'Αλ. Πίνο'),(19,16,'Δ. Αχλιόπτας'),(20,18,'Ι. Παναγάκης'),(21,18,'Α. Λυγίζου'),(22,28,'Φ. Τζαφέρης'),(23,28,'Μ. Λουκά'),(24,46,'Π. Ροντογιάννης'),(25,30,'Δ. Γκιζόπουλος'),(26,30,'Σ. Ξεργιάς'),(27,47,'Θ. Θεοχάρης'),(28,47,'Μ. Κυριακάκος'),(29,31,'Κ. Χριστοδουλόπουλος'),(30,27,'Ι. Σταυρακάκης'),(31,27,'Α. Βάιος'),(32,51,'Α. Τσίπουρας'),(33,23,'Ε. Χατζηευθυμιάδης'),(34,23,'Σ. Πασκαλής'),(35,50,'Β. Καρακώστας'),(36,49,'Α. Πασχάλης'),(37,49,'Β. Βασιλόπουλος'),(38,48,'Μ. Κουμπαράκης'),(39,52,'Δ. Βαρουτάς'),(40,52,'Δ.Κατσιάνης'),(41,29,'Δ. Γουνόπουλος'),(42,29,'Ν. Περδικοπάνης'),(43,32,'Γ. Αλεξανδρόπουλος'),(44,32,'Α. Λυγίζου'),(45,65,'Β. Ζησιμόπουλος'),(46,39,'Ι. Εμίρης'),(47,39,'Ι. Χαμόδρακας'),(48,41,'Α. Αλωνιστιώτη'),(49,41,'Π. Μπαλαούρας'),(50,40,'Ι. Ιωαννίδης'),(51,40,'Σ. Πασκαλής'),(52,92,'Α. Γόγουλου'),(53,92,'Λ. Χαλάτση'),(54,44,'Ι. Τολίδης'),(55,99,'Δ. Συβρίδης'),(56,104,'Ν. Καλουπτσίδης'),(57,70,'Μ. Ρούσσου'),(58,70,'Α. Κολοβού'),(59,56,'Π. Ρούπα'),(60,76,'Γ. Κανέλλος'),(61,69,'Ν. Μισυρλής'),(62,69,'Φ. Τζαφέρης'),(63,54,'Ο. Φουρτουνέλλη'),(64,95,'Ν. Πασσάς'),(65,75,'Α. Πίνο'),(66,87,'Θ. Νίκας'),(67,48,'Μ. Κουμπαράκης'),(68,83,'Μ. Κουμπαράκης'),(69,105,'Α. Γόγουλου'),(70,105,'Λ. Χαλάτση'),(71,79,'Α. Γιαννοπούλου'),(72,77,'Π. Μαθιόπουλος'),(73,106,'Γ. Κουρουπέτρογλου'),(74,106,'Α. Πίνο'),(75,11,'Π. Δοδός'),(76,11,'Μ. Λουκά'),(77,14,'Δ. Γκιζόπουλος'),(78,14,'Σ. Ξεργιάς'),(79,13,'Μ. Κουμπαράκης'),(80,13,'Κ. Χατζηκοκολάκης'),(81,13,'Σ. Πασκαλής'),(82,13,'Μ. Κυριακάκος'),(83,12,'Α. Τσίπουρας'),(84,53,'Μ. Λουκά'),(85,19,'Β. Ζησιμόπουλος'),(86,19,'Ι. Εμίρης'),(87,19,'Ο. Φουρτουνέλλη'),(88,22,'Ι. Σταυρακάκης'),(89,22,'Α. Βάιος'),(90,27,'Ι. Σταυρακάκης'),(91,27,'Α. Βάιος'),(92,21,'Γ. Αλεξανδρόπουλος'),(93,21,'Α. Λυγίζου'),(94,20,'Ι. Ιωαννίδης'),(95,20,'Μ. Ρούσσου'),(96,20,'Μ. Κυριακάκος'),(97,20,'Α. Κολοβού'),(98,64,'Ι. Παναγάκης'),(99,64,'Ν. Περδικοπάνης'),(100,59,'Δ. Αλεξανδράκης'),(101,97,'Δ. Αχλιόπτας'),(102,62,'Ε. Χατζηευθυμιάδης'),(103,37,'Α. Αλωνιστιώτη'),(104,99,'Π. Μπαλαούρας'),(105,99,'Ν. Πασσάς'),(106,63,'Γ. Αλεξανδρόπουλος'),(107,55,'Φ. Τζαφέρης'),(108,61,'Θ. Νίκας'),(109,36,'Δ. Συβρίδης'),(110,38,'Ν. Καλουπτσίδης'),(111,34,'Π. Ροντογιάννης'),(112,58,'Π. Σταματόπουλος'),(113,33,'Σ. Κολλιόπουλος'),(114,33,'Ο. Φουρτουνέλλη'),(115,35,'Θ. Ανδρούτσος'),(116,24,'Ι. Ρουσσοπούλου'),(117,24,'Α. Ντούλας'),(118,57,'Δ. Γουνόπουλος'),(119,57,'Α. Κολοβού'),(120,60,'Ι. Χαμόδρακας'),(121,42,'Η. Μανωλάκος'),(122,78,'Ν. Πασσάς'),(123,45,'Ι. Τολίδης'),(124,99,'Α. Τσίπουρας'),(125,73,'Δ. Γκιζόπουλος'),(126,73,'Β. Καρακώστας'),(127,89,'Σ. Καζάζης'),(128,68,'Σ. Κολλιόπουλος'),(129,68,'Ο. Φουρτουνέλλη'),(130,93,'Α. Τύμπας'),(131,94,'Ι. Τολίδης'),(132,66,'Κ. Χατζηκοκολάκης'),(133,96,'Δ. Βαρουτάς'),(134,96,'Δ. Κατσιάνης'),(135,90,'Σ. Καζάζης'),(136,81,'Φ. Τζαφέρης'),(137,81,'Μ. Λουκά'),(138,85,'Κ. Χατζηκοκολάκης'),(139,97,'Π. Ροντογιάννης'),(140,108,'Α. Γόγουλου'),(141,86,'Ι. Σαϊδης'),(142,80,'Π. Ρούπα'),(143,109,'Χρ. Τζάμος'),(144,88,'Γ. Κανέλλος');
/*!40000 ALTER TABLE `course_has_professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_has_recommended`
--

DROP TABLE IF EXISTS `course_has_recommended`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course_has_recommended` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course` int(11) NOT NULL,
  `recommended` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `chr_course_fk_idx` (`course`),
  KEY `chr_recommended_fk_idx` (`recommended`),
  CONSTRAINT `chr_course_fk` FOREIGN KEY (`course`) REFERENCES `course` (`id`),
  CONSTRAINT `chr_recommended_fk` FOREIGN KEY (`recommended`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_has_recommended`
--

LOCK TABLES `course_has_recommended` WRITE;
/*!40000 ALTER TABLE `course_has_recommended` DISABLE KEYS */;
INSERT INTO `course_has_recommended` VALUES (4,13,9),(5,15,11),(6,14,10),(7,17,9),(8,18,11),(9,19,13),(10,19,8),(11,20,13),(12,21,18),(13,22,16),(14,23,14),(15,24,14),(16,28,11),(17,28,7),(18,29,20),(20,34,19),(21,35,14),(22,36,26),(24,39,34),(25,40,20),(26,41,31),(27,42,14),(29,49,14),(30,50,14),(31,58,13),(32,38,16),(33,59,17),(34,55,7),(35,69,28),(36,81,19),(37,80,19);
/*!40000 ALTER TABLE `course_has_recommended` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_has_specialization`
--

DROP TABLE IF EXISTS `course_has_specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course_has_specialization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course` int(11) NOT NULL,
  `specialization` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `chs_course_fk_idx` (`course`),
  CONSTRAINT `chs_course_fk` FOREIGN KEY (`course`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_has_specialization`
--

LOCK TABLES `course_has_specialization` WRITE;
/*!40000 ALTER TABLE `course_has_specialization` DISABLE KEYS */;
INSERT INTO `course_has_specialization` VALUES (1,28,1,'Υποχρεωτικό'),(2,29,2,'Υποχρεωτικό'),(3,29,3,'Υποχρεωτικό'),(4,33,1,'Υποχρεωτικό'),(5,34,2,'Υποχρεωτικό'),(6,35,3,'Υποχρεωτικό'),(7,35,4,'Βασικό'),(8,30,3,'Βασικό'),(9,30,4,'Υποχρεωτικό'),(10,31,5,'Υποχρεωτικό'),(11,32,6,'Υποχρεωτικό'),(12,36,4,'Υποχρεωτικό'),(13,37,5,'Υποχρεωτικό'),(14,38,6,'Υποχρεωτικό'),(15,53,6,'Βασικό'),(16,46,1,'Βασικό'),(17,46,2,'Βασικό'),(18,47,1,'Βασικό'),(19,47,6,'Βασικό'),(20,51,5,'Βασικό'),(21,50,3,'Βασικό'),(22,50,4,'Βασικό'),(23,49,4,'Βασικό'),(24,48,2,'Βασικό'),(25,48,3,'Βασικό'),(26,52,5,'Βασικό'),(27,98,1,'Βασικό'),(28,98,2,'Βασικό'),(29,64,1,'Βασικό'),(30,64,2,'Βασικό'),(31,64,6,'Βασικό'),(32,59,3,'Βασικό'),(33,62,4,'Βασικό'),(34,62,5,'Βασικό'),(35,63,5,'Βασικό'),(36,63,6,'Βασικό'),(37,55,1,'Βασικό'),(38,61,4,'Βασικό'),(39,58,2,'Βασικό'),(40,57,2,'Βασικό'),(41,60,3,'Βασικό'),(42,60,5,'Βασικό'),(43,65,1,'Βασικό'),(44,65,2,'Βασικό'),(45,65,6,'Βασικό'),(46,70,1,'Βασικό'),(47,70,2,'Βασικό'),(48,76,4,'Βασικό'),(49,76,5,'Βασικό'),(50,72,3,'Βασικό'),(51,54,1,'Βασικό'),(52,95,5,'Βασικό'),(53,75,4,'Βασικό'),(54,75,6,'Βασικό'),(55,87,4,'Βασικό'),(56,48,2,'Βασικό'),(57,83,2,'Βασικό'),(58,79,1,'Βασικό'),(59,77,5,'Βασικό'),(60,89,6,'Βασικό'),(61,68,1,'Βασικό'),(62,66,1,'Βασικό'),(63,90,6,'Βασικό'),(64,85,3,'Βασικό'),(65,107,1,'Βασικό'),(66,107,2,'Βασικό'),(67,86,3,'Βασικό'),(68,80,1,'Βασικό'),(69,109,1,'Βασικό'),(70,109,2,'Βασικό');
/*!40000 ALTER TABLE `course_has_specialization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_is_passed`
--

DROP TABLE IF EXISTS `course_is_passed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course_is_passed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `course` int(11) NOT NULL,
  `examination` int(11) NOT NULL,
  `grade` decimal(11,2) NOT NULL,
  `year` int(11) NOT NULL,
  `available` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `cip_user_fk_idx` (`user`),
  KEY `cip_course_fk_idx` (`course`),
  CONSTRAINT `cip_course_fk` FOREIGN KEY (`course`) REFERENCES `course` (`id`),
  CONSTRAINT `cip_user_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_is_passed`
--

LOCK TABLES `course_is_passed` WRITE;
/*!40000 ALTER TABLE `course_is_passed` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_is_passed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `declared`
--

DROP TABLE IF EXISTS `declared`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `declared` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `course` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `advisor` int(11) NOT NULL,
  `gradegoal` decimal(11,2) DEFAULT NULL,
  `examination` int(11) NOT NULL,
  `grade` decimal(11,2) DEFAULT NULL,
  `available` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `declared_user_fk_idx` (`user`),
  KEY `declared_course_fk_idx` (`course`),
  KEY `declared_advisor_fk_idx` (`advisor`),
  CONSTRAINT `declared_advisor_fk` FOREIGN KEY (`advisor`) REFERENCES `advisor` (`id`),
  CONSTRAINT `declared_course_fk` FOREIGN KEY (`course`) REFERENCES `course` (`id`),
  CONSTRAINT `declared_user_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `declared`
--

LOCK TABLES `declared` WRITE;
/*!40000 ALTER TABLE `declared` DISABLE KEYS */;
/*!40000 ALTER TABLE `declared` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45),
  `surname` varchar(45),
  `registration_num` varchar(45),
  `email` varchar(45),
  `registration_year` varchar(45) DEFAULT NULL,
  `direction` varchar(45) DEFAULT NULL,
  `username` varchar(45),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-19 17:39:07
