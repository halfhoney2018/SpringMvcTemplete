DROP DATABASE IF EXISTS yootk ;
CREATE DATABASE yootk CHARACTER SET UTF8;
USE yootk ;
CREATE TABLE t_news(
  c_nid   BIGINT    AUTO_INCREMENT ,
  c_title VARCHAR(50) ,
  c_content TEXT ,
  CONSTRAINT pk_nid PRIMARY KEY(c_nid)
) engine=innodb ;