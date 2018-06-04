package lv.nixx.poc.crud.person.service;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.person.model.PersonInternalModel;
import lv.nixx.poc.crud.person.model.PersonResponse;
import lv.nixx.poc.crud.service.ToDtoConverter;

@Service
public class PersonToDtoConverter extends ToDtoConverter<PersonInternalModel, PersonResponse> {

	@Override
	protected PersonResponse map(PersonInternalModel internalModel) {
		
		PersonResponse personResponse = new PersonResponse();
		personResponse.setId(internalModel.getId());
		personResponse.setNameSurname(internalModel.getName() + ":" + internalModel.getSurname());
		
		return personResponse;
	}



}
