

# Vert.X - Kafka

Sample Vert.X application interacting with a Kafka queue.

# About

Sample project to demo the Vert.X Kafka client.

 - A Vert.X websever exposes a REST endpoint that will invoke the
   Kafka producer.
 - Consumers are then listening to the queue and processing the
   messages.
 - Configuration is passed as json files

## Dependencies required to run the project

1.  Kafka    ( instructions to install are available here: https://kafka.apache.org/quickstart )


## Running locally

Start the project by running the Main class in Intellij.

## Future work

 - Deploy to Kubernetes