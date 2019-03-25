package lv.nixx.poc.react.restfull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CrudService {
	
	private final Map<Long, Person> persons;

	public CrudService() {
		persons = Arrays.asList(
				new Person(1L, "Name1", "Surname1"),
				new Person(2L, "Name2", "Surname2"), 
				new Person(3L, "Name3", "Surname3")
				)
				.stream()
				.collect(Collectors.toMap(Person::getId, Function.identity()));
	}
	
	public Collection<Person> getAllPersons() {
		return persons.values();
	}
	
	public void addPerson(Person p) {
		persons.put(p.getId(), p);
	}
	
	public Person deletePerson(Long id) {
		return persons.remove(id);
	}
	
	public Person getPerson(Long id) {
		return persons.get(id);
	}
	

}
