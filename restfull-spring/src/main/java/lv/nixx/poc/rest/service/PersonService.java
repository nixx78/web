package lv.nixx.poc.rest.service;

import lv.nixx.poc.rest.PersonDAO;
import lv.nixx.poc.rest.domain.Person;
import lv.nixx.poc.rest.domain.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonDAO dao;

    @Autowired
    public void setDao(PersonDAO dao) {
        this.dao = dao;
    }

    public PersonDTO save(PersonDTO personDTO) {

        Person p = new Person()
                .setName(personDTO.getName())
                .setSurname(personDTO.getSurname())
                .setDateOfBirth(personDTO.getDateOfBirth());

        Person saved = dao.save(p);

        return personDTO.setId(saved.getId());
    }

}
