FROM maven:3.9.6-eclipse-temurin-21-alpine

COPY ./Musician/pom.xml ./pom.xml
COPY ./Musician/src ./src

RUN mvn clean package

EXPOSE 2205

ENTRYPOINT ["java", "-jar", "./target/musician-1.0-jar-with-dependencies.jar"]