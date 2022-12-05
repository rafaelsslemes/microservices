FROM maven:3.8.6-eclipse-temurin-19 as build

WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests


FROM eclipse-temurin:19-jdk-ubi9-minimal

WORKDIR /app
COPY --from=build ./build/target/*.jar ./app.jar

# Receive environment variables or use default
ARG EUREKA_SERVER=localhost

ENTRYPOINT java -jar ./app.jar
