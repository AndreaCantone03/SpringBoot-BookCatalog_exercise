CREATE DATABASE  IF NOT EXISTS `SpringBootDatabase` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `SpringBootDatabase`;

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

DROP TABLE IF EXISTS `book`;

CREATE TABLE book(
	`id` integer NOT NULL AUTO_INCREMENT,
	`title` varchar(40) NOT NULL,
	`author` varchar(40) NOT NULL,
	`isbn` date default null,
	`price` float NOT NULL,
    -- `user_id` integer NOT NULL,
    -- CONSTRAINT userId FOREIGN KEY (user_id) REFERENCES `user`(id),
	PRIMARY KEY (`id`)
);

INSERT INTO `books` (title, author, isbn, price) VALUES ("Math Book", "Math professor", "2024-01-10", 10.0), ("Italian Book", "Italian professor", "2024-02-05", 10.0);

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`(
	`id` int(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(20) NOT NULL,
    `surname` varchar(20) NOT NULL,
	`username` varchar(20) NOT NULL,
    `password` varchar(25) NOT NULL,
	PRIMARY KEY (`id`)
);

INSERT INTO `user` (`name`, surname, username, `password`) VALUES ("Name","Surname","Username","Password"), ("John", "Doe", "JohnDoe", "JD");