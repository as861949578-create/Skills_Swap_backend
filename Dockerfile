FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "SkillsSwapCommunity-0.0.1-SNAPSHOT.jar"]
