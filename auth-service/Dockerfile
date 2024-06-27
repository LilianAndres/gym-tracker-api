FROM maven:3.9.7-eclipse-temurin-21-alpine AS build
WORKDIR /app

COPY common/pom.xml /tmp/common/pom.xml
COPY common/src /tmp/common/src
RUN mvn -f /tmp/common/pom.xml clean package -DskipTests

RUN mvn install:install-file \
    -Dfile=/tmp/common/target/common-0.0.1-SNAPSHOT.jar \
    -DgroupId=com.gym.tracker \
    -DartifactId=common \
    -Dversion=0.0.1-SNAPSHOT \
    -Dpackaging=jar

COPY auth-service/pom.xml /app/pom.xml
COPY auth-service/src /app/src
RUN mvn clean package -U -DskipTests

FROM openjdk:21-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
