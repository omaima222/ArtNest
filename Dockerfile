FROM  maven:3.9.6-eclipse-temurin-11-alpine AS build

WORKDIR /app

COPY ./pom.xml ./pom.xml

COPY ./src ./src

RUN mvn clean package -DskipTests

FROM openjdk:20-jdk-slim

WORKDIR /app

COPY --from=build /app/target/artnest-0.0.1-SNAPSHOT.jar  /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]