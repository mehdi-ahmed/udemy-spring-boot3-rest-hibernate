CREATE DATABASE IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

--
-- Table structure for `employee`
--


DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
    `id` int NOT NULL AUTO_INCREMENT,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) DEFAULT NULL,
    `email` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for `employee`
--

INSERT INTO `employee` VALUES
                           (1, 'Marcus', 'Ben Ammar', 'marcus@gmail.com'),
                           (2, 'Sophie', 'Coucke', 'sophie@gmail.com'),
                           (3, 'Ilona', 'Ben Ammar', 'ilona@gmail.com'),
                           (4, 'Daria', 'Rubakina', 'daria@gmail.com');
