FROM openjdk:17-jdk-alpine
LABEL maintainer="hh3"
ADD target/splitau-0.0.3-SNAPSHOT.jar splitautest.jar
ENTRYPOINT ["java", "-jar", "splitautest.jar"]

