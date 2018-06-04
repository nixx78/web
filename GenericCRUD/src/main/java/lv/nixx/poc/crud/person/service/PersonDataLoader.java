package lv.nixx.poc.crud.person.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.internal.DataSource;
import lv.nixx.poc.crud.person.dao.PersonDao;
import lv.nixx.poc.crud.person.model.Person;
import lv.nixx.poc.crud.person.model.PersonInternalModel;
import lv.nixx.poc.crud.service.DataLoader;

@Service
public class PersonDataLoader extends DataLoader<PersonInternalModel>{
	
	private PersonDao dao;
	
	@Autowired
	public void setDao(PersonDao personDao) {
		this.dao = personDao;
	}
	
	@Override
	public Collection<PersonInternalModel> load() {
		
		Collection<Person> allPersons = dao.getAllPersons();
		
		return allPersons.stream().map( p -> {
			PersonInternalModel m = new PersonInternalModel(p.getId());
			m.setDataSource(DataSource.DB);
			m.setName(p.getName());
			m.setSurname(p.getSurname());
			m.setDateOfBirth(p.getDateOfBirth());
			return m;
		}).collect(Collectors.toList());
		
	}

}
