# Usar una imagen base con Maven y JDK 11 para construir la aplicación
FROM maven:3.8.4-jdk-17 AS build

# Establecer un directorio de trabajo
WORKDIR /app

# Copiar archivos de tu proyecto al directorio de trabajo
COPY . /app

# Ejecutar Maven para construir el proyecto
RUN mvn clean package

# Crear una nueva imagen basada en OpenJDK 11 para la aplicación Spring Boot
FROM openjdk:11-jre-slim-buster

# Establecer un directorio de trabajo diferente para la imagen final
WORKDIR /app

# Copiar el archivo WAR construido desde la etapa anterior
COPY --from=build /app/target/forge-0.0.1-SNAPSHOT.war /app/forge-0.0.1-SNAPSHOT.war

# Exponer el puerto que utilizará la aplicación (si es necesario)
EXPOSE 8080

# Comando de entrada para ejecutar la aplicación Spring Boot desde el archivo WAR
ENTRYPOINT ["java", "-jar", "/app/forge-0.0.1-SNAPSHOT.war"]
