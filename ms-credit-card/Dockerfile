FROM maven:3.8.6-eclipse-temurin-19 as build

WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests


FROM eclipse-temurin:19-jdk-ubi9-minimal

WORKDIR /app
COPY --from=build ./build/target/*.jar ./app.jar

ARG EUREKA_SERVER=eureka_server
ARG RABBITMQ_SERVER=rabbitmq_server

ENTRYPOINT java -jar -Dspring.profiles.active=release ./app.jar
