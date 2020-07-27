FROM openjdk:8-jdk-alpine
MAINTAINER daniel.carvajal <jdanieldeveloper@gmail.com>

# Refer to Maven build
ARG JAR_FILE=target/api-query-side.jar
WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]











