# Description:
Gaming Service is a Spring Boot REST project with CRUD operations and some business logic.
There are 4 entities in the project: **User**, **UserProfile**, **Game** and **Feedback**, these entities have such relations as: <br/>
1. Each **User** must have a **UserProfile** with some additional information **(OneToOne shared primary key)**
2. **User** can buy any **Game** if the balance allow to do it **(ManyToMany)**
3. **User** can leave a feedback about a bought **Game**, it means that **User** and **Game** have list of **Feedback** **(OneToMany)**

## Getting started:
1. Run [**gaming-service.sql**](https://github.com/PetroPochynok/gaming-service/blob/master/sql-scripts/gaming-service.sql) to create database with appropriate tables with some inserted records.
2. Set up your own database properties in [**application.properties**](https://github.com/PetroPochynok/gaming-service/blob/master/src/main/resources/application.properties) (**username** and **password**) to make everything work.
3. Use some http client to test functionality of the project, for example you can use Postman.

## Used technologies:

* Java 11
* Spring Boot
* Spring Data Jpa/Hibernate
* MySQL
* Lombok
* MapStruct

## Database structure:
<img src="https://i.imgur.com/vp79LSs.png"/>

## Example of using Postman:
<img src="https://i.imgur.com/OGLsjAe.png"/>
<hr />
<img src="https://i.imgur.com/2irarEm.png"/>
<hr />
<img src="https://i.imgur.com/5vNzAMA.png"/>
