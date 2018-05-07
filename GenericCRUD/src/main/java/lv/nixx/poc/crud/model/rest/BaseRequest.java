package lv.nixx.poc.crud.model.rest;

public class BaseRequest<T> {

	private T entityId;

	public BaseRequest() {
		super();
	}

	public BaseRequest(T entityId) {
		super();
		this.entityId = entityId;
	}

	public T getEntityId() {
		return entityId;
	}

	public void setEntityId(T entityId) {
		this.entityId = entityId;
	}

}
