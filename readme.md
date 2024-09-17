# Vaquejada PRO API

![branches](https://badgen.net/github/branches/EscritorioDeVaquejada/vqr-backend)
![Java](https://img.shields.io/badge/java-%23ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F?style=flat&logo=spring&logoColor=white)

## About the System
The **Vaquejada PRO API** is a REST API designed to modernize the management of ticketing and finances for vaquejada events. The application is developed in **Java**, using **Spring Boot 3**, with the goal of providing a more efficient alternative to the traditional paper-based system.


# Installation Guide

## 1. Running the Application with Docker Compose

### Prerequisites

Ensure the following tools are installed on your system before proceeding:

- **Java**: [JDK 21.0.4](https://www.oracle.com/br/java/technologies/downloads/#java21)
- **Apache Maven**: [3.9.9](https://maven.apache.org/download.cgi)
- **Docker Engine**: [20.10.7 or later](https://docs.docker.com/engine/install/)
- **Docker Compose**: [1.29.2 or later](https://docs.docker.com/compose/install/)

### Installation Steps

#### 1 Build the Project

Navigate to the root directory of the project and run the following command:

```bash
mvn clean package -DskipTests
```
#### 2 Locate the Generated JAR File

Once the build process is complete, navigate to the `/target` directory. A `.jar` file named `demo-0.0.1-SNAPSHOT.jar` will be created. Move or copy this file to the root directory of your application, where the `docker-compose.yaml` file is located.

>Note: The .jar file is necessary for creating a Docker image from the Dockerfile as described in its configuration. Once the Docker image is built, the .jar file is no longer needed.


#### 3. Configure Environment Variables

Optionally, you can set new values for the environment variables in the `docker-compose.yaml` file or use the default values provided.

```yaml
services:
  app:
    image: 'vaquejada-pro-backend:latest'
    build:
      context: .
    container_name: api
    restart: always
    depends_on:
      - db
    environment:
      - SPRING_DATASOURCE_URL=${DATASOURCE_URL:-jdbc:postgresql://db:${DB_PORT:-5432}/${POSTGRES_DB:-db}}
      - SPRING_DATASOURCE_USERNAME=${DATASOURCE_USERNAME:-${POSTGRES_USER:-postgres}}
      - SPRING_DATASOURCE_PASSWORD=${DATASOURCE_PASSWORD:-${POSTGRES_PASSWORD:-admin}}
      - SPRING_JPA_HIBERNATE_DDL_AUTO=${SPRING_JPA_HIBERNATE_DDL_AUTO:-none}
      - SPRING_JPA_OPEN_IN_VIEW=${SPRING_JPA_OPEN_IN_VIEW:-false}
      - SECURITY_JWT_TOKEN_SECRET_KEY=${SECURITY_TOKEN_SECRET_KEY:-fQzvKofPe1erX4xLqt9f/UL15vi2PQh54QJoBT2QGO0=}
      - SECURITY_JWT_TOKEN_EXPIRE_LENGTH=${SECURITY_TOKEN_EXPIRE_LENGTH:-10800000}
      - SECURITY_JWT_REFRESH_TOKEN_EXPIRE_LENGTH=${SECURITY_REFRESH_TOKEN_EXPIRE_LENGTH:-14400000}
      - CORS_ORIGIN_PATTERNS=${CORS_ORIGIN_PATTERNS:-http://localhost:3000,http://localhost:8080,https://vaquejada-pro.com.br}
    ports:
      - ${API_PORT:-8080}:8080

  db:
    image: postgres
    container_name: database
    restart: always
    shm_size: 128mb
    environment:
      - POSTGRES_USER=${POSTGRES_USER:-postgres}
      - POSTGRES_PASSWORD=${POSTGRES_PASSWORD:-admin}
      - POSTGRES_DB=${POSTGRES_DB:-db}
    ports:
      - ${DB_PORT:-5432}:5432
```
>Note: This step is optional. The application should function properly with the default environment variable values. However, ensure that the ports mapped from the containers to the host are not in use by other processes.

#### 4. Start the Application with Docker Compose

In the root directory of your application, use the following command to launch the Docker containers and start the application:

```bash
docker-compose up
```
## 2. Running the Application via Maven or IDE

### Installation Steps

#### 1. Starting the PostgreSQL Database

In the root directory of the application, run the following command to start a Docker container for the PostgreSQL database, as defined in the `docker-compose.yml`:

```bash
docker-compose up db
```

#### 2. Configuring Environment Variables

In the `src/main/resources` directory, create a file named `application-dev.yml` containing the following properties:

```yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/db # Your database url
    username: postgres # Your database username
    password: admin # Your database password

  jpa:
    hibernate:
      ddl-auto: none
    open-in-view: false

security:
  jwt:
    token:
      secret-key: your-secret-key # JWT secret key (any string)
      expire-length: 3600000 # JWT token expiration time (1 hour = 3,600,000 ms)
    refresh-token:
      expire-length: 10800000 # JWT refresh token expiration time (3 hours = 10,800,000 ms)

server:
  port: 8080

cors:
  origin-patterns:
    http://localhost:3000,http://localhost:8080,https://vaquejada-pro.com.br
```
>Note: The file above provides a default, functional configuration. Altering it is optional unless the specified configurations cause compatibility issues, similar problems, or if specific adjustments are needed for your application’s requirements.


#### 3. Running the application

- To run the application via maven, use the following commands:

```bash
mvn clean install -DskipTests
mvn spring-boot:run
```
- Alternatively, you can open the project in an IDE or development tool of your choice that supports Java development with Spring Boot, such as Eclipse, IntelliJ IDEA, or Visual Studio Code.


## Usage Instructions

1. Access `localhost` on the specified port.

2. To obtain an access token, send a `POST` request to the `/auth/login` endpoint with the following credentials. This assumes the application is configured to allow Flyway to create migrations, which is the default behavior as defined in the configuration files.

```json
{
  "username": "original_admin_user",
  "password": "sfsf_34mdv@45_df23gk390d_df@S23%¨#@dfFF"
}
```
If the application has not been set up, you will need to:

- **Register a new user directly in the database** with the `ROLE_ADMIN` permission.

    - Alternatively, you can:

- **Allow access to the `/register` endpoint** for unauthenticated users by configuring the `SecurityConfig.java` file located at `src/main/java/br/com/escritorioDeVaquejada/vqr/config/SecurityConfig.java`.

3. When sending requests to the API, include the access token in the `Authorization` header using the following format:
   `Authorization: Bearer ACCESS_TOKEN`
4. When accessing the API, it's important to ensure that the `Origin` header in your requests matches one of the allowed values defined in the application's CORS configuration: `http://localhost:3000, http://localhost:8080, https://vaquejada-pro.com.br`


## Documentation

### OpenAPI Specification

To access the complete documentation of the application's endpoints, request fields, and response objects, you can visit the `/swagger-ui/index.html` endpoint.

To export the API requests to an API client (if the client supports it), you can access the `/v3/api-docs` endpoint to obtain a JSON with all the application's endpoints.
