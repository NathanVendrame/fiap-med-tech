FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY ../grpc-usuario/target/grpc-usuario-*.jar app.jar

EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]