CREATE TABLE IF NOT EXISTS  Reader (
	username varchar(20) PRIMARY KEY,
	password varchar(10) not null,
	fullname varchar(50) not null
);

CREATE TABLE IF NOT EXISTS  Book (
	id identity PRIMARY KEY,
	username varchar(20),
	isbn varchar(10) not null,
	title varchar(50) not null,
	author varchar(50) not null,
	description varchar(2000) not null
);