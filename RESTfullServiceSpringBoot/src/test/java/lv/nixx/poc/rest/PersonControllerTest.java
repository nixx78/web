package lv.nixx.poc.rest;

import static lv.nixx.poc.rest.PersonFixtures.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import lv.nixx.poc.rest.domain.Person;
import lv.nixx.poc.rest.util.RestRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment=WebEnvironment.DEFINED_PORT)
public class PersonControllerTest {
	
	private final String URL = "http://localhost:8080/person";
	
	private final String adminUserCridentials = "nixx:nixx_pass";
	private final String questUserCridentials = "quest:quest_pass";

	
	@Test
	public void getAllPersons() {
		RestRequest r = RestRequest.builder()
				.toURL(URL)
				.expectedResponseType(Person[].class)
				.build();
		
		ResponseEntity<Person[]> response = r.getForEntity();
		
		assertNotNull(response);
		Arrays.stream(response.getBody()).forEach(System.out::println);
	}
	
	
	@Test
	public void addPerson(){
		Person p = new Person("name", "surname", new Date());
		
		RestRequest r = RestRequest.builder()
				.toURL(URL)
				.withData(p)
				.withBasicAuthentication(adminUserCridentials)
				.expectedResponseType(Person.class)
				.build();
		
		ResponseEntity<Person> response = r.postForEntity();
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
		final int id = 1;

		Person p = new Person("new.name", "new.surname", new Date());
		p.setId(id);
		
		RestRequest r = RestRequest.builder()
				.toURL(URL + "/{id}")
				.withURLVariables(id)
				.withData(p)
				.withBasicAuthentication(adminUserCridentials)
				.expectedResponseType(Person.class)
				.build();
		
		r.putForEntity();
	}

	@Test
	public void deletePerson(){
		
		RestRequest r = RestRequest.builder()
				.toURL(URL + "/{id}")
				.withURLVariables(4)
				.withBasicAuthentication(adminUserCridentials)
				.build();
		
		r.delete();
	}

	@Test
	public void getPersonAsXML(){
		
		RestRequest r = RestRequest.builder()
				.toURL(URL + "/{id}/xml")
				.withURLVariables(1)
				.withBasicAuthentication(adminUserCridentials)
				.expectedResponseType(String.class)
				.build();
		
		ResponseEntity<String> response = r.getForEntity();
		String p = response.getBody();
		
		assertNotNull(p);
		System.out.println("person as XML " + p);
	}
	

	@Test(expected = HttpClientErrorException.class)
	public void tryToAccessResourceWrongCridetntials() {
		try {

			RestRequest r = RestRequest.builder()
					.toURL(URL + "/{id}")
					.withURLVariables(2)
					.withBasicAuthentication("wrong:cridentials")
					.expectedResponseType(Person.class)
					.build();
			r.getForEntity();
		} catch (HttpClientErrorException ex){
			assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode() );
			throw ex;
		}	
	}
//	
//	@Test
//	public void getPersonAsObject(){
//		ResponseEntity<Person> p =  getForEntity(URL + "/{id}", adminUserCridentials, Person.class, 2);
//		
//		assertNotNull(p);
//		System.out.println("person: " + p.getBody());
//		
//		HttpHeaders outHeaders = p.getHeaders();
//		List<String> c = outHeaders.get("Set-Cookie");
//		System.out.println(outHeaders);
//		
//		HttpHeaders  headers = new HttpHeaders();
//        headers.set("Cookie", c.stream().collect(Collectors.joining(";")));
//        
//        HttpEntity<String> request = new HttpEntity<String>(headers);
//
//        p = restTemplate.exchange(URL + "/{id}", HttpMethod.GET, request, Person.class, 2);
//		assertNotNull(p);
//		System.out.println("person: " + p.getBody());
//	}
//	

	@Test(expected = HttpClientErrorException.class)
	public void tryGetNotExistingPerson(){
		try {
			RestRequest r = RestRequest.builder()
					.toURL(URL + "/{id}")
					.withURLVariables(2000)
					.withBasicAuthentication(adminUserCridentials)
					.expectedResponseType(Person.class)
					.build();

			r.getForEntity();
		} catch (HttpClientErrorException ex){
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode() );
			System.out.println(ex.getStatusCode() + " " + ex.getStatusText());
			throw ex;
		}
	}
	
	@Test
	public void getPersonAsJSON(){
		RestRequest r = RestRequest.builder()
				.toURL(URL + "/{id}")
				.withURLVariables(2)
				.withBasicAuthentication(questUserCridentials)
				.expectedResponseType(String.class)
				.build();
		
		ResponseEntity<String> response = r.getForEntity();
		String p = response.getBody();
				
		assertNotNull(p);
		System.out.println(response.getStatusCode() + " person: " + p);
	}
	
	@Test
	public void updatePersonAsJSON(){
		
		final int id = 3;
		RestRequest r = RestRequest.builder()
				.toURL(URL + "/{id}")
				.withURLVariables(id)
				.withData(createPersonJSON(id))
				.withBasicAuthentication(adminUserCridentials)
				.withMediaType(MediaType.APPLICATION_JSON)
				.expectedResponseType(String.class)
				.build();
		
		r.putForEntity();
	}
	
	
	@Test
	public void updatePersonAsXML(){
		
		final int id = 2;
		RestRequest r = RestRequest.builder()
				.toURL(URL + "/{id}")
				.withURLVariables(id)
				.withData(createPersonXML(id))
				.withBasicAuthentication(adminUserCridentials)
				.withMediaType(MediaType.APPLICATION_XML)
				.expectedResponseType(String.class)
				.build();
	
		r.putForEntity();
	}
	


	@Test
	public void batchRemove() {
		int[] ids =  new int[] { 1, 2, 3, 4 };
		
		RestRequest r = RestRequest.builder()
				.toURL(URL + "/delete")
				.withData(ids)
				.withBasicAuthentication(adminUserCridentials)
				.build();

		URI postForLocation = r.postForLocation();
		System.out.println("BatchLocation: " + postForLocation);
		
		r = RestRequest.builder()
				.toURL(postForLocation.toString())
				.withBasicAuthentication(adminUserCridentials)
				.expectedResponseType(String.class)
				.build();
		
		ResponseEntity<String> exchange = r.delete();
		assertEquals(HttpStatus.OK, exchange.getStatusCode());
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void batchRemove_BatchNotExists() {
		try {
			RestRequest r = RestRequest.builder()
					.toURL(URL + "/delete/{batchId}")
					.withURLVariables(UUID.randomUUID())
					.withBasicAuthentication(adminUserCridentials)
					.build();
			
			r.delete();
		} catch (HttpClientErrorException t) {
			assertEquals(HttpStatus.NOT_FOUND, t.getStatusCode());
			throw t;
		}
	}

}
