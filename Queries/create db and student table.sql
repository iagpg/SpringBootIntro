CREATE DATABASE IF NOT EXISTS student_tracker;

USE student_tracker;

DROP TABLE IF EXISTS student;


CREATE TABLE student (
id int NOT NULL auto_increment PRIMARY KEY,
first_name varchar(45) DEFAULT NULL,
last_name varchar(45) DEFAULT NULL,
email varchar(45) DEFAULT NULL

)
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;