package lv.nixx.poc.rest;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.util.Base64Utils;

import org.springframework.web.client.RestTemplate;

public class RestRequest {
	
	private Object data;
	private String url;
	private Object[] urlVariables = new Object[]{};
	private Class<?> responseType;
	
	private final HttpHeaders headers = new HttpHeaders();
	
	public <T> ResponseEntity<T> getForEntity() {
		return execute(HttpMethod.GET);
	}
	
	public <T> ResponseEntity<T> postForEntity() {
		return execute(HttpMethod.POST);
	}
	
	public  <T> ResponseEntity<T> putForEntity() {
		return execute(HttpMethod.PUT);
	}

	public <T> ResponseEntity<T> delete() {
		return execute(HttpMethod.DELETE);
	}
	
	public URI  postForLocation() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForLocation(url, new HttpEntity<>(data, headers));
	}

	@SuppressWarnings("unchecked")
	private <T> ResponseEntity<T> execute(HttpMethod method) {
		RestTemplate restTemplate = new RestTemplate();
		return (ResponseEntity<T>) restTemplate.exchange(url, method, new HttpEntity<>(data, headers), responseType, urlVariables);
	}
	
	
	static Builder builder() {
		return new RestRequest().new Builder();
	}
	
	class Builder {
		
		Builder toURL(String url) {
			RestRequest.this.url = url;
			return this;
		}
		
		Builder  withData(Object data) {
			RestRequest.this.data = data;
			return this;
		}

		Builder expectedResponseType(Class<?> responseType) {
			RestRequest.this.responseType = responseType;
			return this;
		}

		Builder withURLVariables(Object... urlVariables) {
			RestRequest.this.urlVariables = urlVariables;
			return this;
		}

		Builder withMediaType(MediaType mediaType) {
			headers.setContentType(mediaType);
			return this;
		}

		Builder withBasicAuthentication(String credentials) {
			String base = Base64Utils.encodeToString(credentials.getBytes());
			headers.add("Authorization", "Basic " + base);
			return this;
		}

		RestRequest build() {
			return RestRequest.this;
		}
	}

	
}
