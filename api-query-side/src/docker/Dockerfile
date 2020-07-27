FROM openjdk:8-jdk-alpine
MAINTAINER daniel.carvajal <jdanieldeveloper@gmail.com>

# Refer to Maven build
ARG JAR_FILE=${project.build.directory}/${api-query-side.final.name}.jar
WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]











