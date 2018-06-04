package lv.nixx.poc.crud.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.hazelcast.BaseHazelcastModel;
import lv.nixx.poc.crud.model.internal.BaseInternalModel;
import lv.nixx.poc.crud.model.rest.BaseRequest;
import lv.nixx.poc.crud.model.rest.BaseResponse;
import lv.nixx.poc.crud.model.rest.Response;

@Service
public abstract class GenericCrudService<ID_TYPE, 
		T extends BaseRequest<ID_TYPE>, 
		V extends BaseResponse, 
		K extends BaseInternalModel<ID_TYPE>, 
		H extends BaseHazelcastModel<ID_TYPE>> {

	protected DataLoader<K> dataLoader = null;
	protected HazelcastEnricher<ID_TYPE, K, H> hazelcastEnricher = null;
	protected OnlineDataEnricher<K> onlineDataEnricher = null;
	protected HazelcastSaver<K> hazelcastSaver = null;
	protected ToDtoConverter<K, V> toDtoConverter = null;
	
	protected abstract void setDataLoader(DataLoader<K> dataLoader);
	protected abstract void setHazelcastEnricher(HazelcastEnricher<ID_TYPE, K, H> hazelcastEnricher);
	protected abstract void setOnlineDataEnricher(OnlineDataEnricher<K> onlineDataEnricher);
	protected abstract void setHazelcastSaver(HazelcastSaver<K> hazelcastSaver);
	protected abstract void setToDtoConverter(ToDtoConverter<K, V> toDtoConverter);
	

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

	public Response<ID_TYPE> undo() {
		return null;
	}

	public Collection<V> getInitialData() {
		return Optional.ofNullable(dataLoader.load())
				.map(hazelcastEnricher::enrich)
				.map(onlineDataEnricher::enrich)
				.map(hazelcastSaver::save)
				.map(toDtoConverter::convertToDto)
				.orElse(Collections.emptyList());
	}

}
