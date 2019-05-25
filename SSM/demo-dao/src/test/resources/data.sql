DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `age` int(10) DEFAULT NULL COMMENT '年龄',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别，1男性，2女性',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;


INSERT INTO `tb_user` VALUES ('1', 'zhangsan', '123456', '张三', '30', '1', '1984-08-08', '2014-09-19 16:56:04', '2014-09-21 11:24:59');
INSERT INTO `tb_user` VALUES ('2', 'lisi', '123456', '李四', '21', '2', '1991-01-01', '2014-09-19 16:56:04', '2014-09-19 16:56:04');
INSERT INTO `tb_user` VALUES ('3', 'wangwu', '123456', '王五', '22', '2', '1989-01-01', '2014-09-19 16:56:04', '2014-09-19 16:56:04');
INSERT INTO `tb_user` VALUES ('4', 'zhangwei', '123456', '张伟', '20', '1', '1988-09-01', '2014-09-19 16:56:04', '2014-09-19 16:56:04');
INSERT INTO `tb_user` VALUES ('5', 'lina', '123456', '李娜', '28', '1', '1985-01-01', '2014-09-19 16:56:04', '2014-09-19 16:56:04');
INSERT INTO `tb_user` VALUES ('6', 'lilei', '123456', '李磊', '23', '1', '1988-08-08', '2014-09-20 11:41:15', '2014-09-20 11:41:15');
INSERT INTO `tb_user` VALUES ('8', 'xiaofeng', '123456', '萧峰', '30', '1', '2018-06-25', '2018-06-25 18:42:25', '2018-06-25 18:42:25');
INSERT INTO `tb_user` VALUES ('12', 'zhendeshuai', '565656', '吴彦祖', '26', '1', '2018-06-26', '2018-06-26 14:55:59', '2018-06-26 15:12:12');
INSERT INTO `tb_user` VALUES ('14', 'jiumozhi', '123456', '鸠摩智', '30', '1', '2018-06-26', '2018-06-26 23:53:15', '2018-06-26 23:53:15');
INSERT INTO `tb_user` VALUES ('21', 'zhangsan1234', '12345', '杰克', '20', '1', '2000-01-01', '2018-07-07 21:50:26', '2018-07-07 21:50:26');
INSERT INTO `tb_user` VALUES ('22', 'zhangsanqwe1', '123455', '张三', '30', '1', '1999-02-25', '2018-07-07 21:51:20', '2018-07-07 21:51:20');
