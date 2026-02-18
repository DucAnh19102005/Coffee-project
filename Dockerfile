FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/coffe-final-project-0.0.1-SNAPSHOT.jar coffeeproject.jar

EXPOSE 8080

CMD ["java", "-jar", "coffeeproject.jar"]