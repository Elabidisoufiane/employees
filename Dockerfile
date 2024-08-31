# Use Maven to build the application
FROM maven:3.9.4-eclipse-temurin-17 AS builder
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Use OpenJDK image to run the application
FROM openjdk:17-jdk-alpine
COPY --from=builder /app/target/employee-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
