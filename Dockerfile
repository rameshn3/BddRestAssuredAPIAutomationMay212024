# Use an official Maven image to build the project
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package

# Use an official OpenJDK runtime image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the packaged jar file from the build stage
COPY --from=build /app/target/BddRestAssuredAPIAutomationMay212024-0.0.1-SNAPSHOT.jar app.jar

# Specify the command to run the application
CMD ["java", "-jar", "app.jar"]