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

CREATE TABLE Question (

  id INTEGER NOT NULL AUTO_INCREMENT,
  tipo VARCHAR(255),
  question VARCHAR(255),
  answer INTEGER NOT NULL,
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


Insert INTO User (nombre, contrasena, email, admin) VALUES ('mikel', 'TAv5pbyzSuw=', 'mikel@gmail.com', 1);
Insert INTO User (nombre, contrasena, email, admin) VALUES ('aleix', '7ZOZ9CzlKJs=', 'aleix@gmail.com', 1);
Insert INTO User (nombre, contrasena, email, admin) VALUES ('guillem', 'AT0D6sMGdKM=', 'guillem@gmail.com', 1);
Insert INTO User (nombre, contrasena, email, admin) VALUES ('PresentacionDsa', 'FUw7NUdZ0TJ6Ve9ZkNdPUA==', 'presentaciondsa@gmail.com', 1);


Insert INTO Question (tipo, question, answer) VALUES ('Inferior', '¿Es Jesus Alcober profesor de Empresa?', 1);
Insert INTO Question (tipo, question, answer) VALUES ('Normal', '¿Es Jordi Berenguer profesor de ER?', 1);
Insert INTO Question (tipo, question, answer) VALUES ('Legendario', '¿Es Lluis Casals profesor de IX?', 1);
Insert INTO Question (tipo, question, answer) VALUES ('Inferior', '¿Cálculo es una asignatura del segundo cuadrimestre?', 0);
Insert INTO Question (tipo, question, answer) VALUES ('Normal', '¿Es Jordi Berenger doctor en telecomunicaciones?', 1);
Insert INTO Question (tipo, question, answer) VALUES ('Legendario', '¿Es Luis Gonzaga Alonso Zarate el director de la EETAC?', 1);
Insert INTO Question (tipo, question, answer) VALUES ('Inferior', '¿Son la galletas de la maquina un manjar de dioses?', 1);
Insert INTO Question (tipo, question, answer) VALUES ('Normal', '¿Es asequible el menu de la cafeteria?', 0);
Insert INTO Question (tipo, question, answer) VALUES ('Legendario', '¿La sala de estudios de la EETAC es la 023G?', 1);
Insert INTO Question (tipo, question, answer) VALUES ('Legendario', '¿La sala de estudios de la EETAC es la 026G?', 0);
Insert INTO Question (tipo, question, answer) VALUES ('Normal', '¿Puede este grupo obtener el 10 en la asignatura?', 1);
Insert INTO Question (tipo, question, answer) VALUES ('Inferior', '¿El cafe con leche en la cafeteria cuesta 1 euro?', 0);
Insert INTO Question (tipo, question, answer) VALUES ('Legendario', '¿Abre la biblioteca a las 8:00?', 0);
Insert INTO Question (tipo, question, answer) VALUES ('Legendario', '¿Abre la biblioteca a las 9:00?', 1);
Insert INTO Question (tipo, question, answer) VALUES ('Inferior', '¿Abre la biblioteca a las 8:00?', 0);
Insert INTO Question (tipo, question, answer) VALUES ('Legendario', '¿Abre la biblioteca a las 9:00?', 1);
Insert INTO Question (tipo, question, answer) VALUES ('Normal', '¿Es Lluis Casals coordinador de IX?', 0);
Insert INTO Question (tipo, question, answer) VALUES ('Normal', '¿Es Jordi Mataix coordinador de XLAM?', 1);
Insert INTO Question (tipo, question, answer) VALUES ('Legendario', '¿Es David Perez Diaz de Cerio cordinador de FC?', 1);
Insert INTO Question (tipo, question, answer) VALUES ('Legendario', '¿Es David Perez Diaz de Cerio cordinador de ER?', 0);
Insert INTO Question (tipo, question, answer) VALUES ('Legendario', '¿Es DSA la ultima asignatura de programación en telemática?', 0);

Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES ('Bernorlax', 'Normal', 15, '/images/bernorlax.png');
Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES ('Davyphno', 'Normal', 15, '/images/davyphno.png');
Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES ('Francerpie', 'Inferior', 1, '/images/francerpie.png');
Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES ('Jesuskou', 'Inferior', 1, '/images/jesuskou.png');
Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES ('Jordinine', 'Normal', 15, '/images/jordinine.png');
Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES ('Lluiskarp', 'Legendario', 30, '/images/lluiskarp.png');
Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES ('Mewdecerio', 'Legendario', 30, '/images/mewdecerio.png');

Insert INTO Relation (idUser, idEetakemon, level) VALUES (1, 1, 30);
Insert INTO Relation (idUser, idEetakemon, level) VALUES (2, 1, 30);
Insert INTO Relation (idUser, idEetakemon, level) VALUES (3, 1, 30);
Insert INTO Relation (idUser, idEetakemon, level) VALUES (1, 2, 30);
Insert INTO Relation (idUser, idEetakemon, level) VALUES (2, 2, 30);
Insert INTO Relation (idUser, idEetakemon, level) VALUES (3, 2, 30);
Insert INTO Relation (idUser, idEetakemon, level) VALUES (1, 3, 30);
Insert INTO Relation (idUser, idEetakemon, level) VALUES (2, 3, 30);
Insert INTO Relation (idUser, idEetakemon, level) VALUES (3, 3, 30);
