USE cloud_native;

SET NAMES utf8 ;

DROP TABLE IF EXISTS `Content`;

CREATE TABLE `content` (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           title VARCHAR(255) NOT NULL,
                           author VARCHAR(255) NOT NULL,
                           date VARCHAR(255),
                           content TEXT,
                           imageUrl VARCHAR(255)
);

insert into content(title, author, date, content, imageUrl) values ('가','임직찬','가','가','가');
insert into content(title, author, date, content, imageUrl) values ('가','임직찬','가','가','가');
insert into content(title, author, date, content, imageUrl) values ('가','임직찬','가','가','가');
insert into content(title, author, date, content, imageUrl) values ('가','임직찬','가','가','가');
insert into content(title, author, date, content, imageUrl) values ('가','임직찬','가','가','가');