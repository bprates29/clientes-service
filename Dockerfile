# Usando uma imagem base do JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado para o container
COPY target/clientes-service-0.0.1-SNAPSHOT.jar app.jar

# Expondo a porta do serviço
EXPOSE 8090

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
