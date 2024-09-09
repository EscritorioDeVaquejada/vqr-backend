# Vaquejada PRO API 

![release](https://badgen.net/github/release/EscritorioDeVaquejada/vqr-backend)
![branches](https://badgen.net/github/branches/EscritorioDeVaquejada/vqr-backend)
![Java](https://img.shields.io/badge/java-%23ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F?style=flat&logo=spring&logoColor=white)

## About the System
The **Vaquejada PRO API** is a REST API designed to modernize the management of ticketing and finances for vaquejada events. The application is developed in **Java**, using **Spring Boot 3**, with the goal of providing a more efficient alternative to the traditional paper-based system.


## Installation

### Prerequisites

- Java [JDK 21.0.4](https://www.oracle.com/br/java/technologies/downloads/#java21)
- Apache Maven [3.6.3](https://maven.apache.org/download.cgi)
- PostgreSQL [14.13](https://www.postgresql.org/download/)

> **Note:** You can use any SQL database supported by [Hibernate](https://github.com/hibernate/hibernate-orm/blob/main/dialects.adoc). However, the migrations located in the `src/main/resources/db/migration` directory were written specifically for PostgreSQL and may cause errors during application startup if used with another database. If you encounter issues, it is recommended to adjust the `spring.jpa.hibernate.ddl-auto` setting in the `application-dev.properties` file to a value other than `none`, which shifts the responsibility for creating tables from Flyway to JPA. Alternatively, you can choose to delete the `db` directory.

### Configuring Environment Variables

In the `src/main/resources` directory, create a file named `application-dev.properties` containing the following properties:

```properties
# Example connection URL: jdbc:postgresql://localhost:5432/your-database
spring.datasource.url=your-database-url
spring.datasource.username=your-database-username
spring.datasource.password=your-database-password

server.port=8080

spring.jpa.hibernate.ddl-auto=none
spring.jpa.open-in-view=false

# JWT secret key (any string)
security.jwt.token.secret-key=your-secret-key
# JWT token expiration time (1 hour = 3,600,000 ms)
security.jwt.token.expire-length=3600000
# JWT refresh token expiration time (3 hours = 10,800,000 ms)
security.jwt.refresh-token.expire-length=10800000
```

### Running
To run the application, use the following commands:

```bash
mvn clean install -DskipTests
mvn spring-boot:run
```

### Usage Instructions

1. Open `localhost` on the specified port.
2. Access the `POST /auth/login` endpoint to obtain an access token by logging in to the application.
3. When sending a request to the API, add the access token to the `Authorization` header with the value `Bearer ACCESS_TOKEN`.

## Documentation

### OpenAPI Specification

To access the complete documentation of the application's endpoints, request fields, and response objects, you can visit the `/swagger-ui/index.html` endpoint.

To export the API requests to an API client (if the client supports it), you can access the `/v3/api-docs` endpoint to obtain a JSON with all the application's endpoints.
