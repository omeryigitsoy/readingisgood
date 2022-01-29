FROM openjdk:11-jdk-slim
COPY target/customer-service-0.0.1-SNAPSHOT.jar customerservice.jar
ENTRYPOINT ["java","-jar","customerservice.jar"]