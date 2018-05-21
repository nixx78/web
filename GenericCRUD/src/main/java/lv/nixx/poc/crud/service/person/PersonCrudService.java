package lv.nixx.poc.crud.service.person;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.model.rest.PersonOperationRequest;
import lv.nixx.poc.crud.model.rest.PersonResponse;
import lv.nixx.poc.crud.service.internal.GenericCrudService;

@Service
public class PersonCrudService extends GenericCrudService<String, PersonOperationRequest, PersonResponse, PersonInternalModel> {
	
	
}
