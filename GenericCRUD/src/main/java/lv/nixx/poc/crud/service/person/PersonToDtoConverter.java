package lv.nixx.poc.crud.service.person;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.model.rest.PersonResponse;
import lv.nixx.poc.crud.service.internal.ToDtoConverter;

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
