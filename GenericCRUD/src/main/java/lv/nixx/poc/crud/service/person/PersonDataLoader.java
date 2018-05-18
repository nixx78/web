package lv.nixx.poc.crud.service.person;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.dao.Person;
import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.service.dao.PersonDao;
import lv.nixx.poc.crud.service.internal.DataLoader;

@Service
public class PersonDataLoader extends DataLoader<PersonInternalModel>{
	
	@Autowired
	public PersonDao dao;

	@Override
	public Collection<PersonInternalModel> load() {
//		return dao.getAllPersons();
		return null;
	}

}
