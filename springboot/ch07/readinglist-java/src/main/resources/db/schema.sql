CREATE DATABASE IF NOT EXISTS TEST;
use test;
drop table reader;
drop table book;
CREATE TABLE IF NOT EXISTS  reader (
	username varchar(20) PRIMARY KEY,
	password varchar(10) not null,
	fullname varchar(50) not null
);

CREATE TABLE IF NOT EXISTS  book (
	id int auto_increment primary key,
	username varchar(20),
	isbn varchar(10) not null,
	title varchar(50) not null,
	author varchar(50) not null,
	description varchar(2000) not null
);
