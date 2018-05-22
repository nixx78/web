package lv.nixx.poc.crud.model.internal;

public class BaseInternalModel<T> {

	private T id;
	private DataSource dataSource = DataSource.UNDEFINED;

	public BaseInternalModel(T id) {
		super();
		this.id = id;
	}

	public T getId() {
		return id;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


}
