package lv.nixx.poc.crud.person.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lv.nixx.poc.crud.model.hazelcast.BaseHazelcastModel;

@Data
@EqualsAndHashCode(callSuper=false)
public class PersonHazelcastModel extends BaseHazelcastModel<String> {
	private String override1;
	private String override2;
}
