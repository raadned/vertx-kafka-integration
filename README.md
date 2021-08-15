# Vert.X - Kafka

Vert.X application interacting with a Kafka topic.

# Project Description

Vert.x service interacting with a Kafka topic.

The main components are:

 - A Vert.X webserver exposing a REST endpoint that will activate the
   Kafka producer.
 - Consumers are listening to the Kafka topic and processing the
   messages. The Kafka consumers are based on Vert.X worker verticles.
 - Project configuration is passed in as JSON

## Dependencies

 -  Kafka  ( instructions to install are available at: https://kafka.apache.org/quickstart )

## Running locally

### Using IntelliJ
Start the project by running the Main class in IntelliJ.

#### Using Docker

`docker build . -t <TAG_NAME>`

`docker start <TAG_NAME>`

## Future work

 - K8 deployments
