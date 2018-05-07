package lv.nixx.poc.crud.service.internal;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.dao.Person;
import lv.nixx.poc.crud.model.rest.PersonOperation;
import lv.nixx.poc.crud.model.rest.PersonResponse;
import lv.nixx.poc.crud.service.dao.PersonDao;

@Service
public class PersonCrudService extends GenericCrudService<String, PersonOperation, PersonResponse, PersonInternalModel> {

	private PersonDao dao;

	@Override
	protected Collection<PersonInternalModel> loadDataToInternalModels() {
		return dao.getAllPersons()
				.stream()
				.map(this::map)
				.collect(Collectors.toList());
	}

	@Override
	protected PersonInternalModel map(Object entity) {
		Person p = (Person) entity;
		
		return new PersonInternalModel();
	}

	@Override
	protected void enrichWithHazelcastData(Collection<PersonInternalModel> internalModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void enrichWithOnlineData(Collection<PersonInternalModel> internalModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void saveDataToHazelcast(Collection<PersonInternalModel> internalModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Collection<PersonResponse> convertToRestResponse(Collection<PersonInternalModel> internalModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
