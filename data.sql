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
CREATE TABLE Depenses(
    idDepense INT PRIMARY KEY AUTO_INCREMENT,
    idStructure INT REFERENCES Structures(idStructure),
    idAdherent INT NOT NULL,
    montant DOUBLE NOT NULL DEFAULT 0,
    dateDepense DATE NOT NULL,
    typeDepense text NOT NULL
);
CREATE TABLE Adherents(
    idAdherent INT PRIMARY KEY AUTO_INCREMENT,
    idLigneFonction INT NOT NULL REFERENCES LigneFonctions(idLigneFonction),
    idStructure INT NOT NULL REFERENCES Structures(idStructure),
    nom text NOT NULL,
    prenom text NOT NULL,
    cin text NOT NULL,
    dateNaissance DATE NOT NULL,
    lieuNaissance text NOT NULL,
    dateAdhesion DATE NOT NULL,
    profession text NOT NULL,
    sexe text NOT NULL,
    motDePasse text NOT NULL,
    telephone text NOT NULL,
    adresse text NOT NULL,
    email text NOT NULL
);
CREATE TABLE LigneFonctions(
    idLigneFonction INT PRIMARY KEY AUTO_INCREMENT,
    idFonction INT NOT NULL REFERENCES Fonctions(idFonction),
    dateDebut DATE NOT NULL,
    dateFin DATE NOT NULL
);
CREATE TABLE Fonctions(
    idFonction INT PRIMARY KEY AUTO_INCREMENT,
    role text NOT NULL
);
CREATE TABLE Donneurs(
    idDonneur INT PRIMARY KEY AUTO_INCREMENT,
    idStructure INT REFERENCES Structures(idStructure),
    email text NOT NULL,
    telephone text NOT NULL,
    adresse text NOT NULL,
    motDePasse text NOT NULL
);
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
CREATE TABLE Dons(
    idDon INT PRIMARY KEY AUTO_INCREMENT,
    idDonneur INT NOT NULL REFERENCES Donneurs(idDonneur),
    dateDon DATE NOT NULL,
    montant DOUBLE NOT NULL DEFAULT 0
);
CREATE TABLE DonsEspece(
    idDon INT NOT NULL REFERENCES Dons(idDon)
);
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