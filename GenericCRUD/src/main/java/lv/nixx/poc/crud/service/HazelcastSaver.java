package lv.nixx.poc.crud.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class HazelcastSaver<K> {
	
	public HazelcastService hazelcastService;
	
	@Autowired
	public void setHazelcastService(HazelcastService hazelcastService) {
		this.hazelcastService = hazelcastService;
	}

	public abstract Collection<K> save(Collection<K> entities);
	
}