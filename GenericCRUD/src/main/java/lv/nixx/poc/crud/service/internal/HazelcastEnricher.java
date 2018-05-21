package lv.nixx.poc.crud.service.internal;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.service.HazelcastService;

@Service
public abstract class HazelcastEnricher<K> {
	
	private HazelcastService hazelcastService;
	
	
	@Autowired
	public void setHazelcastService(HazelcastService hazelcastService) {
		this.hazelcastService = hazelcastService;
	}
	
	protected HazelcastService hazelcastService() {
		return this.hazelcastService;
	}

	public abstract Collection<K> enrich(Collection<K> entities);
	
	
}