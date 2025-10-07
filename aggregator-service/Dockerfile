FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia o jar do aggregator
COPY ../aggregator-service/target/aggregator-service-*.jar app.jar

# Instala dockerize (binário leve para esperar dependências)
RUN apt-get update && apt-get install -y wget \
  && wget https://github.com/jwilder/dockerize/releases/download/v0.7.0/dockerize-linux-amd64-v0.7.0.tar.gz \
  && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-v0.7.0.tar.gz \
  && rm dockerize-linux-amd64-v0.7.0.tar.gz \
  && apt-get clean && rm -rf /var/lib/apt/lists/*

EXPOSE 8080

ENTRYPOINT ["dockerize", "-wait", "tcp://grpc-usuario:9090", "-wait", "tcp://grpc-agendamento:6565", "-timeout", "60s", "java", "-jar", "app.jar"]