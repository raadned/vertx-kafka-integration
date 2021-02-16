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

        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", config().getString("host"));
        config.put("key.deserializer", config().getString("key.deserializer"));
        config.put("value.deserializer", config().getString("value.deserializer"));
        config.put("group.id", config().getString("group.id"));
        config.put("auto.offset.reset", config().getString("auto.offset.reset"));
        config.put("enable.auto.commit", config().getString("enable.auto.commit"));

        // use consumer for interacting with Apache Kafka
        KafkaConsumer<String, String> consumer = KafkaConsumer.create(vertx, config);
        consumer.handler(record -> {
            logger.info("Processing key=" + record.key() + ",value=" + record.value() +
                    ",partition=" + record.partition() + ",offset=" + record.offset());
        });
        // listen to events sent on topic
        consumer.subscribe("quickstart-events");
    }

}
