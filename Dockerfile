# Usa a imagem base do OpenJDK 21 (Java 23 não tem suporte estável ainda)
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado pelo Gradle para dentro do container
COPY build/libs/*.jar app.jar

# Expõe a porta da aplicação (deve ser a mesma usada no `application.properties`)
EXPOSE 8080

# Comando para rodar a aplicação dentro do container
ENTRYPOINT ["java", "-jar", "app.jar"]
