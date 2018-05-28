package lv.nixx.poc.crud.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.hazelcast.PersonHazelcastModel;
import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.model.rest.PersonOperationRequest;
import lv.nixx.poc.crud.model.rest.PersonResponse;
import lv.nixx.poc.crud.service.internal.DataLoader;
import lv.nixx.poc.crud.service.internal.GenericCrudService;
import lv.nixx.poc.crud.service.internal.HazelcastEnricher;
import lv.nixx.poc.crud.service.internal.HazelcastSaver;
import lv.nixx.poc.crud.service.internal.OnlineDataEnricher;
import lv.nixx.poc.crud.service.internal.ToDtoConverter;

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
