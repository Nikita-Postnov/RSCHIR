FROM openjdk:17-oracle
VOLUME /app
COPY *.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]