FROM adoptopenjdk/openjdk11:alpine-slim
ADD build/libs/satisfactorymanager-*.jar satisfactorymanager.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "satisfactorymanager.jar"]
