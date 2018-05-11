package lv.nixx.poc.crud.service.internal;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.dao.Person;
import lv.nixx.poc.crud.model.rest.PersonOperation;
import lv.nixx.poc.crud.model.rest.PersonResponse;
import lv.nixx.poc.crud.service.HazelcastService;
import lv.nixx.poc.crud.service.dao.PersonDao;

@Service
public class PersonCrudService extends GenericCrudService<String, PersonOperation, PersonResponse, PersonInternalModel> {

	private PersonDao dao;
	private HazelcastService hazelcastService;
	
	@Autowired
	public void setDao(PersonDao dao) {
		this.dao = dao;
	}

	@Autowired
	public void setHazelcastService(HazelcastService hazelcastService) {
		this.hazelcastService = hazelcastService;
	}

	@Override
	protected Collection<PersonInternalModel> loadDataToInternalModels() {
		return dao.getAllPersons().stream().map(this::map).collect(Collectors.toList());
	}

	@Override
	protected PersonInternalModel map(Object entity) {

		Person person = (Person) entity;

		PersonInternalModel model = new PersonInternalModel(person.getId());
		model.setName(person.getName());
		model.setSurname(person.getSurname());
		model.setDateOfBirth(person.getDateOfBirth());

		return model;
	}

	@Override
	protected void enrichWithHazelcastData(PersonInternalModel internalModel) {
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
