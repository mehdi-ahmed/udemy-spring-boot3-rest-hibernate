USE `employee_directory`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
SET foreign_key_checks = 1;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(50) NOT NULL,
                        `password` char(80) NOT NULL,
                        `enabled` tinyint NOT NULL,
                        `first_name` varchar(64) NOT NULL,
                        `last_name` varchar(64) NOT NULL,
                        `email` varchar(64) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
                               `user_id` int(11) NOT NULL,
                               `role_id` int(11) NOT NULL,

                               PRIMARY KEY (`user_id`,`role_id`),

                               KEY `FK_ROLE_idx` (`role_id`),

                               CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`)
                                   REFERENCES `user` (`id`)
                                   ON DELETE NO ACTION ON UPDATE NO ACTION,

                               CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
                                   REFERENCES `role` (`id`)
                                   ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

