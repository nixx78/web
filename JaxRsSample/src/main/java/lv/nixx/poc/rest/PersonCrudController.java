package lv.nixx.poc.rest;

import java.net.URI;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonCrudController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private PersonStorage persons = new PersonStorage();
	
	@Context
	private UriInfo uriInfo;
	
	public PersonCrudController() {
		persons.put(new Person("1", "Name1", "Surname1"));
		persons.put(new Person("2", "Name2", "Surname2"));
		persons.put(new Person("3", "Name3", "Surname3"));
		persons.put(new Person("4", "Name4", "Surname4"));
		persons.put(new Person("5", "Name5", "Surname5"));
	}

	@GET
	public List<Person> getPersonList() {
		//return new PageImpl<>(persons.getPersons());
		return persons.getPersons();
	}
	
	@GET
	@Path("{id}")
	public Person getPerson(@PathParam("id") String id) {
		log.debug("Get person by id [{}]", id);
		return persons.get(id);
	}
	
	@POST
	public Response postPerson(Person p) {
		persons.put(p);
		
		URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .resolveTemplate("id", p.getId())
                .build();

        return Response.created(location).build();
	}
	
	
	class PersonStorage extends HashMap<String, Person>{
		private static final long serialVersionUID = 1L;

		public void put(Person p){
			put(p.getId(), p);
		}

		public List<Person> getPersons(){
			return new ArrayList<>(persons.values());
		}
	}

}
