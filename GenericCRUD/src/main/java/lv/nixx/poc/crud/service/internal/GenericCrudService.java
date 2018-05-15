package lv.nixx.poc.crud.service.internal;

import java.util.Collection;

import lv.nixx.poc.crud.model.internal.BaseInternalModel;
import lv.nixx.poc.crud.model.rest.BaseRequest;
import lv.nixx.poc.crud.model.rest.BaseResponse;
import lv.nixx.poc.crud.model.rest.Response;

public abstract class GenericCrudService<ID_TYPE, T extends BaseRequest<ID_TYPE>, V extends BaseResponse, K extends BaseInternalModel<ID_TYPE>> {

	protected abstract Collection<K> loadDataToInternalModels();

	protected abstract K map(Object entity);

	protected abstract void enrichWithHazelcastData(Collection<K> internalModel);

	protected abstract void enrichWithOnlineData(Collection<K> internalModel);

	protected abstract void saveDataToHazelcast(Collection<K> internalModel);

	protected abstract Collection<V> convertToRestResponse(Collection<K> internalModel);

	public Response<ID_TYPE> add(T request) {
		return null;
	}

	public Response<ID_TYPE> update(T request) {
		return null;
	}

	public Response<ID_TYPE> delete(T request) {
		return null;
	}

	public Response<ID_TYPE> refrteshInUI() {
		return null;
	}

	public Response<ID_TYPE> flushToDB() {
		return null;
	}

	public Collection<V> getInitialData() {
		Collection<K> loadDataToInternalModels = loadDataToInternalModels();

		enrichWithHazelcastData(loadDataToInternalModels);
		enrichWithOnlineData(loadDataToInternalModels);
		saveDataToHazelcast(loadDataToInternalModels);
		return convertToRestResponse(loadDataToInternalModels);
	}

	public Response<ID_TYPE> undo() {
		return null;
	}

}
