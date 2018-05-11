package lv.nixx.poc.crud.model.internal;

public class BaseInternalModel<T> {

	private T id;

	public BaseInternalModel(T id) {
		super();
		this.id = id;
	}

	public T getId() {
		return id;
	}

}
