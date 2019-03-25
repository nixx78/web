package lv.nixx.poc.rest.domain;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Action<K, V> {

	@NonNull
	private K id;

	@NonNull
	private V body;

	@NonNull
	private Operation operation;

	@NonNull
	private Status status;

	public Action(K id, V body, Operation operation) {
		this.id = id;
		this.body = body;
		this.operation = operation;
	}
}
