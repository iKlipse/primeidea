CREATE DATABASE  IF NOT EXISTS `ipdb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `ipdb`;
-- MySQL dump 10.13  Distrib 5.6.13, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: ipdb
-- ------------------------------------------------------
-- Server version	5.5.36

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
-- Table structure for table `ip_allocation`
--

DROP TABLE IF EXISTS `ip_allocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_allocation` (
  `alloc_id` int(11) NOT NULL,
  `alloc_desc` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `alloc_val` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`alloc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_allocation`
--

LOCK TABLES `ip_allocation` WRITE;
/*!40000 ALTER TABLE `ip_allocation` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_allocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_challenge`
--

DROP TABLE IF EXISTS `ip_challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_challenge` (
  `chal_id` bigint(20) NOT NULL,
  `chal_status` int(11) DEFAULT NULL,
  `chal_crtd_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`chal_id`),
  KEY `chal_status_id_idx` (`chal_status`),
  KEY `chal_crtd_user_fk_idx` (`chal_crtd_by`),
  CONSTRAINT `chal_crtd_user_fk` FOREIGN KEY (`chal_crtd_by`) REFERENCES `ip_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `chal_status_id` FOREIGN KEY (`chal_status`) REFERENCES `ip_challenge_status` (`cs_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_challenge`
--

LOCK TABLES `ip_challenge` WRITE;
/*!40000 ALTER TABLE `ip_challenge` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_challenge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_challenge_status`
--

DROP TABLE IF EXISTS `ip_challenge_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_challenge_status` (
  `cs_id` int(11) NOT NULL,
  `cs_desc` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`cs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_challenge_status`
--

LOCK TABLES `ip_challenge_status` WRITE;
/*!40000 ALTER TABLE `ip_challenge_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_challenge_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_claim`
--

DROP TABLE IF EXISTS `ip_claim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_claim` (
  `claim_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `reward_id` int(11) DEFAULT NULL,
  `claim_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`claim_id`),
  KEY `claim_user_id_fk_idx` (`user_id`),
  KEY `claim_reward_id_idx` (`reward_id`),
  KEY `claim_status_id_fk_idx` (`claim_status`),
  CONSTRAINT `claim_reward_id` FOREIGN KEY (`reward_id`) REFERENCES `ip_rewards` (`rw_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `claim_status_id_fk` FOREIGN KEY (`claim_status`) REFERENCES `ip_claim_status` (`cs_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `claim_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `ip_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_claim`
--

LOCK TABLES `ip_claim` WRITE;
/*!40000 ALTER TABLE `ip_claim` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_claim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_claim_status`
--

DROP TABLE IF EXISTS `ip_claim_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_claim_status` (
  `cs_id` int(11) NOT NULL COMMENT '	',
  `cs_desc` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`cs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_claim_status`
--

LOCK TABLES `ip_claim_status` WRITE;
/*!40000 ALTER TABLE `ip_claim_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_claim_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_config`
--

DROP TABLE IF EXISTS `ip_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_config` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  `config_key` varchar(450) COLLATE utf8_bin NOT NULL,
  `config_value` varchar(450) COLLATE utf8_bin NOT NULL,
  `config_env` varchar(45) COLLATE utf8_bin NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(450) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_config`
--

LOCK TABLES `ip_config` WRITE;
/*!40000 ALTER TABLE `ip_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_function`
--

DROP TABLE IF EXISTS `ip_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_function` (
  `func_id` int(11) NOT NULL,
  `func_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`func_id`),
  KEY `func_grp_id_fk_idx` (`group_id`),
  KEY `func_user_id_fk_idx` (`user_id`),
  CONSTRAINT `func_grp_id_fk` FOREIGN KEY (`group_id`) REFERENCES `ip_group` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `func_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `ip_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_function`
--

LOCK TABLES `ip_function` WRITE;
/*!40000 ALTER TABLE `ip_function` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_group`
--

DROP TABLE IF EXISTS `ip_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_group` (
  `group_id` bigint(20) NOT NULL,
  `group_name` varchar(450) COLLATE utf8_bin NOT NULL,
  `group_status` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `group_parent_id` bigint(20) DEFAULT NULL,
  `group_email` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `group_admin_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  KEY `grp_par_id_fk_idx` (`group_parent_id`),
  KEY `grp_admin_id_fk_idx` (`group_admin_id`),
  CONSTRAINT `grp_admin_id_fk` FOREIGN KEY (`group_admin_id`) REFERENCES `ip_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `grp_par_id_fk` FOREIGN KEY (`group_parent_id`) REFERENCES `ip_group` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_group`
--

LOCK TABLES `ip_group` WRITE;
/*!40000 ALTER TABLE `ip_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_idea`
--

DROP TABLE IF EXISTS `ip_idea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_idea` (
  `idea_id` bigint(20) NOT NULL,
  `idea_title` varchar(100) COLLATE utf8_bin NOT NULL,
  `idea_desc` varchar(1000) COLLATE utf8_bin NOT NULL,
  `idea_cat` int(11) NOT NULL,
  `idea_ba` varchar(1000) COLLATE utf8_bin NOT NULL,
  `idea_status` int(11) NOT NULL,
  `idea_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idea_tag` varchar(500) COLLATE utf8_bin NOT NULL,
  `idea_blob` blob,
  `idea_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`idea_id`),
  KEY `idea_user_id_fk_idx` (`idea_user_id`),
  KEY `idea_cat_id_fk_idx` (`idea_cat`),
  KEY `idea_status_id_fk_idx` (`idea_status`),
  CONSTRAINT `idea_cat_id_fk` FOREIGN KEY (`idea_cat`) REFERENCES `ip_idea_cat` (`ic_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idea_status_id_fk` FOREIGN KEY (`idea_status`) REFERENCES `ip_idea_status` (`is_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idea_user_id_fk` FOREIGN KEY (`idea_user_id`) REFERENCES `ip_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_idea`
--

LOCK TABLES `ip_idea` WRITE;
/*!40000 ALTER TABLE `ip_idea` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_idea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_idea_cat`
--

DROP TABLE IF EXISTS `ip_idea_cat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_idea_cat` (
  `ic_id` int(11) NOT NULL,
  `ic_desc` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_idea_cat`
--

LOCK TABLES `ip_idea_cat` WRITE;
/*!40000 ALTER TABLE `ip_idea_cat` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_idea_cat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_idea_status`
--

DROP TABLE IF EXISTS `ip_idea_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_idea_status` (
  `is_id` int(11) NOT NULL,
  `is_desc` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`is_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_idea_status`
--

LOCK TABLES `ip_idea_status` WRITE;
/*!40000 ALTER TABLE `ip_idea_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_idea_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_points`
--

DROP TABLE IF EXISTS `ip_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_points` (
  `point_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `alloc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`point_id`),
  KEY `points_user_id_idx` (`user_id`),
  KEY `points_alloc_id_fk_idx` (`alloc_id`),
  CONSTRAINT `points_alloc_id_fk` FOREIGN KEY (`alloc_id`) REFERENCES `ip_allocation` (`alloc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `points_user_id` FOREIGN KEY (`user_id`) REFERENCES `ip_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_points`
--

LOCK TABLES `ip_points` WRITE;
/*!40000 ALTER TABLE `ip_points` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_rewards`
--

DROP TABLE IF EXISTS `ip_rewards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_rewards` (
  `rw_id` int(11) NOT NULL,
  `rw_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`rw_id`),
  KEY `rw_status_id_fk_idx` (`rw_status`),
  CONSTRAINT `rw_status_id_fk` FOREIGN KEY (`rw_status`) REFERENCES `ip_rewards_status` (`rs_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_rewards`
--

LOCK TABLES `ip_rewards` WRITE;
/*!40000 ALTER TABLE `ip_rewards` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_rewards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_rewards_status`
--

DROP TABLE IF EXISTS `ip_rewards_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_rewards_status` (
  `rs_id` int(11) NOT NULL,
  `rs_desc` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`rs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_rewards_status`
--

LOCK TABLES `ip_rewards_status` WRITE;
/*!40000 ALTER TABLE `ip_rewards_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_rewards_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_solution`
--

DROP TABLE IF EXISTS `ip_solution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_solution` (
  `sol_id` bigint(20) NOT NULL,
  `chal_id` bigint(20) DEFAULT NULL,
  `sub_user_id` bigint(20) DEFAULT NULL,
  `sol_status_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sol_id`),
  KEY `sol_chal_id_fk_idx` (`chal_id`),
  KEY `sol_sub_user_fk_idx` (`sub_user_id`),
  KEY `sol_status_id_fk_idx` (`sol_status_id`),
  CONSTRAINT `sol_chal_id_fk` FOREIGN KEY (`chal_id`) REFERENCES `ip_challenge` (`chal_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sol_status_id_fk` FOREIGN KEY (`sol_status_id`) REFERENCES `ip_solution_status` (`ss_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sol_sub_user_fk` FOREIGN KEY (`sub_user_id`) REFERENCES `ip_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_solution`
--

LOCK TABLES `ip_solution` WRITE;
/*!40000 ALTER TABLE `ip_solution` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_solution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_solution_status`
--

DROP TABLE IF EXISTS `ip_solution_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_solution_status` (
  `ss_id` int(11) NOT NULL,
  `ss_desc` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ss_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_solution_status`
--

LOCK TABLES `ip_solution_status` WRITE;
/*!40000 ALTER TABLE `ip_solution_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_solution_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_tag`
--

DROP TABLE IF EXISTS `ip_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_tag` (
  `tag_id` bigint(20) NOT NULL,
  `tag_type` int(11) NOT NULL,
  `tag_entity_id` bigint(20) NOT NULL,
  `tag_entity_table` int(11) NOT NULL,
  `tag_text` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `tag_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`tag_id`),
  KEY `tag_tt_id_fk_idx` (`tag_type`),
  KEY `tag_te_id_fk_idx` (`tag_entity_table`),
  KEY `tag_user_id_fk_idx` (`tag_user_id`),
  CONSTRAINT `tag_te_id_fk` FOREIGN KEY (`tag_entity_table`) REFERENCES `ip_tag_entity_type` (`te_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tag_tt_id_fk` FOREIGN KEY (`tag_type`) REFERENCES `ip_tag_type` (`tt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tag_user_id_fk` FOREIGN KEY (`tag_user_id`) REFERENCES `ip_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_tag`
--

LOCK TABLES `ip_tag` WRITE;
/*!40000 ALTER TABLE `ip_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_tag_entity_type`
--

DROP TABLE IF EXISTS `ip_tag_entity_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_tag_entity_type` (
  `te_id` int(11) NOT NULL,
  `te_desc` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`te_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_tag_entity_type`
--

LOCK TABLES `ip_tag_entity_type` WRITE;
/*!40000 ALTER TABLE `ip_tag_entity_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_tag_entity_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_tag_type`
--

DROP TABLE IF EXISTS `ip_tag_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_tag_type` (
  `tt_id` int(11) NOT NULL,
  `tt_desc` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`tt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_tag_type`
--

LOCK TABLES `ip_tag_type` WRITE;
/*!40000 ALTER TABLE `ip_tag_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_tag_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ip_user`
--

DROP TABLE IF EXISTS `ip_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ip_user` (
  `user_id` bigint(20) NOT NULL,
  `user_f_name` varchar(75) COLLATE utf8_bin NOT NULL,
  `user_l_name` varchar(75) COLLATE utf8_bin NOT NULL,
  `user_m_name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `user_id_num` varchar(45) COLLATE utf8_bin NOT NULL,
  `user_screen_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `user_email` varchar(450) COLLATE utf8_bin NOT NULL,
  `user_contact` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `user_skills` varchar(1000) COLLATE utf8_bin NOT NULL,
  `user_bio` varchar(1000) COLLATE utf8_bin NOT NULL,
  `user_fb_handle` varchar(500) COLLATE utf8_bin NOT NULL,
  `user_tw_handle` varchar(500) COLLATE utf8_bin NOT NULL,
  `user_avatar` blob NOT NULL,
  `user_status` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'A',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_screen_name_UNIQUE` (`user_screen_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ip_user`
--

LOCK TABLES `ip_user` WRITE;
/*!40000 ALTER TABLE `ip_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `ip_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-28  6:44:54
