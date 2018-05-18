package lv.nixx.poc.crud.service.person;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.model.rest.PersonOperationRequest;
import lv.nixx.poc.crud.model.rest.PersonResponse;
import lv.nixx.poc.crud.service.internal.GenericCrudService;

@Service
public class PersonCrudService extends GenericCrudService<String, PersonOperationRequest, PersonResponse, PersonInternalModel> {


	
//	@Autowired
//	public void setHazelcastService(HazelcastService hazelcastService) {
//		this.hazelcastService = hazelcastService;
//	}
//
//	@Override
//	protected Collection<PersonInternalModel> loadDataToInternalModels() {
//		return dao.getAllPersons().stream().map(this::map).collect(Collectors.toList());
//	}
//
//	@Override
//	protected PersonInternalModel map(Object entity) {
//		Person person = (Person) entity;
//		PersonInternalModel model = new PersonInternalModel(person.getId());
//		model.setName(person.getName());
//		model.setSurname(person.getSurname());
//		model.setDateOfBirth(person.getDateOfBirth());
//
//		return model;
//	}
//
//	@Override
//	protected void enrichWithHazelcastData(Collection<PersonInternalModel> internalModel) {
//
//		Map<String, PersonHazelcastModel> modelMap = hazelcastService.getAllPersonModels()
//				.stream()
//				.collect(Collectors.toMap(PersonHazelcastModel::getEntityId, Function.identity()));
//
//		internalModel.forEach(m -> {
//			String id = m.getId();
//			PersonHazelcastModel hm = modelMap.getOrDefault(id, null);
//			if (hm != null) {
//				m.setOverride1(hm.getOverride1());
//				m.setOnline2(hm.getOverride2()); 
//			}
//
//		});
//	}
//
//	@Override
//	protected void enrichWithOnlineData(Collection<PersonInternalModel> internalModel) {
//
//	}
//
//	@Override
//	protected void saveDataToHazelcast(Collection<PersonInternalModel> internalModel) {
//
//	}
//
//	@Override
//	protected Collection<PersonResponse> convertToRestResponse(Collection<PersonInternalModel> internalModel) {
//		return internalModel.stream().map(m -> {
//			PersonResponse p = new PersonResponse();
//			p.setId(m.getId());
//			p.setNameSurname(m.getName() + ":" + m.getSurname());
//			return p;
//		}).collect(Collectors.toList());
//	}
	
}
