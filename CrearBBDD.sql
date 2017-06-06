DROP DATABASE IF EXISTS Proyecto;
CREATE DATABASE Proyecto;
USE Proyecto;

CREATE TABLE User (

  id INTEGER NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255),
  contrasena VARCHAR(255),
  email VARCHAR(255),
  admin INTEGER,
  PRIMARY KEY (id)   
)ENGINE = InnoDB;


CREATE TABLE Eetakemon (

  id INTEGER NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255),
  tipo VARCHAR(255),
  nivel INTEGER NOT NULL,
  foto VARCHAR(255),
  PRIMARY KEY (id)   
)ENGINE = InnoDB;

CREATE TABLE Relation (

  id INTEGER NOT NULL AUTO_INCREMENT,
  idUser INTEGER NOT NULL,
  idEetakemon INTEGER NOT NULL,
  level INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (idUser) REFERENCES   User(id),
  FOREIGN KEY (idEetakemon) REFERENCES   Eetakemon(id)   
)ENGINE = InnoDB;