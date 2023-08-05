#Build stage
FROM maven:3.8.6-openjdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean compile install

#Package stage
FROM openjdk:17-jdk-alpine
VOLUME /app
EXPOSE 8080
ARG JAR_FILE=target/storageSTC-0.0.1.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]