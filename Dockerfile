FROM openjdk:19
WORKDIR /app
COPY target/Nasa_bot-1.0.jar bot.jar
ENTRYPOINT ["java", "-jar", "bot.jar"]
