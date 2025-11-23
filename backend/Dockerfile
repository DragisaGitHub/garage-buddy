FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . .
RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /workspace/backend/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
