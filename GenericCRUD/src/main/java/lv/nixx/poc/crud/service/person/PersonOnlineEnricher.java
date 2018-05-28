package lv.nixx.poc.crud.service.person;

import org.springframework.stereotype.Service;

import lv.nixx.poc.crud.model.internal.PersonInternalModel;
import lv.nixx.poc.crud.service.internal.OnlineDataEnricher;

@Service
public class PersonOnlineEnricher extends OnlineDataEnricher<PersonInternalModel>{

}
