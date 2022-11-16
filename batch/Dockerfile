FROM openjdk:11-jdk
RUN gradle clean build --no-daemon
ARG JAR_FILE=build/libs/batch-0.0.1-plain.jar
COPY ${JAR_FILE} app.jar


EXPOSE 8000


ENTRYPOINT ["java","-jar","/app.jar"]
#FROM adoptopenjdk/openjdk11
#CMD ["./mvnw", "clean", "package"]
#ARG JAR_FILE_PATH=target/*.jar
#COPY ${JAR_FILE_PATH} app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]