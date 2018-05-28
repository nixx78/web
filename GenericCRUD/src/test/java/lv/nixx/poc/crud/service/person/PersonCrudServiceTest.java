package lv.nixx.poc.crud.service.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lv.nixx.poc.crud.config.AppConfig;
import lv.nixx.poc.crud.model.dao.Person;
import lv.nixx.poc.crud.model.rest.PersonResponse;
import lv.nixx.poc.crud.service.TestContextConfiguration;
import lv.nixx.poc.crud.service.dao.PersonDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestContextConfiguration.class})
public class PersonCrudServiceTest {

	@Autowired
	public PersonDao dao;
	
	@Autowired
	public PersonCrudService personCrudService; 

	@Test
	public void getInitialDataTest() {

		Person p1 = new Person();
		p1.setId("ID1000");
		p1.setName("Name1");
		p1.setSurname("Surname1");

		Person p2 = new Person();
		p2.setId("ID2000");
		p2.setName("Name2");
		p2.setSurname("Surname2");

		when(dao.getAllPersons()).thenReturn(Arrays.asList(p1, p2));

		 Collection<PersonResponse> initialData = personCrudService.getInitialData();
		 assertNotNull(initialData);
		 assertEquals(2, initialData.size());
		
		 System.out.println(initialData);
	}

}
