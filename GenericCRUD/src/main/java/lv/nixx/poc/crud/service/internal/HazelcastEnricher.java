package lv.nixx.poc.crud.service.internal;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.hazelcast.BaseHazelcastModel;
import lv.nixx.poc.crud.model.internal.BaseInternalModel;
import lv.nixx.poc.crud.model.internal.DataSource;
import lv.nixx.poc.crud.service.HazelcastService;

@Service
public abstract class HazelcastEnricher<ID_Type, K extends BaseInternalModel<ID_Type>, V extends BaseHazelcastModel<ID_Type>> {
	
	private HazelcastService hazelcastService;
	
	protected abstract Collection<V> getHazelcastData();
	
	protected abstract void enrich(K internalModel, V hazelcastModel);
	protected abstract K createInternalModel(V hm);

	@Autowired
	public void setHazelcastService(HazelcastService hazelcastService) {
		this.hazelcastService = hazelcastService;
	}
	
	protected HazelcastService hazelcastService() {
		return this.hazelcastService;
	}
	
	public Collection<K> enrich(Collection<K> entities) {
	
		// Possible the following situations:
		//  - Data exists in DB					- Add to Hazelcast
		//  - Data exists in DB && Hazelcast	- update internal model, not save to Hazelcast
		//  - Data exists in Hazelcast only		- update internal model, not save to Hazelcast
		
		
		final Map<ID_Type, K> entitiesMap = entities.stream().collect(Collectors.toMap(BaseInternalModel::getId, Function.identity()));
		
		
		for (V hm: getHazelcastData()) {

			ID_Type entityId = hm.getEntityId();
			
			if (entitiesMap.containsKey(entityId)) {
				K pim = entitiesMap.get(entityId);
				pim.setDataSource(DataSource.DB_HAZELCAST);
				enrich(pim, hm);
			} else {
				entitiesMap.put(entityId, createInternalModel(hm));
			}
			
		}
		
		return entitiesMap.values();
	}
	
	
}