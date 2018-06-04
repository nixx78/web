package lv.nixx.poc.crud.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public abstract class ToDtoConverter<K, V> {
	
	public Collection<V> convertToDto(Collection<K> entities){
		return entities.stream()
				.map(this::map)
				.collect(Collectors.toList());
	}
	
	protected abstract V map(K internalModel);
	
}