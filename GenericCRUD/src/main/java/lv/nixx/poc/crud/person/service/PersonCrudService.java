package lv.nixx.poc.crud.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.person.model.PersonInternalModel;
import lv.nixx.poc.crud.person.model.PersonOperationRequest;
import lv.nixx.poc.crud.person.model.PersonResponse;
import lv.nixx.poc.crud.service.DataLoader;
import lv.nixx.poc.crud.service.GenericCrudService;
import lv.nixx.poc.crud.service.HazelcastEnricher;
import lv.nixx.poc.crud.service.HazelcastSaver;
import lv.nixx.poc.crud.service.OnlineDataEnricher;
import lv.nixx.poc.crud.service.ToDtoConverter;

@Service
public class PersonCrudService extends GenericCrudService<String, PersonOperationRequest, PersonResponse, PersonInternalModel, PersonHazelcastModel> {
	
	@Override
	@Autowired
	protected void setDataLoader(DataLoader<PersonInternalModel> personDataLoader) {
		this.dataLoader = personDataLoader;
	}

	@Override
	@Autowired
	protected void setHazelcastEnricher(HazelcastEnricher<String, PersonInternalModel, PersonHazelcastModel> hazelcastEnricher) {
		this.hazelcastEnricher = hazelcastEnricher;
	}

	@Override
	@Autowired
	protected void setOnlineDataEnricher(OnlineDataEnricher<PersonInternalModel> onlineDataEnricher) {
		this.onlineDataEnricher = onlineDataEnricher;
	}

	@Override
	@Autowired
	protected void setHazelcastSaver(HazelcastSaver<PersonInternalModel> hazelcastSaver) {
		this.hazelcastSaver = hazelcastSaver;
	}

	@Override
	@Autowired
	protected void setToDtoConverter(ToDtoConverter<PersonInternalModel, PersonResponse> toDtoConverter) {
		this.toDtoConverter = toDtoConverter;
	}
	
}
