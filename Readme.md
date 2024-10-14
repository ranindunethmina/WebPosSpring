# POS System REST API

This is a Point of Sale (POS) system RESTful API built using Spring Web MVC, Spring Data JPA, Hibernate, and MySQL.

## Features
- Manage Customers, Items, Orders, and OrderDetails
- Fully functional RESTful API
- Proper logging and error handling

## Architecture
- **Controller Layer**: Handles HTTP requests and responses.
- **Service Layer**: Business logic implementation.
- **Repository Layer**: Data persistence and retrieval using Spring Data JPA.
- **DTO**: Data Transfer Objects used between client and server.

## Tech Stack
- **Backend**: Java, Spring Web MVC, Spring Data JPA, Hibernate
- **Database**: MySQL
- **Logging**: Logback

## How to Run
1. Clone the repository.
2. Update `application.properties` with your MySQL database configuration.
3. Run the application with your IDE or Maven:
    ```bash
    mvn clean install
    mvn tomcat7:run
    ```

## License
This project is licensed under the MIT License - see the [MIT](LICENSE) file for details.