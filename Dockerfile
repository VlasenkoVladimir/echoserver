FROM openjdk:21

ARG jarFile=build/libs/org.example.echoServer-1.0-SNAPSHOT.jar
ARG varFile=.env

WORKDIR /opt/echoapp

COPY ${varFile} /opt/echoapp/
COPY ${jarFile} /opt/echoapp/

EXPOSE 9090

ENV env_file: ${varFile}

ENTRYPOINT ["java", "-jar", "${jarFile}"]