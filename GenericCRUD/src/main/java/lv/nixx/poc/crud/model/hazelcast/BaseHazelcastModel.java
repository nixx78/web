package lv.nixx.poc.crud.model.hazelcast;

import lombok.Data;

@Data
public class BaseHazelcastModel<T> {
	
	private T entityId;

}
