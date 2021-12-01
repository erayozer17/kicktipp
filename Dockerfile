FROM adoptopenjdk/openjdk16
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY src/*.jar app.jar
EXPOSE 80
CMD ["java","-Dserver.port=80", "-jar", "app.jar"]