package lv.nixx.poc.crud.service.person;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.hazelcast.PersonHazelcastModel;
import lv.nixx.poc.crud.model.internal.DataSource;
import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.service.internal.*;

@Service
public class PersonHazelcastEnricher extends HazelcastEnricher<PersonInternalModel> {

	@Override
	public Collection<PersonInternalModel> enrich(Collection<PersonInternalModel> entities) {
		
		// Possible the following situations:
		//  - Data exists in DB					- Add to Hazelcast
		//  - Data exists in DB && Hazelcast	- update internal model, not save to Hazelcast
		//  - Data exists in Hazelcast only		- update internal model, not save to Hazelcast
		
		Map<String, PersonInternalModel> dbEntitiesMap = entities.stream().collect(Collectors.toMap(PersonInternalModel::getId, Function.identity()));
		
		for (PersonHazelcastModel hm: hazelcastService().getAllPersonModels()) {

			String entityId = hm.getEntityId();
			if (dbEntitiesMap.containsKey(entityId)) {
				PersonInternalModel pim = dbEntitiesMap.get(entityId);
				pim.setDataSource(DataSource.DB_HAZELCAST);
				enrich(pim, hm);
			} else {
				dbEntitiesMap.put(entityId, createInternalModel(hm));
			}
			
		}
		
		return dbEntitiesMap.values();
	}
	

	private void enrich(PersonInternalModel personInternalModel, PersonHazelcastModel hazelcastModel) {
		personInternalModel.setOverride1(hazelcastModel.getOverride1());
		personInternalModel.setOverride2(hazelcastModel.getOverride2());
	}
	
	private PersonInternalModel createInternalModel(PersonHazelcastModel hazelcastModel) {
		PersonInternalModel pim = new PersonInternalModel(hazelcastModel.getEntityId());
		pim.setDataSource(DataSource.HAZELCAST);
		enrich(pim, hazelcastModel);
		return pim;
	}

}
