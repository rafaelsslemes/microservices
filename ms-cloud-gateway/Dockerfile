FROM maven:3.8.6-eclipse-temurin-19 as build

WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests


FROM eclipse-temurin:19-jdk-ubi9-minimal

WORKDIR /app
COPY --from=build /build/target/*.jar ./app.jar

EXPOSE 8080

ARG EUREKA_SERVER=eureka_server
ARG KEYCLOAK_SERVER=localhost
ARG KEYCLOAK_PORT=8081

ENTRYPOINT java -jar ./app.jar