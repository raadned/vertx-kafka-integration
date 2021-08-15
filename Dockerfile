FROM openjdk:11

COPY ./target/vertx-kafka-integration-1.0-SNAPSHOT-fat.jar /tmp/service.jar

COPY /conf/config.json /tmp/conf/config.json

WORKDIR /tmp

CMD ["java", "-jar", "service.jar"]