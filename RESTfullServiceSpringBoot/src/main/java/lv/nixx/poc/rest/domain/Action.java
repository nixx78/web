package lv.nixx.poc.rest.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Action<K, V> {

	private K id;
	private V body;
	private Operation operation;
	private Status status;

	public Action() {
	}

	public Action(K id, V body, Operation operation) {
		this.id = id;
		this.body = body;
		this.operation = operation;
	}
}
