CREATE DATABASE IF NOT EXISTS library;

USE library;

DROP TABLE IF EXISTS libraryStore;


CREATE TABLE libraryStore (
id int NOT NULL auto_increment PRIMARY KEY,
first_name varchar(45) DEFAULT NULL,
last_name varchar(45) DEFAULT NULL,
email varchar(45) DEFAULT NULL
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;