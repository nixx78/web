package lv.nixx.poc.crud.service.internal;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import lv.nixx.poc.crud.config.PersonCrudServiceConfig;
import lv.nixx.poc.crud.model.dao.Person;
import lv.nixx.poc.crud.model.rest.PersonResponse;
import lv.nixx.poc.crud.service.HazelcastService;
import lv.nixx.poc.crud.service.dao.PersonDao;
import lv.nixx.poc.crud.service.person.PersonDataLoader;
import lv.nixx.poc.crud.service.person.PersonHazelcastEnricher;
import lv.nixx.poc.crud.service.person.PersonHazelcastSaver;
import lv.nixx.poc.crud.service.person.PersonOnlineEnricher;
import lv.nixx.poc.crud.service.person.PersonToDtoConverter;


@RunWith(MockitoJUnitRunner.class)
public class PersonCrudServiceTest {
	
	@Spy
	@InjectMocks
	private PersonCrudServiceConfig personCrudServiceConfig;
	
	@Spy
	private PersonDataLoader personLoader;

	@Mock
	public PersonDao dao;
	
	@Spy
	private PersonHazelcastEnricher personHazelcastEnricher;
	
	@Spy
	private PersonHazelcastSaver personHazelcastSaverSaver;
	
	@Spy
	private PersonToDtoConverter personToDtoConverter;
	
	@Spy
	private PersonOnlineEnricher personOnlineEnricher;
	
	@Mock
	private HazelcastService hazelcastService;
	
	@Before
	public void init() {
		personLoader.setDao(dao);
		personHazelcastEnricher.setHazelcastService(hazelcastService);
	}
		
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
		
		Collection<PersonResponse> initialData = personCrudServiceConfig.personCrudService().getInitialData();
		assertNotNull(initialData);
		
		System.out.println(initialData);
		
	
	}

}
