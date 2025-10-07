# ---------- Build global ----------
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests \
 && mkdir /app/jars \
 && cp grpc-usuario/target/*.jar /app/jars/ \
 && cp grpc-agendamento/target/*.jar /app/jars/ \
 && cp aggregator-service/target/*.jar /app/jars/ \
 && cp kafka-consumer/target/consumer-*.jar /app/jars/ \
 && cp kafka-producer/target/producer-*.jar /app/jars/


# ---------- Runtime grpc-usuario ----------
FROM eclipse-temurin:21-jdk AS grpc-usuario
WORKDIR /app
COPY --from=builder /app/jars/grpc-usuario-*.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]

# ---------- Runtime grpc-agendamento ----------
FROM eclipse-temurin:21-jdk AS grpc-agendamento
WORKDIR /app
COPY --from=builder /app/jars/grpc-agendamento-*.jar app.jar
EXPOSE 6565
ENTRYPOINT ["java", "-jar", "app.jar"]

# ---------- Runtime aggregator-service ----------
FROM eclipse-temurin:21-jdk AS aggregator-service
WORKDIR /app
COPY --from=builder /app/jars/aggregator-service-*.jar app.jar
# Instala dockerize (binário leve para esperar dependências)
RUN apt-get update && apt-get install -y wget \
  && wget https://github.com/jwilder/dockerize/releases/download/v0.7.0/dockerize-linux-amd64-v0.7.0.tar.gz \
  && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-v0.7.0.tar.gz \
  && rm dockerize-linux-amd64-v0.7.0.tar.gz \
  && apt-get clean && rm -rf /var/lib/apt/lists/*
EXPOSE 8080
ENTRYPOINT ["dockerize", "-wait", "tcp://grpc-usuario:9090", "-wait", "tcp://grpc-agendamento:6565", "-timeout", "60s", "java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jdk AS kafka-consumer
WORKDIR /app
COPY --from=builder /app/jars/consumer-*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jdk AS kafka-producer
WORKDIR /app
COPY --from=builder /app/jars/producer-*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]