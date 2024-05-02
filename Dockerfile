FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/rober-0.0.1.jar
COPY ${JAR_FILE} app_rober.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_rober.jar"]