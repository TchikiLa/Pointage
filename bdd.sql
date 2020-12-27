 CREATE DATABASE ptg DEFAULT CHARACTER SET UTF8 DEFAULT COLLATE UTF8_GENERAL_CI;
 
 USE ptg;
 
CREATE TABLE fonction(
    idfonction INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(30) NOT NULL,
  salair VARCHAR(30) NOT NULL

);
CREATE TABLE departement(
    iddepartement INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(30) NOT NULL,
  faculte VARCHAR(30) NOT NULL

);
CREATE TABLE fonctionnaires(
  idfonctionnaire INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(30) NOT NULL,
  prenom VARCHAR(30) NOT NULL,
  age VARCHAR(30) NOT NULL,
  idfonction INT NOT NULL,
  iddepartement INT NOT NULL,
  FOREIGN KEY(idfonction) REFERENCES fonction(idfonction),
  FOREIGN KEY(iddepartement) REFERENCES departement(iddepartement)
);


CREATE TABLE pointage(
  idfonctionnaire INT NOT NULL,
  jour DATE NOT NULL  PRIMARY KEY,
  statut VARCHAR(30) NOT NULL,

    PRIMARY KEY(idfonctionnaire,jour),
    FOREIGN KEY(idfonctionnaire) REFERENCES fonctionnaires(idfonctionnaire)
);



