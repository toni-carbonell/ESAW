DROP SCHEMA IF EXISTS twitter;
CREATE DATABASE IF NOT EXISTS twitter;
USE twitter;

CREATE TABLE USERS(
id INT(4) PRIMARY KEY NOT NULL,
mail VARCHAR(50) NOT NULL,
pwd VARCHAR(50) NOT NULL,
gender ENUM('Female', 'Male', 'Other'),
name VARCHAR(50) NOT NULL,
userName VARCHAR(50) NOT NULL,
userType ENUM('Reader', 'Author', 'Admin'));

CREATE TABLE TWEETS(
id INT(4) PRIMARY KEY NOT NULL,
uid INT(4) NOT NULL,
parentid INT(4) NOT NULL,
timestamp DATETIME NOT NULL,
content VARCHAR(255) NOT NULL,
FOREIGN KEY(parentid) REFERENCES TWEETS(id));

CREATE TABLE FOLLOW(
user1_following INT(4) NOT NULL,
user2_followed INT(4) NOT NULL,
PRIMARY KEY(user1_following, user2_followed),
FOREIGN KEY(user1_following) REFERENCES USERS(id),
FOREIGN KEY(user2_followed) REFERENCES USERS(id));

CREATE TABLE BOOK(
id INT(4) PRIMARY KEY NOT NULL,
aid INT(4) NOT NULL,
name VARCHAR(50) NOT NULL,
publishdate DATE NOT NULL,
authorName VARCHAR(50) NOT NULL,
description VARCHAR(255) NOT NULL,
genre VARCHAR(50) NOT NULL,
FOREIGN KEY(aid) REFERENCES USERS(id));

CREATE TABLE LIKES_BOOK(
uid INT(4) NOT NULL,
bid INT(4) NOT NULL,
PRIMARY KEY(uid, bid),
FOREIGN KEY(uid) REFERENCES USERS(id),
FOREIGN KEY(bid) REFERENCES BOOK(id));


INSERT INTO USERS (id, mail, gender, name, userName, userType)
VALUES (1, "oriol.gv2001@gmail.com", 2, "Oriol", "MainSazed", "Reader"),
(2, "brandon.sanderson@gmail.com", 2, "Brandon", "DragonSteel", "Author");

INSERT INTO TWEETS (id, uid, parentid, timestamp, content)
VALUES (1,1,0,"2001-06-03 17:30:10","I love the cormere"),
(2,2,1,"2001-06-03 19:30:10", "I love you gancho");

INSERT INTO FOLLOW (user1_following, user2_followed)
VALUES (1,2);

INSERT INTO BOOK (id, aid, name, publishdate, authorName, description, genre)
VALUES (1,2,"The way of Kings", "2010-08-31", "Brandon Sanderson", "Best book ever", "fantasy"),
(2,2,"The final empire", "206-07-17", "Brandon Sanderson", "Best book ever, just behind the way of kings", "fantasy, drama");

INSERT INTO LIKES_BOOK (uid, bid)
VALUES(1,1),
(1,2);

SELECT * FROM TWEETS
WHERE uid IN (SELECT
user2_followed FROM FOLLOW
WHERE user1_followed = 1); #Where 1 is the id of the concrete user, and uid will be all the users that user 1 is following