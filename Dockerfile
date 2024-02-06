# syntax = docker/dockerfile:1.2

FROM eclipse-temurin:21-jdk-alpine
RUN --mount=type=secret,id=/src/main/resources/application.yml,dst=/etc/secrets/application_yml cat /etc/secrets/application_yml
RUN --mount=type=secret,id=/src/main/resources/public.pem,dst=/etc/secrets/public_pem cat /etc/secrets/public_pem
RUN --mount=type=secret,id=/src/main/resources/private.pem,dst=/etc/secrets/private_pem cat /etc/secrets/private_pem

VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
