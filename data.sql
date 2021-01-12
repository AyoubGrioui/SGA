DROP DATABASE IF EXISTS SGA;
CREATE DATABASE SGA;
USE SGA;
CREATE TABLE Structures(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom text NOT NULL,
    dateCreation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    email text NOT NULL, 
    adresse text NOT NULL,
    siteWeb text NOT NULL,
    objectif text NOT NULL
    );
CREATE TABLE Depenses(
    id INT PRIMARY KEY AUTO_INCREMENT, 
    idAdherent INT NOT NULL, 
    montant DOUBLE NOT NULL DEFAULT 0,
    dateDepense DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    typeDepense text NOT NULL
    );
CREATE TABLE Adherents(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom text NOT NULL, 
    prenom text NOT NULL,
    cin text NOT NULL, 
    dateNaissance DATETIME NOT NULL, 
    lieuNaissance text NOT NULL,
    dateAdhesion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(), 
    profession text NOT NULL, 
    photo text NOT NULL, 
    sexe text NOT NULL, 
    motDePasse text NOT NULL, 
    telephone text NOT NULL, 
    adresse text NOT NULL, 
    email text NOT NULL
    );
CREATE TABLE LigneFonctions(
    id INT PRIMARY KEY AUTO_INCREMENT,
    dateDebut DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    dateFin DATETIME NOT NULL
    );
CREATE TABLE Fonctions(
    id INT PRIMARY KEY AUTO_INCREMENT,
    role text NOT NULL
    );
CREATE TABLE Donneurs(
    id INT PRIMARY KEY AUTO_INCREMENT,
    email text NOT NULL,
    telephone text NOT NULL, 
    adresse text NOT NULL, 
    motDePasse text NOT NULL
);
CREATE TABLE DonneursPhysiques(
    id INT NOT NULL REFERENCES Donneurs(id),
    cin text NOT NULL, 
    nom text NOT NULL, 
    prenom text NOT NULL
    );
CREATE TABLE DonneursMorals(
    id INT NOT NULL REFERENCES Donneurs(id),
    nom text NOT NULL
    );
CREATE TABLE Dons(
    id INT PRIMARY KEY AUTO_INCREMENT,
    dateDon DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    montant DOUBLE NOT NULL DEFAULT 0
);
CREATE TABLE DonsEspece(
    id INT NOT NULL REFERENCES Dons(id)
    );
CREATE TABLE DonsCheque(
    id INT NOT NULL REFERENCES Dons(id),
    numeroCompteBanque text NOT NULL,
    dateCheque DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    dateDepot DATETIME NOT NULL,
    nomBanque text NOT NULL
    );
CREATE TABLE DonsVersement(
    id INT NOT NULL REFERENCES Dons(id),
    numeroCompteBanque text NOT NULL
    );
