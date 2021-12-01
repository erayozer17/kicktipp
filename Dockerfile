FROM adoptopenjdk/openjdk16
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY target/*.jar app.jar
EXPOSE 80
CMD ["java", "-jar", "app.jar"]