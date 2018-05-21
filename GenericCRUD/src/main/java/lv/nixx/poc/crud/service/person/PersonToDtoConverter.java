package lv.nixx.poc.crud.service.person;

import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.model.rest.PersonResponse;
import lv.nixx.poc.crud.service.internal.ToDtoConverter;

public class PersonToDtoConverter extends ToDtoConverter<PersonInternalModel, PersonResponse> {

	@Override
	protected PersonResponse map(PersonInternalModel internalModel) {
		
		PersonResponse personResponse = new PersonResponse();
		personResponse.setId(internalModel.getId());
		personResponse.setNameSurname(internalModel.getName() + ":" + internalModel.getSurname());
		
		return personResponse;
	}



}
