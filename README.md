# A microservices environment case

## A set of applications composing a microservices environment to develop knowlegment around Spring Cloud.

Tech Stack:

* Java 19
* Spring Cloud
* Eureka Server and Clients
* API Gateway, LoadBalancer
* Docker
* RabbitMQ
* OpenFeign
* Keycloak
* Spring Boot
* Spring JPA
* H2 Database
* Lombok

# Required configuration

* In Keycloak Admin Console create a realm named 'microservices'
* Create Client named 'microservices', enable Client Authentication, enable Authorization, on Valid redirect URI set 'localhost:8080'
* On Realm settings set Fronted URL to 'http://keycloak:8080'

* In RabbitMQ create a queue 'credit-card-issue'

# Docker usage

## Build

cd ./ms-customers
docker build --tag ms-customers .

cd ../ms-credit-card
docker build --tag ms-credit-card .

cd ../ms-credit-card
docker build --tag ms-credit-card .

cd ../ms-credit-appraiser
docker build --tag ms-credit-appraiser .

cd ../ms-cloud-gateway
docker build --tag ms-cloud-gateway .

cd ../eureka_server
docker build --tag eureka_server .

## Initialization

docker network create microservices

docker run --name keycloak -p 8081:8080 --network microservices -d -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:20.0.1 start-dev

In Keycloak Admin Console create a queue 'credit-card-issue'

docker run -it --name rabbitmq -p 5672:5672 -p 15672:15672 --network microservices rabbitmq:3.9-management

In RabbitMQ create a queue 'credit-card-issue'

docker run --name ms-eureka-server -p 8761:8761 --network microservices -d ms-eureka-server

docker run --name ms-customers --network microservices -d -e EUREKA_SERVER=ms-eureka-server ms-customers

docker run --name ms-credit-card --network microservices -d -e EUREKA_SERVER=ms-eureka-server -e RABBITMQ_SERVER=rabbitmq ms-credit-card

docker run --name ms-credit-appraiser --network microservices -d -e EUREKA_SERVER=ms-eureka-server -e RABBITMQ_SERVER=rabbitmq ms-credit-appraiser

docker run --name ms-cloud-gateway -p 8080:8080 --network microservices -d -e EUREKA_SERVER=ms-eureka-server -e KEYCLOAK_SERVER=keycloak -e KEYCLOAK_PORT=8080 ms-cloud-gateway

