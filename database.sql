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

DROP TABLE IF EXISTS `operation_log_201905`
CREATE TABLE `operation_log_201905` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户表外键',
  `operation_type` varchar(191) DEFAULT NULL COMMENT '操作类型',
  `ip` varchar(191) DEFAULT NULL COMMENT 'IP地址',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_operation_log_on_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作记录表(201905)';
