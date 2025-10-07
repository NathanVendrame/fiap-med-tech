FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY kafka-producer/target/kafka-producer-*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]