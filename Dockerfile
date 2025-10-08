FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY currency-api.jar /app/currency-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "currency-api.jar"]