FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR flight-search
COPY --from=build target/*.jar open-weather.jar
ENTRYPOINT ["java", "-jar", "flight-search.jar"]