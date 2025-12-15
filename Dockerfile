FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar el JAR
COPY target/laboratorios-0.0.1-SNAPSHOT.jar app.jar

# Copiar el Wallet
COPY Wallet_FullStack3 /app/Wallet_FullStack3

# Variable de entorno Oracle
ENV TNS_ADMIN=/app/Wallet_FullStack3

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
