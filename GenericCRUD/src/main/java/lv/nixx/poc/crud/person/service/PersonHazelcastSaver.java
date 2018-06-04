package lv.nixx.poc.crud.person.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.person.model.PersonInternalModel;
import lv.nixx.poc.crud.service.HazelcastSaver;

@Service
public class PersonHazelcastSaver extends HazelcastSaver<PersonInternalModel>{

	@Override
	public Collection<PersonInternalModel> save(Collection<PersonInternalModel> entities) {
		return entities;
	}

}
