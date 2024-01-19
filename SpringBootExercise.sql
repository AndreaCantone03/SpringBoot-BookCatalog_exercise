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

DROP TABLE IF EXISTS `books`;

CREATE TABLE books(
	`id` integer NOT NULL AUTO_INCREMENT,
	`title` varchar(40) NOT NULL,
	`author` varchar(40) NOT NULL,
	`year` date default null,
	`price` float NOT NULL,
    `user_id` integer NOT NULL,
    CONSTRAINT userId FOREIGN KEY (user_id) REFERENCES `user`(id),
	PRIMARY KEY (id)
);

INSERT INTO `books` (title, author, `year`, price, user_id) VALUES ("Math Book", "Math professor", "2024-01-10", 10.0, 1), ("Italian Book", "Italian professor", "2024-02-05", 10.0, 2);

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`(
	`id` int(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(30) NOT NULL,
    `surname` varchar(30) NOT NULL,
	`username` varchar(30) NOT NULL,
    `password` varchar(30) NOT NULL,
	PRIMARY KEY (`id`)
);

INSERT INTO `users` (`name`, surname, username, `password`) VALUES ("Name","Surname","Username","Password"), ("John", "Doe", "JohnDoe", "JD");