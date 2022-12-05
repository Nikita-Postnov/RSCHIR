FROM openjdk:11-oracle
WORKDIR /app
COPY /build/libs/cursov_p-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]