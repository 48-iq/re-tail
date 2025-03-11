FROM maven:3.9.9-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY gateway/pom.xml ./pom.xml
RUN mvn dependency:resolve

COPY gateway/src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre-jammy
COPY --from=build /app/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]