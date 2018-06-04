package lv.nixx.poc.crud.alpha.service;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.alpha.model.AlphaHazelcastModel;
import lv.nixx.poc.crud.alpha.model.AlphaInternalModel;
import lv.nixx.poc.crud.alpha.model.AlphaOperationRequest;
import lv.nixx.poc.crud.alpha.model.AlphaResponse;
import lv.nixx.poc.crud.service.DataLoader;
import lv.nixx.poc.crud.service.GenericCrudService;
import lv.nixx.poc.crud.service.HazelcastEnricher;
import lv.nixx.poc.crud.service.HazelcastSaver;
import lv.nixx.poc.crud.service.OnlineDataEnricher;
import lv.nixx.poc.crud.service.ToDtoConverter;

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
