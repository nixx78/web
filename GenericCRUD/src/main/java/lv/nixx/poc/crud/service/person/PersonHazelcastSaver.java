package lv.nixx.poc.crud.service.person;

import java.util.Collection;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.service.internal.HazelcastSaver;

@Service
public class PersonHazelcastSaver extends HazelcastSaver<PersonInternalModel>{

	@Override
	public Collection<PersonInternalModel> save(Collection<PersonInternalModel> entities) {
		return entities;
	}

}
