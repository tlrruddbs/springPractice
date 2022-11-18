FROM openjdk:11-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]



#ARG JAR_FILE=/var/jenkins_home/workspace/aws/batch/build/libs/batch-0.0.1.jar \
#COPY ${JAR_FILE} app.jar
#
#EXPOSE 8000
#
#ENTRYPOINT ["java","-jar","/app.jar"]