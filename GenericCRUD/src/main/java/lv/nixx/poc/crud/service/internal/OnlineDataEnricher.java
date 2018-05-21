package lv.nixx.poc.crud.service.internal;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class OnlineDataEnricher<K> {
	
	public Collection<K> enrich(Collection<K> entities){
		return entities;
	}

}
