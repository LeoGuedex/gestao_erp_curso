# Imagem do Container
FROM openjdk:17-jdk-slim

# Caminho padrão do diretorio
WORKDIR /app

# Copia do JAR da aplicação para o container
COPY target/gestao_erp_curso-1.0.0.jar gestao_erp_curso-1.0.0.jar

# Exposição de Porta
EXPOSE 8080

# Inicio da App
CMD ["java", "-jar", "gestao_erp_curso-1.0.0.jar"]