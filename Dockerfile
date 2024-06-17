FROM eclipse-temurin:21-jdk as build

COPY . /app
WORKDIR /app

RUN chmod +x mvmw

RUN ./mvnw package -DskipTest
RUN mv -f target/*.jar app.jar

FROM eclipse-temurin:21-jdk-nanoserverjre

ARG PORT
ENV PORT=${PORT}

COPY --from=build /app/app.jar .

RUN useradd runtime

USER runtime

ENTRYPOINT [ "java", "_Dserver.port=${PORT}", "-jar", "app.jar" ]