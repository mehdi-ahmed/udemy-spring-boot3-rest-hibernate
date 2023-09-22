--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `user` (`username`,`password`,`enabled`, `first_name`, `last_name`, `email`)
VALUES
    ('marcus','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1,'Marcus', 'Ammar', 'marcus@happyhouse.com'),
    ('sophie','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1,'Sophie', 'Coocke', 'sophie@happyhouse.com'),
    ('ilona','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1,'Ilona', 'Ammar', 'ilona@happyhouse.com');

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES
    ('ROLE_EMPLOYEE'),('ROLE_MANAGER'),('ROLE_ADMIN');



--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 3),
    (3, 1)