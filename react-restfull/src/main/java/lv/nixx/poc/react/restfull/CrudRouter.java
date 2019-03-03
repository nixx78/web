package lv.nixx.poc.react.restfull;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.time.LocalTime;

import static org.springframework.http.MediaType.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CrudRouter {

	@Bean
	public RouterFunction<ServerResponse> route(CrudOperationHandler crudHandler) {
		
		return RouterFunctions.route(GET("/person").and(accept(APPLICATION_JSON)), crudHandler::getAllPersons)
			   .andRoute(GET("/person/{id}")
			   .and(accept(APPLICATION_JSON)), crudHandler::getOnePersonById)
			   .andRoute(DELETE("/person/{id}").and(accept(APPLICATION_JSON)), crudHandler::deletePerson);
	}
	
	@Bean
	public RouterFunction<ServerResponse> routeHello() {
		
	    HandlerFunction<ServerResponse> hello = request -> ok()
	    		.body( fromObject("Hello, current time is: " + LocalTime.now()));
	    
		return RouterFunctions.route(GET("/timestamp"), hello);
	}

	
}