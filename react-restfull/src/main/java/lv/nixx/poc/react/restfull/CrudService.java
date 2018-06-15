package lv.nixx.poc.react.restfull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public class CrudService {

	private Collection<Person> persons = new ArrayList<>(Arrays.asList(
			new Person(1L, "Name1", "Surname1"),
			new Person(2L, "Name2", "Surname2"), 
			new Person(3L, "Name3", "Surname3")
			)
	);

	public Collection<Person> getAllPersons() {
		return persons;
	}
	
	public void addPerson(Person p) {
		persons.add(p);
	}
	
	public void deletePerson(Long id) {
		persons.remove(new Person(id));
	}
	

}
