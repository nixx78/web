package lv.nixx.poc.rest;

import lv.nixx.poc.rest.domain.Action;
import lv.nixx.poc.rest.domain.Operation;
import lv.nixx.poc.rest.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static lv.nixx.poc.rest.PersonFixtures.createPersonJSON;
import static lv.nixx.poc.rest.PersonFixtures.createPersonXML;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestAppRunner.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class PersonControllerTest {

    final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    private final String URL = "http://localhost:8080/rest-spring/rest/person";
    private final String adminUserCredentials = "nixx:nixx_pass";

    @Value("${server.port}")
    private int serverPort;

	@Test
	public void getAllPersons() {

        System.out.println(serverPort);

        RestRequest r = RestRequest.builder()
				.toURL(URL)
				.expectedResponseType(Person[].class)
				.build();

		ResponseEntity<Person[]> response = r.getForEntity();

		assertNotNull(response);
		Arrays.stream(response.getBody()).forEach(System.out::println);
	}

    @Test
    public void addPerson() throws ParseException {
        Person p = new Person("name", "surname", df.parse("06.12.1990"));

        RestRequest r = RestRequest.builder()
                .toURL(URL)
                .withData(p)
                .withBasicAuthentication(adminUserCredentials)
                .expectedResponseType(Person.class)
                .build();

        ResponseEntity<Person> response = r.postForEntity();
        p = response.getBody();

        assertNotNull(p);
        String location = response.getHeaders().getFirst("Location");
        HttpStatus statusCode = response.getStatusCode();

        assertEquals("status code", HttpStatus.CREATED, statusCode);
        assertNotNull("location", location);

        System.out.println("HTTP header 'Location' " + location);
        System.out.println("created person " + p + " status " + statusCode);
    }

    @Test
    public void processActions() throws ParseException {

        List<Action<String, Person>> actions = Arrays.asList(
                new Action<>(null, new Person("name", "surname", df.parse("06.12.1990")), Operation.ADD),
                new Action<>("10", new Person("name", "surname", df.parse("06.12.1990")), Operation.UPDATE),
                new Action<>("10", null, Operation.DELETE)
        );

        RestRequest r = RestRequest.builder()
                .toURL(URL + "/processActions")
                .withData(actions)
                .withBasicAuthentication(adminUserCredentials)
                .expectedResponseType(List.class)
                .build();

        ResponseEntity<List<Action<String, Person>>> response = r.postForEntity();
        HttpHeaders headers = response.getHeaders();

        Set<String> keys = headers.keySet();
        System.out.println("Keys: " + keys);
        for (String k : keys) {
            System.out.println("Key: " + k + ":value" + headers.get(k));
        }

        String[] cookie = String.join(";", headers.get(HttpHeaders.SET_COOKIE)).split(";");
        System.out.println("==== Cookie ====");
        for (String c : cookie)
            System.out.println(c.trim());
        System.out.println("==== Response body ====");
        System.out.println(response.getBody());
        System.out.println("=======================");
    }

    @Test
    public void updatePerson() throws ParseException {
        final int id = 1;

        Person p = new Person("new.name", "new.surname", df.parse("06.12.1990"));
        p.setId(id);

        RestRequest r = RestRequest.builder()
                .toURL(URL + "/{id}")
                .withURLVariables(id)
                .withData(p)
                .withBasicAuthentication(adminUserCredentials)
                .expectedResponseType(Person.class)
                .build();

        r.putForEntity();
    }

	@Test
	public void deletePerson() {

		RestRequest r = RestRequest.builder()
				.toURL(URL + "/{id}")
				.withURLVariables(4)
				.withBasicAuthentication(adminUserCredentials)
				.build();

		r.delete();
	}

    @Test
    public void getPersonAsXML() {

        RestRequest r = RestRequest.builder()
                .toURL(URL + "/{id}/xml")
                .withURLVariables(1)
                .withBasicAuthentication(adminUserCredentials)
                .expectedResponseType(String.class)
                .build();

        ResponseEntity<String> response = r.getForEntity();
        String p = response.getBody();

        assertNotNull(p);
        System.out.println("person as XML " + p);
    }

    @Test(expected = HttpServerErrorException.class)
    public void tryGetNotExistingPerson() {
        try {
            RestRequest r = RestRequest.builder()
                    .toURL(URL + "/{id}")
                    .withURLVariables(2000)
                    .withBasicAuthentication(adminUserCredentials)
                    .expectedResponseType(Person.class)
                    .build();

            r.getForEntity();
        } catch (HttpServerErrorException ex) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatusCode());
            System.out.println(ex.getStatusCode() + " " + ex.getStatusText());
            throw ex;
        }
    }

    @Test
    public void getPersonAsJSON() {
        String questUserCredentials = "quest:quest_pass";
        RestRequest r = RestRequest.builder()
                .toURL(URL + "/{id}")
                .withURLVariables(2)
                .withBasicAuthentication(questUserCredentials)
                .expectedResponseType(String.class)
                .build();

        ResponseEntity<String> response = r.getForEntity();
        String p = response.getBody();

        assertNotNull(p);
        System.out.println(response.getStatusCode() + " person: " + p);
    }

    @Test
    public void updatePersonAsJSON() throws ParseException {

        final int id = 3;

        RestRequest r = RestRequest.builder()
                .toURL(URL + "/{id}")
                .withURLVariables(id)
                .withData(createPersonJSON(id))
                .withBasicAuthentication(adminUserCredentials)
                .withMediaType(MediaType.APPLICATION_JSON)
                .expectedResponseType(String.class)
                .build();

        r.putForEntity();
    }


    @Test
    public void updatePersonAsXML() {

        final int id = 2;
        String personXML = createPersonXML(id);
        RestRequest r = RestRequest.builder()
                .toURL(URL + "/{id}")
                .withURLVariables(id)
                .withData(personXML)
//                .withBasicAuthentication(adminUserCredentials)
                .withMediaType(MediaType.APPLICATION_XML)
                .expectedResponseType(String.class)
                .build();

        r.putForEntity();
    }

    @Test
    public void batchRemove() {
        int[] ids = new int[]{1, 2, 3, 4};

        RestRequest r = RestRequest.builder()
                .toURL(URL + "/delete")
                .withData(ids)
                .withBasicAuthentication(adminUserCredentials)
                .build();

        URI postForLocation = r.postForLocation();
        System.out.println("BatchLocation: " + postForLocation);

        r = RestRequest.builder()
                .toURL(postForLocation.toString())
                .withBasicAuthentication(adminUserCredentials)
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
                    .withBasicAuthentication(adminUserCredentials)
                    .build();

            r.delete();
        } catch (HttpClientErrorException t) {
            assertEquals(HttpStatus.NOT_FOUND, t.getStatusCode());
            throw t;
        }
    }

    @Test
    public void loginRequest() {

        RestRequest r = RestRequest.builder()
                .toURL("http://localhost:8080/rest-spring/login")
                .build();

        ResponseEntity<String> response = r.postForEntity();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
