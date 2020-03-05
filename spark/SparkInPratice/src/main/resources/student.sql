DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `name` varchar(60) NOT NULL COMMENT '姓名',
  `age` int(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `name` varchar(60) NOT NULL COMMENT '姓名',
  `score` int(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `good_student_infos`;
CREATE TABLE `good_student_infos` (
  `name` varchar(60) NOT NULL COMMENT '姓名',
  `age` int(20) NOT NULL,
  `score` int(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

insert into t_student values('yasaka',18);
insert into t_student values('xuruyun',17);
insert into t_student values('liangyongqi',19);

insert into t_score values('yasaka',100);
insert into t_score values('xuruyun',99);
insert into t_score values('liangyongqi',74);


CREATE TABLE `wordcount` (
  `word` varchar(255) NOT NULL,
  `numcount` int(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
