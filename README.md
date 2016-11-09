# Contacts (Spring MVC, JSP):

Webapp to save the contacts (emails, phone numbers). It have access to database, 
where are saved contacts, users, data of registration and users profiles. Program allows on create the accounts, which must be confirmed by email. Admin have access to manage users accounts. To contacts can be added the images. Data of contacts are added and edited using the REST transfer.

### Technologies:

- Java 8
- Spring MVC, Spring Security
- Hibernate (ORM, JPA, validation)
- MySQL
- JSP
- Bootstrap
- AngularJS
- Maven

#### Before a first run:

1. Configure a file to MySQL database: /resources/database.properties

```
spring.datasource.url=jdbc:mysql://localhost:3306/[database_name]
spring.datasource.username=[username]
spring.datasource.password=[password]
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

2. Create database schema:
- Execute a sql file: /resources/database_schema.sql
