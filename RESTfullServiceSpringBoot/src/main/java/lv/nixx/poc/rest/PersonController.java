package lv.nixx.poc.rest;

import java.util.*;
import lv.nixx.poc.rest.domain.Person;
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
	
	static final String BASE_URL = "rest/person";

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonDAO personDAO; 
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Person> addPerson(@RequestBody Person p, UriComponentsBuilder builder) {
		log.debug("Adding person [{}]", p);
		
		personDAO.save(p);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(p.getId()).toUri());
		
		return new ResponseEntity<Person>(p, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}/xml", produces="application/xml")
	public @ResponseBody  ResponseEntity<Person> getPersonAsXML(@PathVariable int id) {
		log.debug("Get person by id [{}] as XML", id);
		
		final Person p = personDAO.getById(id);
		return new ResponseEntity<Person>(p, p == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<Person> getPerson(@PathVariable(name="id") int id) {
		log.debug("Get person by id [{}]", id);

		final Person p = personDAO.getById(id);
		return new ResponseEntity<Person>(p, p == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}

	@ApiOperation(value="Method return all Persons", responseContainer = "List")
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Person[] getAllPersons() {
		log.debug("Get all persons");
		Collection<Person> allPersons = personDAO.getAllPersons();
		return allPersons.toArray(new Person[allPersons.size()]);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public @ResponseBody Person updatePerson(@RequestBody Person person, @PathVariable String id) {
		log.debug("Update person, id [{}]", id);
		personDAO.update(person);
		return person;
	}
		
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public @ResponseBody void removePerson(@PathVariable String id) {
		log.debug("remove person, id [{}]", id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/delete")
	public @ResponseBody ResponseEntity<String> postPersonRemoveBatch(@RequestBody Integer[] ids, UriComponentsBuilder builder) {
        Arrays.stream(ids).forEach(t->log.debug(t.toString()));
		UUID batchId = personDAO.addToDeleteBatch(ids);
		
		log.debug("Add persons to batch id [{}]", batchId);
		
        final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path(BASE_URL + "/delete/{id}").buildAndExpand(batchId).toUri());
		
		return new ResponseEntity<String>(batchId.toString(), headers, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/delete/{batchId}")
	public @ResponseBody ResponseEntity<String> removePersonBatch(@PathVariable(name="batchId") UUID batchId) {
		log.debug("Delete persons from batch id [{}]", batchId);
		
		if (personDAO.isBatchExists(batchId)) {
			Integer[] uuids =  personDAO.deletePersonBatch(batchId);
	        Arrays.stream(uuids).forEach(t->log.debug(t.toString()));
			return new ResponseEntity<String>(batchId.toString(), HttpStatus.OK);		
		}

		return new ResponseEntity<String>(batchId.toString(), HttpStatus.NOT_FOUND);
	}
	
}
