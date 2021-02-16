import io.vertx.config.ConfigRetriever;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import verticles.CustomHttpServer;
import verticles.KafkaConsumerVerticle;
import verticles.KafkaProducerVerticle;

public class Main {

    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        ConfigRetriever retriever = ConfigRetriever.create(vertx);

        retriever.getConfig(ar -> {
            if(ar.failed()) {
                logger.info("Could not extract the config");
            } else {
                JsonObject config = ar.result();
                deployVerticles(vertx, config);
            }
        });
    }

    private static void deployVerticles(Vertx vertx, JsonObject config) {
        DeploymentOptions consumerConfig = new DeploymentOptions()
                .setWorker(true)
                .setWorkerPoolName("thread-pool-1")
                .setConfig(config.getJsonObject("kafka").getJsonObject("consumer"))
                .setWorkerPoolSize(4);
        vertx.deployVerticle(KafkaConsumerVerticle.class, consumerConfig);

        DeploymentOptions producerConfig = new DeploymentOptions().setConfig(config.getJsonObject("kafka").getJsonObject("producer"));
        vertx.deployVerticle(KafkaProducerVerticle.class, producerConfig);
        vertx.deployVerticle(new CustomHttpServer());
    }
}
