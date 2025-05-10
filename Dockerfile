# Use Eclipse Temurin base image for Java 17
FROM eclipse-temurin:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper and project files into the container
COPY . .

# Run Maven to build the project and package the JAR file
RUN ./mvnw clean package -DskipTests

# Expose the port your app will run on (adjust if your app uses a different port)
EXPOSE 8080

# Set the command to run your application JAR
CMD ["java", "-jar", "target/SkillsSwapCommunity-0.0.1-SNAPSHOT.jar"]
