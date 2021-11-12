package lv.nixx.poc.react.restfull;

import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.time.LocalTime;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN;
import static reactor.core.publisher.Flux.just;

public class HttpServerMicroApplication {

    public static void main(String[] args) {

        DisposableServer server = HttpServer.create()
                .host("localhost")
                .port(8080)
                .route(routes ->
                        routes
                                .get("/hello",
                                        (req, res) -> res.header(CONTENT_TYPE, TEXT_PLAIN)
                                                .sendString(just("Hello, current time is: " + LocalTime.now()))
                                )
                                .get("/message/{text}",
                                        (req, res) -> res.header(CONTENT_TYPE, TEXT_PLAIN)
                                                .sendString(just("Message [" + req.param("text") + "] from user"))
                                )
                ).bindNow();

        server.onDispose().block();
    }

}
