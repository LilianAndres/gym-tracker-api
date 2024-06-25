FROM maven:3.9.7-eclipse-temurin-21-alpine AS common-build
WORKDIR /app/common

COPY common/pom.xml ./
COPY common/src ./src
RUN mvn clean install -DskipTests

FROM maven:3.9.7-eclipse-temurin-21-alpine AS build
WORKDIR /app

COPY auth-service/pom.xml ./pom.xml
COPY auth-service/src ./src

COPY --from=common-build /root/.m2/repository/com/gym/tracker/common/0.0.1-SNAPSHOT/common-0.0.1-SNAPSHOT.jar /root/.m2/repository/com/gym/tracker/common/0.0.1-SNAPSHOT/

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
