FROM adoptopenjdk/openjdk11:alpine-slim
COPY build/libs/satisfactorymanager-*.jar satisfactorymanager.jar
COPY src/main/resources/data/* data/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "satisfactorymanager.jar"]
