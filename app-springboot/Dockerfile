FROM gradle:jdk17 AS build

WORKDIR /app-springboot

COPY --chown=gradle:gradle build.gradle settings.gradle ./
COPY --chown=gradle:gradle src ./src

RUN gradle build -x test

FROM openjdk:17-jdk-slim

WORKDIR /app-springboot

COPY --from=build /app-springboot/build/libs/inha-chatbot.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
