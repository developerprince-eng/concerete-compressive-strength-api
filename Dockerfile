FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN ulimit -c unlimited
ENTRYPOINT ["java","-jar","/app.jar"]
