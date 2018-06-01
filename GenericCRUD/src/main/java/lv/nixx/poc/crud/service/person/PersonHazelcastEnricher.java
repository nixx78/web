package lv.nixx.poc.crud.service.person;

import java.util.Collection;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.hazelcast.PersonHazelcastModel;
import lv.nixx.poc.crud.model.internal.DataSource;
import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.service.internal.*;

@Service
public class PersonHazelcastEnricher extends HazelcastEnricher<String , PersonInternalModel, PersonHazelcastModel> {
	
	@Override
	protected Collection<PersonHazelcastModel> getHazelcastData() {
		return hazelcastService().getAllPersonModels();
	}


	@Override
	protected void enrich(PersonInternalModel personInternalModel, PersonHazelcastModel hazelcastModel) {
		personInternalModel.setOverride1(hazelcastModel.getOverride1());
		personInternalModel.setOverride2(hazelcastModel.getOverride2());
	}
	
	@Override
	protected  PersonInternalModel createInternalModel(PersonHazelcastModel hazelcastModel) {
		PersonInternalModel pim = new PersonInternalModel(hazelcastModel.getEntityId());
		pim.setDataSource(DataSource.HAZELCAST);
		enrich(pim, hazelcastModel);
		return pim;
	}


}
