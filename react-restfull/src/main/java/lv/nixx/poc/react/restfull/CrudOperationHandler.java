package lv.nixx.poc.react.restfull;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class CrudOperationHandler {

	@Autowired
	private CrudService crudService;

	public Mono<ServerResponse> getAllPersons(ServerRequest request) {

		return ServerResponse.ok()
				.contentType(APPLICATION_JSON)
				.body(fromValue(crudService.getAllPersons()));

	}
	
	public Mono<ServerResponse> getOnePersonById(ServerRequest request) {
		Long id = Long.valueOf(request.pathVariable("id"));
		return ServerResponse.ok()
				.contentType(APPLICATION_JSON)
				.body(fromValue(crudService.getPerson(id)));
	}

	public Mono<ServerResponse> deletePerson(ServerRequest request) {
		crudService.deletePerson(Long.valueOf(request.pathVariable("id")));
		return ServerResponse.ok().build();
	}

}