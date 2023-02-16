FROM openjdk:17-jdk-alpine

ARG JAR_FILE=restfull-spring.war
ARG APP_FOLDER=restfull-spring

RUN mkdir ${APP_FOLDER}

COPY ${JAR_FILE} ${APP_FOLDER}/restfull-spring.war

WORKDIR /${APP_FOLDER}

ENTRYPOINT ["java", "-jar", "/resfull-spring/restfull-spring.war"]