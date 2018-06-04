package lv.nixx.poc.crud.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.person.service.PersonHazelcastModel;

@Service
public class HazelcastService {

	public Collection<PersonHazelcastModel> getAllPersonModels() {
		return Collections.emptyList();
	};

}
