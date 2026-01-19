# Stage 1: Build (Compilation)
FROM maven:3.9-eclipse-temurin-23 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime (Exécution) - UPDATED TO 23
FROM eclipse-temurin:23-jre-alpine
WORKDIR /app
COPY --from=build /app/target/projetMPISI-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]