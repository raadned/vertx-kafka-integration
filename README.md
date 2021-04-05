# Vert.X - Kafka

Sample Vert.X application interacting with a Kafka topic.

# Project Description

Sample project to test the Vert.X - Kafka integration.

The main components are:

 - A Vert.X webserver exposing a REST endpoint that will trigger the
   Kafka producer.
 - Consumers are listening to the queue and processing the
   messages. These are modelled as worker verticles.
 - Project configuration is passed in as json

## Dependencies

 -  Kafka  ( instructions to install are available at: https://kafka.apache.org/quickstart )

## Running locally

Start the project by running the Main class in IntelliJ.

## Future work

 - K8 deployments
