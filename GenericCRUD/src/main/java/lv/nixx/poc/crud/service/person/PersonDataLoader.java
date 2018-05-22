package lv.nixx.poc.crud.service.person;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.dao.Person;
import lv.nixx.poc.crud.model.internal.DataSource;
import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.service.dao.PersonDao;
import lv.nixx.poc.crud.service.internal.DataLoader;

@Service
public class PersonDataLoader extends DataLoader<PersonInternalModel>{
	
	private PersonDao dao;
	
	@Autowired
	public void setDao(PersonDao dao) {
		this.dao = dao;
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
