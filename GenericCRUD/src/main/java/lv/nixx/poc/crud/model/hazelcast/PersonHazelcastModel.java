package lv.nixx.poc.crud.model.hazelcast;

import lombok.Data;

@Data
public class PersonHazelcastModel extends BaseHazelcastModel<String> {
	private String override1;
	private String override2;
}
