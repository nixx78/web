package lv.nixx.poc.rest.domain;

import lombok.*;

@ToString
@Data
@NoArgsConstructor
public class Action<K, V> {

	private K id;
	private V body;
	private Operation operation;
	private Status status;

	public Action(K id, V body, Operation operation) {
		this.id = id;
		this.body = body;
		this.operation = operation;
	}

    public Action(K id, V body, Operation operation, Status status) {
        this.id = id;
        this.body = body;
        this.operation = operation;
        this.status = status;
    }


}
