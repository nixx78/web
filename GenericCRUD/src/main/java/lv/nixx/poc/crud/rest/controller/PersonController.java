package lv.nixx.poc.crud.rest.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lv.nixx.poc.crud.model.rest.PersonResponse;
import lv.nixx.poc.crud.service.person.PersonCrudService;

@RestController
@RequestMapping(path="crudsample")
public class PersonController {
	
	private PersonCrudService crudService;

	@Autowired
	public void setCrudService(PersonCrudService crudService) {
		this.crudService = crudService;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/person/init")
	@ResponseBody
	public Collection<PersonResponse> getInitData() {
		return crudService.getInitialData();
	}
	

}
