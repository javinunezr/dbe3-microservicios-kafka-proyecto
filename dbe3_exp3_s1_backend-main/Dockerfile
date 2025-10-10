# Dockerfile para Backend Service
FROM eclipse-temurin:21-jre

# Crear directorio de trabajo
WORKDIR /app

# Copiar el JAR compilado
COPY target/*.jar app.jar

# Copiar el Wallet de Oracle
COPY Wallet_dbe3db/ /app/Wallet_dbe3db/

# Exponer el puerto 8181
EXPOSE 8181

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
