# Dockerfile para Eureka Server
FROM eclipse-temurin:21-jre

# Crear directorio de trabajo
WORKDIR /app

# Copiar el JAR compilado
COPY target/*.jar app.jar

# Exponer el puerto 8761
EXPOSE 8761

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
