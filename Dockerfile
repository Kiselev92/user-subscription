FROM amazoncorretto:17
WORKDIR /app
COPY target/user-subscription-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]