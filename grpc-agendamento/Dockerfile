FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY ../grpc-agendamento/target/grpc-agendamento-*.jar app.jar

EXPOSE 6565
ENTRYPOINT ["java", "-jar", "app.jar"]