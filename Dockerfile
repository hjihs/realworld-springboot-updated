# syntax = docker/dockerfile:1.2

FROM eclipse-temurin:21-jdk-alpine
RUN --mount=type=secret,id=/src/main/resources/application_yml,dst=/etc/secrets/application.yml cat /etc/secrets/application.yml
RUN --mount=type=secret,id=/src/main/resources/public_pem,dst=/etc/secrets/public.pem cat /etc/secrets/public.pem
RUN --mount=type=secret,id=/src/main/resources/private_pem,dst=/etc/secrets/private.pem cat /etc/secrets/private.pem

VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
