FROM openjdk:11-jdk-slim
COPY target/config-server-0.0.1-SNAPSHOT.jar configserver.jar
ENTRYPOINT ["java","-jar","configserver.jar"]