package lv.nixx.poc.rest;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JaxRsSampleApp.class)
@WebAppConfiguration
@IntegrationTest("server.port=8080")
public class RestIntegrationTest {

    private RestTemplate restTemplate = new TestRestTemplate();
    
    @Test
    public void postAndGetPerson() {

    	final Person p = new Person("100", "Name1", "Surname1" );

    	URI uri = restTemplate.postForLocation("http://localhost:8080/person", p);
    	
    	ResponseEntity<Person> responseEntity = restTemplate.getForEntity(uri, Person.class);
    	Person ep = responseEntity.getBody();
    	
    	assertEquals(p, ep);
    }
    
    @Test
    public void getAllPersons() {
    	
    	final Person p1 = new Person("100", "Name100", "Surname100" );
    	final Person p2 = new Person("101", "Name101", "Surname101" );
    	final Person p3 = new Person("102", "Name102", "Surname102" );

    	
    	restTemplate.postForLocation("http://localhost:8080/person", p1);
    	restTemplate.postForLocation("http://localhost:8080/person", p2);
    	restTemplate.postForLocation("http://localhost:8080/person", p3);
    	
    	
    	ResponseEntity<List<Person>> re = getPersons("http://localhost:8080/person");
    	
    	List<Person> content = re.getBody();

    	System.out.println(content);
    }

    private ResponseEntity<List<Person>> getPersons(String uri) {
    	return restTemplate.exchange(uri, HttpMethod.GET, null, getParameterizedPageTypeReference());
    }

    private ParameterizedTypeReference<List<Person>> getParameterizedPageTypeReference() {
        return new ParameterizedTypeReference<List<Person>>() {};
    }
    
}