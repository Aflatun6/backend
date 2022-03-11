FROM openjdk:11.0-jdk-slim

ENV API_SERVER_PORT=8090
ENV MANAGEMENT_SERVER_PORT=8091
ENV JAVA_OPTS=""
EXPOSE 8090 8091
USER 1001
COPY build/libs/backend-0.0.1-SNAPSHOT.jar backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75", "-jar", "backend-0.0.1-SNAPSHOT.jar"]
