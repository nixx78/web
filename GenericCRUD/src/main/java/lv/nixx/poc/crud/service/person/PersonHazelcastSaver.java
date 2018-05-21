package lv.nixx.poc.crud.service.person;

import java.util.Collection;

import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.service.internal.HazelcastSaver;

public class PersonHazelcastSaver extends HazelcastSaver<PersonInternalModel>{

	@Override
	public Collection<PersonInternalModel> save(Collection<PersonInternalModel> entities) {
		return entities;
	}

}
