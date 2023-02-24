# mvn package
# docker build -t backend .

FROM openjdk:17
MAINTAINER tk_sky
EXPOSE 2023
ADD ./target/GlimmerMeeting-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","app.jar"]