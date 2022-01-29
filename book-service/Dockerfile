FROM openjdk:11-jdk-slim
COPY target/book-service-0.0.1-SNAPSHOT.jar bookservice.jar
ENTRYPOINT ["java","-jar","bookservice.jar"]