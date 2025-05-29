FROM openjdk:17

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} /app/api.jar

EXPOSE 8093

CMD ["java", "-Dspring.profiles.active=development", "-Dserver.port=8093", "-jar", "api.jar"]