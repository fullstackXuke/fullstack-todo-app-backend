FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/backend.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
