FROM adoptopenjdk/openjdk11:ubi
MAINTAINER diogo

COPY target/casa-do-codigo-0.0.1-SNAPSHOT.jar casa-codigo.jar
ENTRYPOINT [ "java", "-jar", "/casa-codigo.jar" ]