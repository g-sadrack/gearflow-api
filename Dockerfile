# Build stage
FROM maven:3.9.9-eclipse-temurin-21-alpine as builder
WORKDIR /app
# 1. Copia apenas o POM para resolver dependências primeiro
COPY pom.xml .
# 2. Baixa todas as dependências (cache eficiente)
RUN mvn dependency:go-offline
# 3. Copia todo o código fonte
COPY . .
# 4. Constrói o projeto
RUN mvn clean package -DskipTests
# Production stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]