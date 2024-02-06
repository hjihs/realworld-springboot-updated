# syntax = docker/dockerfile:1.2

# syntax=docker/dockerfile:1.2
FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

RUN --mount=type=secret,id=application_yml,dst=/etc/secrets/application.yml cp /etc/secrets/application.yml ./src/main/resources/application.yml
RUN --mount=type=secret,id=public_pem,dst=/etc/secrets/public.pem cp /etc/secrets/public.pem ./src/main/resources/public.pem
RUN --mount=type=secret,id=private_pem,dst=/etc/secrets/private.pem cp /etc/secrets/private.pem ./src/main/resources/private.pem

ENTRYPOINT ["java","-jar","/app.jar"]
