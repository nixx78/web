package lv.nixx.poc.crud.model.rest;

import lombok.Getter;

@Getter
public class Response<T> {
	
	private T entityId;
	private Status status;
	
	public Response(T entityId, Status status) {
		super();
		this.entityId = entityId;
		this.status = status;
	}


}
