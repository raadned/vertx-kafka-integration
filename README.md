

# Vert.X - Kafka

Sample Vert.X application interacting with a Kafka queue.

# Project Description

Sample project to demo the Vert.X Kafka client.

The main components are:
 - A Vert.X webserver exposing a REST endpoint that will invoke the
   Kafka producer.
 - Consumers are then listening to the queue and processing the
   messages. These are modelled as worker verticles.
 - Project configuration is passed in using a json file

## Dependencies

1.  Kafka    ( instructions to install are available here: https://kafka.apache.org/quickstart )


## Running locally

Start the project by running the Main class in Intellij.

## Future work

 - Deploy to Kubernetes
 - Create fat jar