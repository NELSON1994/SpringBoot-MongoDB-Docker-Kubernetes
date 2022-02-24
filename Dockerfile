FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
LABEL version="1.0"
LABEL description="Sample dockerized stuent demo details app"
WORKDIR /opt
ENV PORT 3333
EXPOSE 3333
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar