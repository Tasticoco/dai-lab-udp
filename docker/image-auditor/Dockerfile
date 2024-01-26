FROM maven:3.9.6-eclipse-temurin-21-alpine

COPY ./Auditor/pom.xml ./pom.xml
COPY ./Auditor/src ./src

RUN mvn clean package

EXPOSE 2205

ENTRYPOINT ["java", "-jar", "./target/Auditor-1.0-jar-with-dependencies.jar"]