ALTER TABLE `user`
ADD COLUMN `password` varchar(100) DEFAULT NULL AFTER `email`;