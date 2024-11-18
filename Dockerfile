FROM debian:buster-slim

ARG version=21.0.5.11-1

RUN set -eux \
    && apt-get update \
    && apt-get install -y --no-install-recommends \
        curl ca-certificates gnupg software-properties-common fontconfig java-common \
    && curl -fL https://apt.corretto.aws/corretto.key | apt-key add - \
    && add-apt-repository 'deb https://apt.corretto.aws stable main' \
    && mkdir -p /usr/share/man/man1 || true \
    && apt-get update \
    && apt-get install -y java-21-amazon-corretto-jdk=1:$version \
    && apt-get purge -y --auto-remove -o APT::AutoRemove::RecommendsImportant=false \
        curl gnupg software-properties-common

ENV LANG=C.UTF-8
ENV JAVA_HOME=/usr/lib/jvm/java-21-amazon-corretto

ARG varFile=.env
ARG jarFile=build/libs/echoServer-1.0-SNAPSHOT.jar

RUN mkdir /opt/echoapp
WORKDIR /opt/echoapp

ADD ${jarFile} ${varFile} /opt/echoapp/

EXPOSE 9090

ENV env_file=${varFile}

ENTRYPOINT ["java", "-jar", "/opt/echoapp/echoServer-1.0-SNAPSHOT.jar"]