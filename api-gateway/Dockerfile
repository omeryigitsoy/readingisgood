FROM openjdk:11-jdk-slim
COPY target/api-gateway-0.0.1-SNAPSHOT.jar apigateway.jar
ENTRYPOINT ["java","-jar","apigateway.jar"]