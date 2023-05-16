CREATE TABLE `user` (
 `id` varchar(36) NOT NULL,
 `first_name` varchar(100) DEFAULT NULL,
 `last_name` varchar(100) DEFAULT NULL,
 `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;