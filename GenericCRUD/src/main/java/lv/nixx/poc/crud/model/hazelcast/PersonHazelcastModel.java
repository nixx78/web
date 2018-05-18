package lv.nixx.poc.crud.model.hazelcast;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PersonHazelcastModel extends BaseHazelcastModel<String> {
	private String override1;
	private String override2;
}
