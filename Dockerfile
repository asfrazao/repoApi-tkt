FROM maven:3.8.6-eclipse-temurin-19-alpine as build

USER root

# ENV MAVEN_CLI_OPTS="-s ./settings.xml --batch-mode"

ENV spring.sql.init.platform="" \
	spring.datasource.url="" \
	spring.datasource.driver-class-name="" \
	spring.datasource.username="" \
	spring.datasource.password="" \
	spring.jpa.show-sql="" \
	spring.jpa.generate-ddl="" \
	spring.jpa.hibernate.ddl-auto="" \
	springdoc.api-docs.path=""

WORKDIR /build

# ADD ./gitlab-credentials/ci/settings.xml /build

COPY ./pom.xml ./pom.xml

RUN mvn dependency:resolve

COPY ./src ./src

RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:19-jdk-slim

WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]

