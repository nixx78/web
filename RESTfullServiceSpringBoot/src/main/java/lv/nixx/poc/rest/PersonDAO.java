package lv.nixx.poc.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.*;

import lv.nixx.poc.rest.domain.Person;

@Component
public class PersonDAO {
	
	private static AtomicInteger id = new AtomicInteger();
	private static Map<Integer, Person> map = new ConcurrentHashMap<>();
	private static Map<UUID, Integer[]> removeBatch = new HashMap<>();
	
	static {
		try {
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			addPerson("name1", "surname1", df.parse("05.10.1979"));
			addPerson("name2", "surname2", df.parse("07.10.1980"));
			addPerson("name3", "surname3", df.parse("15.05.1980"));
			addPerson("name4", "surname4", df.parse("06.12.1978"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void save(Person person) {
		person.setId( id.incrementAndGet() );
		map.put(person.getId(), person);
	}
	
	//@PreAuthorize("hasRole('ROLE_USER')")
	public Person getById(int id) {
		return map.get(id);
	}
	
	public void delete(Integer id) {
		map.remove(id);
	}
	
	public void update(Person person) {
		map.put(person.getId(), person);
	}
	
	public Collection<Person> getAllPersons() {
		return map.values();
	}
	
	public UUID addToDeleteBatch(Integer[] ids) {
		UUID batchId = UUID.randomUUID();
		removeBatch.put(batchId, ids);
		return batchId;
	}
	
	public boolean isBatchExists(UUID batchId) {
		return removeBatch.containsKey(batchId);
	}
	
	public Integer[] deletePersonBatch(UUID batchId) {
		return removeBatch.remove(batchId);
	}

	private static void addPerson(String name, String surname, Date dateOfBirth) throws ParseException {
		int id = PersonDAO.id.incrementAndGet();
		Person p = new Person(name, surname, dateOfBirth );
		p.setId(id);
		map.put(id,  p);
	}

}
