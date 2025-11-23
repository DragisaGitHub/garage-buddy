# Garage Buddy — Backend

This folder contains the Spring Boot backend for Garage Buddy.

Quick start (local):

```powershell
cd backend
./mvnw spring-boot:run
```

Database configuration is in `src/main/resources/application.yml`. Flyway migrations are in `src/main/resources/db/migration`.

The project follows a layered architecture: `config`, `domain`, `application`, `api`, `infrastructure`.
