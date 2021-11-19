# Systéme de Gestion des Associations

## Introduction

L’application SGA permet l’automatisation de la gestion des associations, notre application vous permet de créer une association, créer un compte en tant que président ou secrétaire, d’ajouter des dons, des donateurs, voir la liste des entrées et des sorties et l’imprimer. Elle permet également au donateur de faire le suivi de ses dons.

Réalisé par :

- El-youssefy Omaima
- Elhilali Yassine
- Erguiti Imane
- Grioui Ayoub
- Makhlouk Oussama
- Saidem Mohamed

## Déploiement du projet

- Accéder au dossier du projet puis exécuter à la ligne de commande
- Exécuter les commandes suivantes afin de deploier la base de donnée :
  - `mysql -u root -p `
  - `CREATE USER 'digitals'@'localhost' IDENTIFIED BY 'digitals2021';`
  - `source data.sql`
  - `source default.sql`
  - `GRANT ALL PRIVILEGES ON sga.* TO 'digitals'@'localhost';`

Vous pouvez accéder au projet via `http://localhost:8080/SGA/homePage` .

### Pour accéder à l'espace de secrétaire et président

- Secrétaire :
  - mail : `monsec@gmail.com`
  - mot de passe : `password`
- Président :
  - mail : `monpres@gmail.com`
  - mot de passe : `password`
