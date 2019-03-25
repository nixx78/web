package lv.nixx.poc.rest;

import java.util.*;

import lv.nixx.poc.rest.domain.Action;
import lv.nixx.poc.rest.domain.Person;
import lv.nixx.poc.rest.domain.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/" + PersonController.BASE_URL)
@Api(value="CRUD Operations for entity Person")
public class PersonController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	static final String BASE_URL = "rest/person";


	private PersonDAO personDAO;

	@Autowired
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@PostMapping
	public @ResponseBody ResponseEntity<Person> addPerson(@RequestBody Person p, UriComponentsBuilder builder) {
		log.debug("Adding person [{}]", p);
		
		personDAO.save(p);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(p.getId()).toUri());
		
		return new ResponseEntity<Person>(p, headers, HttpStatus.CREATED);
	}
	
	@PostMapping(value="processActions", consumes="application/json")
	public @ResponseBody List<Action<String, Person>> processActions(@RequestBody List<Action<String, Person>> actions) {
		log.debug("Actions" + actions);
		
		actions.stream().forEach( t -> t.setStatus(Status.SUCCESS));
		
		return actions;
		
	}
	
	@GetMapping(value="/{id}/xml", produces="application/xml")
	public @ResponseBody  Person getPersonAsXML(@PathVariable int id) {
		log.debug("Get person by id [{}] as XML", id);
		return personDAO.getById(id);
	}
	
	@GetMapping(value="/{id}", produces="application/json")
	public @ResponseBody Person getPerson(@PathVariable(name="id") int id) {
		log.debug("Get person by id [{}]", id);
		return personDAO.getById(id);
	}

	@ApiOperation(value="Method return all Persons", responseContainer = "List")
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Person[] getAllPersons() {
		log.debug("Get all persons");
		Collection<Person> allPersons = personDAO.getAllPersons();
		return allPersons.toArray(new Person[allPersons.size()]);
	}

	@PutMapping("/{id}")
	public @ResponseBody Person updatePerson(@RequestBody Person person, @PathVariable String id) {
		log.debug("Update person, id [{}]", id);
		personDAO.update(person);
		return person;
	}
		
	@DeleteMapping("/{id}")
	public @ResponseBody void removePerson(@PathVariable Integer id) {
		log.debug("remove person, id [{}]", id);
		personDAO.delete(id);
	}
	
	@PostMapping("/delete")
	public @ResponseBody ResponseEntity<String> postPersonRemoveBatch(@RequestBody Integer[] ids, UriComponentsBuilder builder) {
        Arrays.stream(ids).forEach(t->log.debug(t.toString()));
		UUID batchId = personDAO.addToDeleteBatch(ids);
		
		log.debug("Add persons to batch id [{}]", batchId);
		
        final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path(BASE_URL + "/delete/{id}").buildAndExpand(batchId).toUri());
		
		return new ResponseEntity<>(batchId.toString(), headers, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{batchId}")
	public @ResponseBody ResponseEntity<String> removePersonBatch(@PathVariable(name="batchId") UUID batchId) {
		log.debug("Delete persons from batch id [{}]", batchId);
		
		if (personDAO.isBatchExists(batchId)) {
			Integer[] uuids =  personDAO.deletePersonBatch(batchId);
	        Arrays.stream(uuids).forEach(t->log.debug(t.toString()));
			return new ResponseEntity<>(batchId.toString(), HttpStatus.OK);
		}

		return new ResponseEntity<>(batchId.toString(), HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/search")
	public @ResponseBody ResponseEntity<Person> searchPerson() {
		throw new IllegalStateException("Method 'searchPerson' not yet supported");
	}
	
}