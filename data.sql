DROP DATABASE IF EXISTS SGA;
CREATE DATABASE SGA;
USE SGA;
CREATE TABLE Structures(
    idStructure INT PRIMARY KEY AUTO_INCREMENT,
    nom text NOT NULL,
    dateCreation DATE NOT NULL,
    email text NOT NULL,
    adresse text NOT NULL,
    siteWeb text NOT NULL,
    objectif text NOT NULL
    );

INSERT INTO Structures(nom,dateCreation,email,adresse,siteWeb,objectif) 
    VALUES("Assoc",CURRENT_DATE(),"contact@assoc.com","Bloc A NR 123 Agadir Maroc","www.assoc.ma","but non lucratif");

SELECT * FROM Structures;

CREATE TABLE Depenses(
    idDepense INT PRIMARY KEY AUTO_INCREMENT,
    idStructure INT NOT NULL  REFERENCES  Structures(idStructure),
    idAdherent INT NOT NULL,
    montant DOUBLE NOT NULL DEFAULT 0,
    dateDepense DATE NOT NULL,
    typeDepense text NOT NULL
    );

INSERT INTO Depenses(idStructure,idAdherent,montant,dateDepense,typeDepense) 
    VALUES(1,1,1000.0,CURRENT_DATE(),"Electricité");

SELECT * FROM Depenses;

CREATE TABLE Adherents(
    idAdherent INT PRIMARY KEY AUTO_INCREMENT,
    idLigneFonction INT NOT NULL  REFERENCES LigneFonctions(idLigneFonction),
    idStructure INT NOT NULL  REFERENCES  Structures(idStructure),
    nom text NOT NULL,
    prenom text NOT NULL,
    cin text NOT NULL,
    dateNaissance DATE NOT NULL,
    lieuNaissance text NOT NULL,
    dateAdhesion DATE NOT NULL,
    profession text NOT NULL,
    photo text NOT NULL,
    sexe text NOT NULL,
    motDePasse text NOT NULL,
    telephone text NOT NULL,
    adresse text NOT NULL,
    email text NOT NULL
    );

INSERT INTO Adherents(idLigneFonction,idStructure,nom,prenom,cin,dateNaissance,lieuNaissance,dateAdhesion,profession,photo,sexe,motDePasse,telephone,adresse,email) 
    VALUES(1,1,"Malek","Ismail","JK0000",CURRENT_DATE(),"Tikiouine",CURRENT_DATE(),"Ingénieur","ismail.png","Homme","ismail123","0600000000","Tikiouine Elhajeb Agadir Maroc","ismail@gmail.com");

SELECT * FROM Adherents;

CREATE TABLE LigneFonctions(
    idLigneFonction INT PRIMARY KEY AUTO_INCREMENT,
    dateDebut DATE NOT NULL,
    dateFin DATE NOT NULL
    );

INSERT INTO LigneFonctions(dateDebut,dateFin) 
    VALUES(CURRENT_DATE(),CURRENT_DATE());

SELECT * FROM LigneFonctions;

CREATE TABLE Fonctions(
    idFonction INT PRIMARY KEY AUTO_INCREMENT,
    idLigneFonction INT NOT NULL  REFERENCES LigneFonctions(idLigneFonction),
    role text NOT NULL
    );

INSERT INTO Fonctions(idLigneFonction,role) 
    VALUES(1,"Secrétaire");

SELECT * FROM Fonctions;

CREATE TABLE Donneurs(
    idDonneur INT PRIMARY KEY AUTO_INCREMENT,
    idStructure INT NOT NULL  REFERENCES  Structures(idStructure),
    email text NOT NULL,
    telephone text NOT NULL,
    adresse text NOT NULL,
    motDePasse text NOT NULL
);

INSERT INTO Donneurs(idStructure,email,telephone,adresse,motDePasse) 
    VALUES(1,"abdelgha4@gmail.com","0700000000","Tikiouine ELhajeb Agadir Maroc","abdelghafour123");

SELECT * FROM Donneurs;

CREATE TABLE DonneursPhysiques(
    idDonneur INT NOT NULL REFERENCES Donneurs(idDonneur),
    cin text NOT NULL,
    nom text NOT NULL,
    prenom text NOT NULL
    );
CREATE TABLE DonneursMorals(
    idDonneur INT NOT NULL REFERENCES Donneurs(idDonneur),
    nom text NOT NULL
    );

INSERT INTO DonneursPhysiques(idDonneur,cin,nom,prenom) 
    VALUES(1,"JK0000","Ait Bennasser","Abdelghafour");

SELECT * FROM DonneursPhysiques;

CREATE TABLE Dons(
    idDon INT PRIMARY KEY AUTO_INCREMENT,
    idDonneur INT NOT NULL REFERENCES Donneurs(idDonneur),
    dateDon DATE NOT NULL,
    montant DOUBLE NOT NULL DEFAULT 0
);

INSERT INTO Dons(idDonneur,dateDon,montant) 
    VALUES(1,CURRENT_DATE(),10000.0);

SELECT * FROM Dons;

CREATE TABLE DonsEspece(
    idDon INT NOT NULL REFERENCES Dons(idDon)
    );

INSERT INTO DonsEspece(idDon) 
    VALUES(1);

SELECT * FROM DonsEspece;

CREATE TABLE DonsCheque(
    idDon INT NOT NULL REFERENCES Dons(idDon),
    numeroCompteBanque text NOT NULL,
    dateCheque DATE NOT NULL,
    dateDepot DATE NOT NULL,
    nomBanque text NOT NULL
    );
CREATE TABLE DonsVersement(
    idDon INT NOT NULL REFERENCES Dons(idDon),
    numeroCompteBanque text NOT NULL
    );
