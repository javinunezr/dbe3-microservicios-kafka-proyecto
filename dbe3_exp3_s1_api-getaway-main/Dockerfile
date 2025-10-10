# Dockerfile para API Gateway
FROM eclipse-temurin:21-jre

# Crear directorio de trabajo
WORKDIR /app

# Copiar el JAR compilado
COPY target/*.jar app.jar

# Exponer el puerto 8082
EXPOSE 8082

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
