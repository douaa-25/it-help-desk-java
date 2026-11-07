# IT Helpdesk — Java Edition

Réimplémentation Java du projet open source [php-helpdesk](https://github.com/MateuszSzczukiewicz/php-helpdesk).
Le backend de cette version est en **Java 21 / Spring Boot**. Le projet conserve le périmètre fonctionnel principal : authentification, rôles ADMIN/USER, création et suivi des tickets, catégories et statuts.

## Architecture

- `controller/` : routes HTTP et interaction avec les pages
- `service/` : logique métier
- `repository/` : accès aux données avec Spring Data JPA
- `model/` : entités JPA et énumérations
- `config/` : sécurité et initialisation des données
- `templates/` : pages HTML Thymeleaf
- `static/css/` : styles
- `src/test/` : tests JUnit / Mockito
- `.github/workflows/ci.yml` : pipeline GitHub Actions

## Prérequis

- Java 21
- Maven 3.9+ (ou le wrapper Maven)
- MySQL 8+ si le profil MySQL est utilisé

## Lancement rapide dans GitHub Codespaces

```bash
java -version
mvn -version
mvn spring-boot:run
```

Ouvrir le port **8080** dans l'onglet Ports de Codespaces.

La première exécution utilise H2 en mémoire pour faciliter la démonstration.
Comptes de démonstration :
- Admin : `admin` / `Admin123!`
- Utilisateur : `testuser` / `User123!`

Changez ces mots de passe avant toute utilisation réelle.

## Utiliser MySQL

Créer la base `helpdesk_db`, puis définir les variables d'environnement `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, et lancer :

```bash
SPRING_PROFILES_ACTIVE=mysql mvn spring-boot:run
```

Exemple de configuration :
- `DB_URL=jdbc:mysql://localhost:3306/helpdesk_db?createDatabaseIfNotExist=true&serverTimezone=UTC`
- `DB_USERNAME=root`
- `DB_PASSWORD=mot_de_passe`

## Tests et qualité

```bash
mvn clean test
mvn clean verify
```

Les tests couvrent le démarrage du contexte Spring et les règles de séparation des tickets entre utilisateur et administrateur.

## Pipeline CI/CD

Le workflow `.github/workflows/ci.yml` s'exécute sur chaque push vers `main` ou `develop` et sur les pull requests vers `main`.
Étapes : récupération du code, installation de Java 21, cache Maven, compilation, tests et archivage des rapports JUnit.

Cette première pipeline est une pipeline **CI** (intégration continue). Le déploiement automatique (CD) sera ajouté après validation de l'environnement cible.

## Licence et attribution

Ce dépôt est une adaptation pédagogique indépendante du périmètre fonctionnel de php-helpdesk.
Conserver l'attribution et vérifier la licence du dépôt source avant toute redistribution.
