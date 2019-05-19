CREATE DATABASE IF NOT EXISTS mpes DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

use mpes;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(191) DEFAULT NULL COMMENT '姓名',
  `gender` varchar(191) DEFAULT NULL COMMENT '性别',
  `idcard` varchar(191) DEFAULT NULL COMMENT '身份证',
  `extra` text COMMENT '附加信息',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
