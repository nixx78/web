package lv.nixx.poc.crud.service.internal;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.service.HazelcastService;

@Service
public abstract class HazelcastEnricher<K> {
	
	@Autowired
	public HazelcastService hazelcastService;
	
	public abstract Collection<K> enrich(Collection<K> entities);
	
	
}