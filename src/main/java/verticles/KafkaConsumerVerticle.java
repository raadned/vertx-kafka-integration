package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaConsumerVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumerVerticle.class);

    @Override
    public void start() {
        logger.info("Started kafka-consumer");

        Map<String, String> consumerConfig = new HashMap<>();
        consumerConfig.put("bootstrap.servers", config().getString("host"));
        consumerConfig.put("key.deserializer", config().getString("key.deserializer"));
        consumerConfig.put("value.deserializer", config().getString("value.deserializer"));
        consumerConfig.put("group.id", config().getString("group.id"));
        consumerConfig.put("auto.offset.reset", config().getString("auto.offset.reset"));
        consumerConfig.put("enable.auto.commit", config().getString("enable.auto.commit"));

        // use consumer for interacting with Apache Kafka
        KafkaConsumer<String, String> consumer = KafkaConsumer.create(vertx, consumerConfig);
        consumer.handler(record -> {
            logger.info("Processing key=" + record.key() + ",value=" + record.value() +
                    ",partition=" + record.partition() + ",offset=" + record.offset());
        });
        // listen to events sent on topic
        consumer.subscribe("quickstart-events");
    }

}
