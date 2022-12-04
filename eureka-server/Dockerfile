# Indicates the MAVEN base image
FROM maven:3.8.6-eclipse-temurin-19 as build

# Creates a directory and points it
WORKDIR /build

# Copies all content from local dir to workdir
COPY . . 

# build Application Jar from source code
RUN mvn clean package -DskipTests


# Indicates the JDK base image
FROM eclipse-temurin:19-jdk-ubi9-minimal

# Creates a directory and points it
WORKDIR /app

# Copies build application from maven container
COPY --from=build ./build/target/*.jar ./app.jar

# Exposes port
EXPOSE 8761

# Executes command on workdir
ENTRYPOINT java -jar app.jar

# TO BUILD IMAGE
# docker build --tag chosenimagename .

# TO RUN
# docker run --name instacename -p 8761:8761 imagename
