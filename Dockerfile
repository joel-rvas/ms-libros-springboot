#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
#
# Package stage
#
FROM openjdk:8-alpine
COPY target/exchange-backend-1.0-SNAPSHOT.jar /usr/local/lib/exchange-backend-1.0.0.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/usr/local/lib/exchange-backend-1.0.0.jar"]