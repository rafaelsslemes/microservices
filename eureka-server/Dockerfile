# Indicates the base image
FROM eclipse-temurin:19-jdk-ubi9-minimal

# Creates a directory and points it
WORKDIR /app

# Copies build application and rename it
COPY /target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

# Exposes port
EXPOSE 8761

# Executes command on workdir
ENTRYPOINT java -jar app.jar

# TO BUILD IMAGE
# docker build --tag chosenimagename .

# TO RUN
# docker run --name instacename -p 8761:8761 imagename
