SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for director
-- ----------------------------
DROP TABLE IF EXISTS `director`;
CREATE TABLE `director` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `director_user` varchar(255) DEFAULT NULL,
  `director_pass` varchar(255) DEFAULT NULL,
  `director_name` varchar(255) DEFAULT NULL,
  `director_sex` bit(1) DEFAULT NULL,
  `director_phone` bigint(20) DEFAULT NULL,
  `director_bank` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for project_manager
-- ----------------------------
DROP TABLE IF EXISTS `project_manager`;
CREATE TABLE `project_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pm_user` varchar(255) DEFAULT NULL,
  `pm_pass` varchar(255) DEFAULT NULL,
  `pm_name` varchar(255) DEFAULT NULL,
  `pm_sex` bit(1) DEFAULT NULL,
  `pm_phone` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `true_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `pm_id` int(11) DEFAULT NULL,
  `engineer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for welfare
-- ----------------------------
DROP TABLE IF EXISTS `welfare`;
CREATE TABLE `welfare` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `welfare_project_id` bigint(20) DEFAULT NULL,
  `welfare_name` varchar(255) DEFAULT NULL,
  `welfare_info` varchar(255) DEFAULT NULL,
  `welfare_number` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for boss
-- ----------------------------
DROP TABLE IF EXISTS `boss`;
CREATE TABLE `boss` (
  `boss_id` int(11) NOT NULL AUTO_INCREMENT,
  `boss_user` varchar(255) DEFAULT NULL,
  `boss_pass` varchar(255) DEFAULT NULL,
  `boss_name` varchar(255) DEFAULT NULL,
  `boss_sex` bit(1) DEFAULT NULL,
  `boss_phone` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`boss_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_user` varchar(255) DEFAULT NULL,
  `admin_pass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for money
-- ----------------------------
DROP TABLE IF EXISTS `money`;
CREATE TABLE `money` (
  `money_id` int(11) NOT NULL AUTO_INCREMENT,
  `money_date` date DEFAULT NULL,
  `money_from_bank` int(11) DEFAULT NULL,
  `money_to_bank` int(11) DEFAULT NULL,
  `money_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`money_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) DEFAULT NULL,
  `project_instruction` varchar(255) DEFAULT NULL,
  `project_pm_id` int(11) DEFAULT NULL,
  `project_start_money` int(11) DEFAULT NULL,
  `project_true_money` int(11) DEFAULT NULL,
  `project_total_money` int(11) DEFAULT NULL,
  `project_status` varchar(255) DEFAULT NULL,
  `project_unpass_dir` varchar(255) DEFAULT NULL,
  `project_unpass_boss` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for engineer
-- ----------------------------
DROP TABLE IF EXISTS `engineer`;
CREATE TABLE `engineer` (
  `engineer_id` int(11) NOT NULL AUTO_INCREMENT,
  `engineer_user` varchar(255) DEFAULT NULL,
  `engineer_pass` varchar(255) DEFAULT NULL,
  `engineer_name` varchar(255) DEFAULT NULL,
  `engineer_sex` bit(1) DEFAULT NULL,
  `engineer_phone` bigint(20) DEFAULT NULL,
  `engineer_bank` int(11) DEFAULT NULL,
  PRIMARY KEY (`engineer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
