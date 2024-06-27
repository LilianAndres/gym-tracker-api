FROM maven:3.9.7-eclipse-temurin-21 AS build_common
WORKDIR /app

COPY pom.xml /app/pom.xml
COPY common/pom.xml /app/common/pom.xml
COPY common/src /app/common/src

RUN mvn -f common/pom.xml clean install -DskipTests

FROM maven:3.9.7-eclipse-temurin-21 AS build_service
WORKDIR /app

COPY --from=build_common /root/.m2 /root/.m2

#ENTRYPOINT ["tail", "-f", "/dev/null"]

COPY pom.xml /app/pom.xml
COPY auth-service/pom.xml /app/auth-service/pom.xml
COPY auth-service/src /app/auth-service/src

RUN mvn -f auth-service/pom.xml clean package -DskipTests

FROM openjdk:21-jdk
WORKDIR /app

COPY --from=build_service /app/auth-service/target/auth-service-0.0.1-SNAPSHOT.jar /app/auth-service-0.0.1-SNAPSHOT.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/auth-service-0.0.1-SNAPSHOT.jar"]
