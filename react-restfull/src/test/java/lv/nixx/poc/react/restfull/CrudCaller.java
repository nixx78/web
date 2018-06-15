package lv.nixx.poc.react.restfull;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class CrudCaller {
	
	private WebClient client = WebClient.create("http://localhost:8080");
	
	
	private Mono<ClientResponse> allPersons = client.get()
			.uri("/persons")
			.accept(MediaType.TEXT_PLAIN)
			.exchange();
	

	public String allPersons() {
		return ">> result = " + allPersons.flatMap(res -> res.bodyToMono(String.class)).block();
	}
	
	
	
}