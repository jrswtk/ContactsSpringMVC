# Contacts (Spring MVC, JSP):

Webapp for saving contacts (emails, phone numbers). Program has an access to the database 
where are saved contacts, users, data of registration and profiles of users. Program allows on create an account which must be confirmed by email. Admin has an access to the management by the accounts of users. Images can be added to contacts. Data of contacts are being added and edit by REST.

### Technologies:

- Java 8
- Spring MVC, Spring Security
- Hibernate (ORM, JPA, validation)
- MySQL
- JSP
- Bootstrap
- AngularJS
- Maven

#### Before the first run:

- Configure the file to the MySQL database: /resources/database.properties

```
spring.datasource.url=jdbc:mysql://localhost:3306/[database_name]
spring.datasource.username=[username]
spring.datasource.password=[password]
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

- Create database schema - execute the sql file: /resources/database_schema.sql
