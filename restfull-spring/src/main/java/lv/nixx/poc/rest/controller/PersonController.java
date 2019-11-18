package lv.nixx.poc.rest.controller;

import java.util.*;

import lv.nixx.poc.rest.PersonDAO;
import lv.nixx.poc.rest.domain.Action;
import lv.nixx.poc.rest.domain.Person;
import lv.nixx.poc.rest.domain.Status;

import lv.nixx.poc.rest.exception.PersonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/" + PersonController.BASE_URL)
@Api(value="CRUD Operations for entity Person")
public class PersonController {

	private static final Logger log = LoggerFactory.getLogger(PersonController.class);

	static final String BASE_URL = "rest/person";

	private PersonDAO personDAO;

	@Autowired
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@PostMapping
	public ResponseEntity<Person> addPerson(@RequestBody Person p, UriComponentsBuilder builder) {
		log.debug("Adding person [{}]", p);
		
		personDAO.save(p);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(p.getId()).toUri());
		
		return new ResponseEntity<>(p, headers, CREATED);
	}
	
	@PostMapping(value="processActions", consumes="application/json")
	public List<Action<String, Person>> processActions(@RequestBody List<Action<String, Person>> actions, HttpServletResponse response) {
		log.debug("Actions [{}]", actions);
		actions.forEach( t -> t.setStatus(Status.SUCCESS));
		response.addCookie(new Cookie("server.cookie1", "cookie1.value"));
		response.addCookie(new Cookie("server.cookie2", "cookie2.value"));
		return actions;
	}
	
	@GetMapping(value="/{id}/xml", produces="application/xml")
	public Person getPersonAsXML(@PathVariable int id) {
		log.debug("Get person by id [{}] as XML", id);
		return personDAO.getById(id);
	}
	
	@GetMapping(value="/{id}", produces="application/json")
	public Person getPerson(@PathVariable(name="id") int id) {
		log.debug("Get person by id [{}]", id);
		return personDAO.getById(id);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.HEAD)
	public ResponseEntity<String> getPersonHeader(@PathVariable(name="id") int id) {
		log.debug("Get person headers (HEAD) by id [{}]", id);
		HttpStatus status = HttpStatus.OK;
		try {
			personDAO.getById(id);
		} catch (PersonNotFoundException ex) {
			status= HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(status);
	}

	@ApiOperation(value="Method return all Persons", responseContainer = "List")
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public Person[] getAllPersons() {
		log.debug("Get all persons");
		Collection<Person> allPersons = personDAO.getAllPersons();
		return allPersons.toArray(new Person[0]);
	}

	@PutMapping("/{id}")
	public Person updatePerson(@RequestBody Person person, @PathVariable String id) {
		log.debug("Update person, id [{}]", id);
		personDAO.update(person);
		return person;
	}
		
	@DeleteMapping("/{id}")
	public void removePerson(@PathVariable Integer id) {
		log.debug("remove person, id [{}]", id);
		personDAO.delete(id);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> postPersonRemoveBatch(@RequestBody Integer[] ids, UriComponentsBuilder builder) {

		log.debug("Ids for delete [{}]", Arrays.toString(ids));

		UUID batchId = personDAO.addToDeleteBatch(ids);
		
		log.debug("Add persons to batch id [{}]", batchId);
		
        final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path(BASE_URL + "/delete/{id}").buildAndExpand(batchId).toUri());
		
		return new ResponseEntity<>(batchId.toString(), headers, OK);
	}
	
	@DeleteMapping("/delete/{batchId}")
	public ResponseEntity<String> removePersonBatch(@PathVariable(name="batchId") UUID batchId) {
		log.debug("Delete persons from batch id [{}]", batchId);
		
		if (personDAO.isBatchExists(batchId)) {
			Integer[] uuids =  personDAO.deletePersonBatch(batchId);
	        Arrays.stream(uuids).forEach(t->log.debug(t.toString()));
			return new ResponseEntity<>(batchId.toString(), HttpStatus.OK);
		}

		return new ResponseEntity<>(batchId.toString(), NOT_FOUND);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Person> searchPerson() {
		throw new IllegalStateException("Method 'searchPerson' not yet supported");
	}
	
}
