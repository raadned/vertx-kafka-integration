package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomHttpServer extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(CustomHttpServer.class);

    @Override
    public void start() {
        logger.info("Started the web-server");
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.post().path("/events").handler(this::handleRequest);
        server.requestHandler(router).listen(config().getInteger("port"));
    }

    public void handleRequest(RoutingContext ctx) {
        // This handler will be called for every request
        HttpServerResponse response = ctx.response();
        response.putHeader("content-type", "application/json");

        // Write to the response and end it
        JsonObject message = ctx.getBodyAsJson();
        //logger.info("body is = {}", message);
        vertx.eventBus().send("kafka.producer", new JsonObject().put("payload", message));
        response.end(message.encode());
    }
}
