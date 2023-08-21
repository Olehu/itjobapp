FROM  eclipse-temurin:17


COPY build/libs/*.jar /app/app.jar


CMD ["java", "-jar", "/app/app.jar"]
