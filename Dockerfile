# Use Java 17 base image
FROM eclipse-temurin:17-jdk

# Set working directory inside container
WORKDIR /app

# Copy built jar file
COPY target/SkillsSwapCommunity-0.0.1-SNAPSHOT.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]
