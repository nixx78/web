package lv.nixx.poc.crud.person.service;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.person.model.PersonInternalModel;
import lv.nixx.poc.crud.service.OnlineDataEnricher;

@Service
public class PersonOnlineEnricher extends OnlineDataEnricher<PersonInternalModel>{

}
