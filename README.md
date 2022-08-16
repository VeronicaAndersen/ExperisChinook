# Java Assignment 2 - Superhero database & Chinook database
First part of the project creates scripts that's located in the folder Scripts. The script folder contains superheros, assistants & superpowers with relations to each other. 
Second part was build with Spring Boot in Java. This part is a Java application that reads & manipulating data with JDBC. This part is like a re-make of iTunes called Chinnook. 

## Installation 

### Repository
The application is free to clone straight from gitlab. Type this into your selected git console to get the current main version: 
```
git clone git@gitlab.com:VeronicaAndersen/java-2-chinook.git
```

### IDE software
To run and use this repository you can use a software that you like. This one was build in IntelliJ IDEA.
The scripts were created in PostgreSQL with pgAdmin 4.

## Setting up database connection
In the file located in src/main/resources/application.properties enter the following:
```
spring.datasource.url= jdbc:postgresql://localhost:5432/<NameOfYourDatabase>
spring.datasource.username= <UsernameForDatabase>
spring.datasource.password= <PasswordForDatabase>
```

## Contributors
Huwaida Alhamdawee @Huwaida-al & Veronica Andersen @VeronicaAndersen
