package lv.nixx.poc.rest.domain;

public class Action<K, V> {

	K id;
	V body;
	Operation operation;
	Status status;

	public Action(K id, V body, Operation operation) {
		this.id = id;
		this.body = body;
		this.operation = operation;
	}

	public Action() {
	}

	public K getId() {
		return id;
	}

	public void setId(K id) {
		this.id = id;
	}

	public V getBody() {
		return body;
	}

	public void setBody(V body) {
		this.body = body;
	}

	public Operation getOperation() {
		return operation;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "Action [id=" + id + ", body=" + body + ", operation=" + operation + "]";
	}

}
