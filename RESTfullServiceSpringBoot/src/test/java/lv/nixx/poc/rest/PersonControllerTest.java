package lv.nixx.poc.rest;

import static org.junit.Assert.*;
import static lv.nixx.poc.rest.PersonFixtures.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import lv.nixx.poc.rest.domain.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment=WebEnvironment.DEFINED_PORT)
public class PersonControllerTest {
	
	private final String URL = "http://localhost:8080/person";

	private RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void addPerson(){
		Person p = new Person("name", "surname", new Date());
	
		ResponseEntity<Person> response = restTemplate.postForEntity(URL, p, Person.class);
		p = response.getBody();
		
		assertNotNull(p);
		String location = response.getHeaders().getFirst("Location");
		HttpStatus statusCode = response.getStatusCode();

		assertEquals("status code", HttpStatus.CREATED, statusCode);
		assertNotNull("location", location ); 
		
		System.out.println("HTTP header 'Location' " + location);
		System.out.println("created person " + p + " status " + statusCode );
	}
	
	@Test
	public void updatePerson(){
		Person p = new Person("new.name", "new.surname", new Date());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(URL + "/{id}", p, p.getId());
	}
	
	@Test
	public void deletePerson(){
		restTemplate.delete(URL + "/{id}", 4);
	}

	@Test
	public void getPersonAsXML(){
		ResponseEntity<String> response = restTemplate.getForEntity(URL + "/{id}/xml", String.class, 1);
		String p = response.getBody();
		
		assertNotNull(p);
		System.out.println("person as XML " + p);
	}
	
	@Test
	public void getPersonAsObject(){
		ResponseEntity<Person> response = restTemplate.getForEntity(URL + "/{id}", Person.class, 2);
		Person p = response.getBody();
		
		assertNotNull(p);
		System.out.println("person: " + p + " code " + response.getStatusCode());
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void tryGetNotExistingPerson(){
		try {
			restTemplate.getForEntity(URL + "/{id}", Person.class, "2000");
		} catch (HttpClientErrorException ex){
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode() );
			System.out.println(ex.getStatusCode() + " " + ex.getStatusText());
			throw ex;
		}
	}
	
	@Test
	public void getPersonAsJSON(){
		ResponseEntity<String> response = restTemplate.getForEntity(URL + "/{id}", String.class, 2);
		String p = response.getBody();
				
		assertNotNull(p);
		System.out.println(response.getStatusCode() + " person: " + p);
	}
	
	@Test
	public void updatePersonAsJSON(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(createPersonJSON(2), headers);
		restTemplate.put(URL + "/{id}", entity, 2);
	}

	@Test
	public void updatePersonAsXML(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		HttpEntity<String> entity = new HttpEntity<String>(createPersonXML(3), headers);
		restTemplate.put(URL + "/{id}", entity, 3);
	}
	
	@Test
	public void addPersonAsJSON(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(createPersonJSON(1), headers);
		restTemplate.postForLocation(URL, entity, String.class);
	}
	
	@Test
	public void addPersonAsXML(){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		
		HttpEntity<String> entity = new HttpEntity<String>(createPersonXML(5),headers);
		restTemplate.postForLocation(URL, entity, String.class);
	}

	@Test
	public void batchRemove() {
		int[] ids =  new int[] { 1, 2, 3, 4 };

		HttpEntity<int[]> entity = new HttpEntity<int[]>(ids);
		URI postForLocation = restTemplate.postForLocation(URL + "/delete", entity, int[].class);
		System.out.println("BatchLocation: " + postForLocation);

		ResponseEntity<String> exchange = restTemplate.exchange(postForLocation, HttpMethod.DELETE, new HttpEntity<String>(""), String.class);
		assertEquals(HttpStatus.OK, exchange.getStatusCode());
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void batchRemove_BatchNotExists() throws URISyntaxException {
		try {
			restTemplate.delete(new URI(URL + "/delete/" + UUID.randomUUID()));
		} catch (HttpClientErrorException t) {
			assertEquals(HttpStatus.NOT_FOUND, t.getStatusCode());
			throw t;
		}
	}
	
	@Test
	public void getAllPersons() {
		ResponseEntity<Person[]> response = restTemplate.getForEntity(URL, Person[].class);
		Person[] persons = response.getBody();
		assertNotNull(persons);
		
		Arrays.stream(persons).forEach(System.out::println);
	}

}
