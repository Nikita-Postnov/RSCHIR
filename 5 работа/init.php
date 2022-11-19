CREATE DATABASE IF NOT EXISTS appDB;
CREATE USER IF NOT EXISTS ‘user'@'%' IDENTIFIED WITH mysql_native_password BY ‘toor';
GRANT SELECT, UPDATE, INSERT, DELETE ON appDB.* TO ‘user'@'%';
FLUSH PRIVILEGES;

USE appDB;
CREATE TABLE IF NOT EXISTS users (
	ID INT(11) NOT NULL AUTO_INCREMENT,
	username VARCHAR(32) NOT NULL,
	password VARCHAR(256) NOT NULL,
	email VARCHAR(64) NOT NULL,
	PRIMARY KEY (ID)
);
 

CREATE TABLE IF NOT EXISTS tickets (
	ID INT(11) NOT NULL AUTO_INCREMENT,
	price INT NOT NULL,
	source VARCHAR(128) NOT NULL,
	destination VARCHAR(128) NOT NULL,
	title VARCHAR(256) NOT NULL,
	PRIMARY KEY (ID)
);
 

CREATE TABLE IF NOT EXISTS files (
	ID INT(11) NOT NULL AUTO_INCREMENT,
	content LONGBLOB NOT NULL,
	author VARCHAR(32) NOT NULL,
	title VARCHAR(256) NOT NULL,
	'type' VARCHAR(256) NOT NULL,
	PRIMARY KEY (ID)
);
 

INSERT INTO users (username, password, email) VALUES ("Maxawergy", "$1$LgKNOvfK$zkSdmsUyzksmrPFJLxGNs1", "maxawergy@yandex.ru");
INSERT INTO tickets (price, source, destination, title) VALUES (23000, "Moscow", "Astana", "Low-cost to Kazakhstan");