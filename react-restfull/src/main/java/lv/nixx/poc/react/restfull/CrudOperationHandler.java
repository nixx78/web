package lv.nixx.poc.react.restfull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class CrudOperationHandler {

	@Autowired
	private CrudService crudService;

	public Mono<ServerResponse> getAllPersons(ServerRequest request) {

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(crudService.getAllPersons()));

	}

	public Mono<ServerResponse> deletePerson(ServerRequest request) {
		crudService.deletePerson(Long.valueOf(request.pathVariable("id")));
		
		return ServerResponse.ok().build();
	}

}