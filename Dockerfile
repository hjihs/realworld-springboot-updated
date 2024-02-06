# syntax = docker/dockerfile:1.2

# syntax=docker/dockerfile:1.2
FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
RUN pwd

RUN mkdir -p /src/main/resources/; \
    cp /etc/secrets/application.yml /src/main/resources/application.yml; \
    cp /etc/secrets/public.pem /src/main/resources/public.pem; \
    cp /etc/secrets/private.pem /src/main/resources/private.pem \

ENTRYPOINT ["java","-jar","/app.jar"]
