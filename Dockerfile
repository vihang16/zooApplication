FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /zoo-application.jar
ENTRYPOINT ["java","-jar","/zoo-application.jar"]