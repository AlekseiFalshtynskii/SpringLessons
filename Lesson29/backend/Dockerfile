#1
FROM maven:3.6.1-jdk-8-alpine

WORKDIR /backend

COPY pom.xml .

COPY src ./src

RUN mvn package -DskipTests

#2
FROM openjdk:8-jdk-alpine

WORKDIR /backend

COPY --from=0 /backend/target/*.jar backend.jar

EXPOSE 8080

CMD java -jar backend.jar
