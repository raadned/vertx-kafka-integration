package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(KafkaProducerVerticle.class);

    @Override
    public void start() {
        logger.info("Started the producer verticle");
        EventBus bus = vertx.eventBus();
        // kafka config producer

        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", config().getString("host"));
        config.put("key.serializer", config().getString("key.serializer"));
        config.put("value.serializer", config().getString("value.serializer"));
        config.put("acks", config().getString("acks"));

        // use producer for interacting with Apache Kafka
        KafkaProducer<String, String> producer = KafkaProducer.create(vertx, config);

        // event bus config
        bus.<JsonObject>consumer("kafka.producer", msg -> {
            JsonObject body = msg.body();
            //logger.info("Received message = {}", body);
            KafkaProducerRecord<String, String> record = KafkaProducerRecord.create("quickstart-events", "Radu", body.getString("payload"));
            producer.write(record);

        });

    }


}
