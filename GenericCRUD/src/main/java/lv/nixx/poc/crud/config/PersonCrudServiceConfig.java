package lv.nixx.poc.crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lv.nixx.poc.crud.service.person.PersonCrudService;
import lv.nixx.poc.crud.service.person.PersonDataLoader;
import lv.nixx.poc.crud.service.person.PersonHazelcastEnricher;
import lv.nixx.poc.crud.service.person.PersonHazelcastSaver;
import lv.nixx.poc.crud.service.person.PersonOnlineEnricher;
import lv.nixx.poc.crud.service.person.PersonToDtoConverter;

@Configuration
public class PersonCrudServiceConfig {

	public PersonDataLoader personLoader;
	public PersonHazelcastEnricher personHazelcastEnricher;
	public PersonHazelcastSaver personHazelcastSaverSaver;
	public PersonToDtoConverter personToDtoConverter;
	public PersonOnlineEnricher personOnlineEnricher;

	@Autowired
	public void setPersonLoader(PersonDataLoader personLoader) {
		this.personLoader = personLoader;
	}

	@Autowired
	public void setPersonHazelcastEnricher(PersonHazelcastEnricher personHazelcastEnricher) {
		this.personHazelcastEnricher = personHazelcastEnricher;
	}

	@Autowired
	public void setPersonHazelcastSaverSaver(PersonHazelcastSaver personHazelcastSaverSaver) {
		this.personHazelcastSaverSaver = personHazelcastSaverSaver;
	}

	@Autowired
	public void setPersonToDtoConverter(PersonToDtoConverter personToDtoConverter) {
		this.personToDtoConverter = personToDtoConverter;
	}

	@Autowired
	public void setPersonOnlineEnricher(PersonOnlineEnricher personOnlineEnricher) {
		this.personOnlineEnricher = personOnlineEnricher;
	}

	@Bean
	public PersonCrudService personCrudService() {

		PersonCrudService s = new PersonCrudService();
		s.setDataLoader(personLoader);
		s.setHazelcastEnricher(personHazelcastEnricher);
		s.setHazelcastSaver(personHazelcastSaverSaver);
		s.setToDtoConverter(personToDtoConverter);
		s.setOnlineDataEnricher(personOnlineEnricher);

		return s;
	}

}
