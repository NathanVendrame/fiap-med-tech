FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY kafka-consumer/target/kafka-consumer-*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]