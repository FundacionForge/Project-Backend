# Usamos la imagen oficial de OpenJDK con Alpine
FROM openjdk:17-jdk-alpine

# Copiamos el archivo WAR generado en el directorio "target" a la imagen con el nombre "java-app.war"
COPY target/forge-0.0.1-SNAPSHOT.war /java-app.war

# Exponemos el puerto en el que la aplicación escucha (esto puede variar dependiendo de tu aplicación)
EXPOSE 8080

# Comando de entrada para ejecutar la aplicación Spring Boot desde el archivo WAR
ENTRYPOINT ["java", "-jar", "/java-app.war"]
