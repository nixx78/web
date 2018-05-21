package lv.nixx.poc.crud.service.person;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.hazelcast.PersonHazelcastModel;
import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.service.internal.*;

@Service
public class PersonHazelcastEnricher extends HazelcastEnricher<PersonInternalModel> {

	@Override
	public Collection<PersonInternalModel> enrich(Collection<PersonInternalModel> entities) {

		Map<String, PersonHazelcastModel> modelsMap = hazelcastService().getAllPersonModels().stream()
				.collect(Collectors.toMap(PersonHazelcastModel::getEntityId, Function.identity()));

		for (PersonInternalModel pim : entities) {
			if (modelsMap.containsKey(pim.getId())) {
				enrich(pim, modelsMap.get(pim.getId()));
			}
		}

		return entities;
	}

	private void enrich(PersonInternalModel personInternalModel, PersonHazelcastModel hazelcastModel) {
		PersonInternalModel pim = new PersonInternalModel(hazelcastModel.getEntityId());
		pim.setOverride1(hazelcastModel.getOverride1());
		pim.setOverride2(hazelcastModel.getOverride2());
	}
	

}
