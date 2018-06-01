package lv.nixx.poc.crud.service.alpha;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.hazelcast.AlphaHazelcastModel;
import lv.nixx.poc.crud.model.internal.AlphaInternalModel;
import lv.nixx.poc.crud.model.rest.AlphaOperationRequest;
import lv.nixx.poc.crud.model.rest.AlphaResponse;
import lv.nixx.poc.crud.service.internal.DataLoader;
import lv.nixx.poc.crud.service.internal.GenericCrudService;
import lv.nixx.poc.crud.service.internal.HazelcastEnricher;
import lv.nixx.poc.crud.service.internal.HazelcastSaver;
import lv.nixx.poc.crud.service.internal.OnlineDataEnricher;
import lv.nixx.poc.crud.service.internal.ToDtoConverter;

@Service
public class AlphaCrudService extends GenericCrudService<Integer, AlphaOperationRequest, AlphaResponse, AlphaInternalModel, AlphaHazelcastModel> {

	@Override
	protected void setDataLoader(DataLoader<AlphaInternalModel> dataLoader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setHazelcastEnricher(
			HazelcastEnricher<Integer, AlphaInternalModel, AlphaHazelcastModel> hazelcastEnricher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setOnlineDataEnricher(OnlineDataEnricher<AlphaInternalModel> onlineDataEnricher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setHazelcastSaver(HazelcastSaver<AlphaInternalModel> hazelcastSaver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setToDtoConverter(ToDtoConverter<AlphaInternalModel, AlphaResponse> toDtoConverter) {
		// TODO Auto-generated method stub
		
	}

}
