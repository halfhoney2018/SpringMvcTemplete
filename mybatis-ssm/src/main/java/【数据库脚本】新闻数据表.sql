DROP DATABASE IF EXISTS yootk ;
CREATE DATABASE yootk CHARACTER SET UTF8;
USE yootk ;
CREATE TABLE news(
  nid   BIGINT    AUTO_INCREMENT ,
  title VARCHAR(50) ,
  content TEXT ,
  CONSTRAINT pk_nid PRIMARY KEY(nid)
) engine=innodb ;