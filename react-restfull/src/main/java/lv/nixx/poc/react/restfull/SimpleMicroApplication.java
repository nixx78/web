package lv.nixx.poc.react.restfull;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.time.LocalTime;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.netty.http.server.HttpServer;

public class SimpleMicroApplication {

	public static void main(String[] args) throws Exception {

	    HandlerFunction<ServerResponse> hello = request -> ok()
	    		.body( fromObject("Hello, current time is: " + LocalTime.now()));

	    RouterFunction<ServerResponse> router = route(GET("/"), hello);

	    HttpHandler httpHandler = RouterFunctions.toHttpHandler(router);

	    HttpServer.create()
		    .host("localhost")
		    .port(8080)
		    .handle(new ReactorHttpHandlerAdapter(httpHandler))
		    .bind()
		    .block()
		    .onDispose();
	    
	    Thread.currentThread().join();
	}
}
