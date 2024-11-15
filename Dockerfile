FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/exchangerate-app-0.0.1-SNAPSHOT.jar /app/exchangerate-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/exchangerate-app.jar"]