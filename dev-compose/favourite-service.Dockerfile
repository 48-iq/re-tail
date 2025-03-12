FROM maven:3.9.9-eclipse-temurin-21-jammy AS build

WORKDIR /core
COPY core/pom.xml pom.xml
RUN mvn dependency:resolve

COPY core/src ./src
RUN mvn install -DskipTests


WORKDIR /app
COPY favourite-service/pom.xml pom.xml
RUN mvn dependency:resolve

COPY favourite-service/src src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre-jammy
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]