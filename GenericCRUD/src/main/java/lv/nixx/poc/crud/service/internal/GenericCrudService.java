package lv.nixx.poc.crud.service.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.internal.BaseInternalModel;
import lv.nixx.poc.crud.model.rest.BaseRequest;
import lv.nixx.poc.crud.model.rest.BaseResponse;
import lv.nixx.poc.crud.model.rest.Response;

@Service
public abstract class GenericCrudService<ID_TYPE, T extends BaseRequest<ID_TYPE>, V extends BaseResponse, K extends BaseInternalModel<ID_TYPE>> {

	protected DataLoader<K> dataLoader = null;
	protected HazelcastEnricher<K> hazelcastEnricher = null;
	protected OnlineDataEnricher<K> onlineDataEnricher = null;
	protected HazelcastSaver<K> hazelcastSaver = null;
	protected ToDtoConverter<K, V> toDtoConverter = null;

	public DataLoader<K> getDataLoader() {
		return dataLoader;
	}

	public void setDataLoader(DataLoader<K> dataLoader) {
		this.dataLoader = dataLoader;
	}

	public HazelcastEnricher<K> getHazelcastEnricher() {
		return hazelcastEnricher;
	}

	public void setHazelcastEnricher(HazelcastEnricher<K> hazelcastEnricher) {
		this.hazelcastEnricher = hazelcastEnricher;
	}

	public OnlineDataEnricher<K> getOnlineDataEnricher() {
		return onlineDataEnricher;
	}

	public void setOnlineDataEnricher(OnlineDataEnricher<K> onlineDataEnricher) {
		this.onlineDataEnricher = onlineDataEnricher;
	}

	public HazelcastSaver<K> getHazelcastSaver() {
		return hazelcastSaver;
	}

	public void setHazelcastSaver(HazelcastSaver<K> hazelcastSaver) {
		this.hazelcastSaver = hazelcastSaver;
	}

	public ToDtoConverter<K, V> getToDtoConverter() {
		return toDtoConverter;
	}

	public void setToDtoConverter(ToDtoConverter<K, V> toDtoConverter) {
		this.toDtoConverter = toDtoConverter;
	}

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
