<h1 align = "center"> Recipe-MS-API </h1>

<p align="center">
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-3.0.5-brightgreen.svg" />
</a>
<a href="Spring Boot url" >
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.0.6-brightgreen.svg" />
</a>

<a >
    <img alt="MySQL" src="https://img.shields.io/badge/MySQL-blue.svg">
</a>
</p>

The Recipe Management System is a web application that allows users to store and manage their recipes. Users can create, view, update, and delete recipes. They can also add comments to recipes and search for recipes based on various criteria.

---
<br>

## Framework Used
* User Registration and Authentication: Users can sign up and log in to the system.
* Recipe CRUD Operations: Users can create, view, update, and delete their recipes.
* Commenting: Users can add comments to recipes created by others.
* Search: Users can search for recipes based on recipe name, ingredients, or any other criteria.

---
<br>

## Dependencies
The following dependencies are required to run the project:

* Spring Boot Dev Tools
* Spring Web
* Spring Data JPA
* MySQL Driver
* Lombok
* Validation
* Swagger -http://16.16.79.112:8080/swagger-ui/index.html#/

<br>

## Technologies Used
* Java (Spring Boot) for the backend server
* MySQL database for data storage
* Spring Data JPA for data access
* Spring Security for user authentication and authorization
* RESTful API for communication with the frontend
* AWS

## Database Configuration
```
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/<DatabaseName>
spring.datasource.username = <userName>
spring.datasource.password = <password>
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true

```
<br>

## Language Used
* Java

---
<br>


## Data Flow

1. The user at client side sends a request to the application through the API endpoints.
2. The API receives the request and sends it to the appropriate controller method.
3. The controller method makes a call to the method in service class.
4. The method in service class builds logic and retrieves or modifies data from the database, which is in turn given to controller class
5. The controller method returns a response to the API.
6. The API sends the response back to the user.

---

<br>


## API End Points

The following endpoints are available in the API:

* User Controller:
```
POST /user/signup: create a new user account
POST /user/signin: authenticate a user and receive an authentication token
DELETE /user/signout: authenticate a user and delete authentication token
```

* Recipe Controller
```
POST /recipe/newRecipe: create to add recipe
GET /recipe/all-recipes: get all recipe
GET /recipe//recipe-by-name: get all recipe by name
PUT /recipe//update-recipe: to update recipe
DELETE /recipe/delete-recipe: to delete reipe

```
* Comment Controller
```
POST /Commetns/newComment: to add comment
Get /Commetns/comments: to get all comment
PUT /Commetns/Edit-comment: to edit comment
DELETE /Commetns/comment: to delete comment

```

<br>

## DataBase Used
* SQL database
```
We have used Persistent database to implement CRUD Operations.
```
---
<br>

## Project Summary

This project is a basic web application that allows users to sign in, sign up, and manage their recipes . Additionally, users can add recipe and view recipe. The application uses authentication tokens to secure user data and ensure that only authenticated users can access certain features of the application.
    
---

## 🤝 Contributing

Contributions, issues and feature requests are welcome!<br />Feel free to check [issues page]("url").
    
---

## Show your support

Give a ⭐️ if this project helped you!
    
---
