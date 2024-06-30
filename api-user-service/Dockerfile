FROM maven:3.9.7-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml /app/pom.xml
COPY src /app/src

RUN mvn clean install -DskipTests

FROM openjdk:21-jdk
WORKDIR /app

COPY --from=build /app/target/api-user-service-0.0.1-SNAPSHOT.jar /app/api-user-service-0.0.1-SNAPSHOT.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app/api-user-service-0.0.1-SNAPSHOT.jar"]
