# Projet Java Spring : Jeux MIAGiques

# Contexte de l’application à réaliser :
Le but de ce projet est de concevoir et développer une application pour les Jeux MIAGiques 2024, version « marathon » des Jeux Olympiques puisqu’il peut y avoir des épreuves tout au long de l’année. Cette application doit permettre à des spectateurs de réserver des billets pour assister aux différentes épreuves, aux participants de s'inscrire aux épreuves pour défendre leur délégation et aux organisateurs de vérifier les billets, de superviser les événements et de maintenir à jour les classements

# Configuration
Ce TP utilise une base de données MySQL qu'on peut créer sous Docker avec la commande :
###### docker run --name=BDMIAGique -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=BDMIAGique -p 3306:3306 mysql

Pour relancer la base de données créée précédemment, il faut exécuter la commande suivante :
###### docker start BDMIAGique

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


