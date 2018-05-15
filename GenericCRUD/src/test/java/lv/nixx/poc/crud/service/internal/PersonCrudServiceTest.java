package lv.nixx.poc.crud.service.internal;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import lv.nixx.poc.crud.model.dao.Person;
import lv.nixx.poc.crud.model.rest.PersonResponse;
import lv.nixx.poc.crud.service.HazelcastService;
import lv.nixx.poc.crud.service.dao.PersonDao;

@RunWith(MockitoJUnitRunner.class)
public class PersonCrudServiceTest {
	
	@Spy
	@InjectMocks
	private PersonCrudService service;
	
	@Mock
	private PersonDao personDao;
	
	@Spy
	private HazelcastService hazelcastService;
		
	@Test
	public void getInitialDataTest() {
		
		Person p1 = new Person();
		Person p2 = new Person();
			
		when(personDao.getAllPersons()).thenReturn(Arrays.asList(p1, p2));
		
		Collection<PersonResponse> initialData = service.getInitialData();
		
	
	}

}
