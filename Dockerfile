FROM alpine:edge
VOLUME /tmp
ARG JAR_FILE=target/*.jar
RUN apk add --no-cache openjdk11
COPY ${JAR_FILE} /zoo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/zoo-0.0.1-SNAPSHOT.jar"]