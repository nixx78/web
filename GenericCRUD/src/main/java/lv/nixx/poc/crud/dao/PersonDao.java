package lv.nixx.poc.crud.dao;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.dao.Person;

@Service
public class PersonDao {
	
	private List<Person> values = new ArrayList<>();
	
	public PersonDao() {
		
		Person p1 = new Person();
		p1.setId("p1ID");
		p1.setName("P1.Name");
		p1.setSurname("P1.Surname");

		Person p2 = new Person();
		p2.setId("p2ID");
		p2.setName("P2.Name");
		p2.setSurname("P2.Surname");

		Person p3 = new Person();
		p3.setId("p3ID");
		p3.setName("P3.Name");
		p3.setSurname("P3.Surname");

		values.add(p1);
		values.add(p2);
		values.add(p3);
	}
	
	public Collection<Person> getAllPersons() {
		return values;
	}

}
