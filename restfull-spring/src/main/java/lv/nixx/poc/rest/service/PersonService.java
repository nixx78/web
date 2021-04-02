package lv.nixx.poc.rest.service;

import lv.nixx.poc.rest.PersonDAO;
import lv.nixx.poc.rest.domain.Person;
import lv.nixx.poc.rest.domain.PersonDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public Collection<PersonDTO> save(String csv) throws IOException {

        Iterable<CSVRecord> records = CSVFormat.RFC4180
                .withFirstRecordAsHeader()
                .withIgnoreSurroundingSpaces()
                .withNullString("")
                .parse(new StringReader(csv));

        return StreamSupport.stream(records.spliterator(), false)
                .map(record -> {
                    String n = record.get("name");
                    String s = record.get("surname");

                    return new Person()
                            .setName(n)
                            .setSurname(s);
                }).map(p-> {
                    Person save = dao.save(p);
                    return new PersonDTO()
                            .setId(save.getId())
                            .setName(save.getName())
                            .setSurname(save.getSurname());
                }).collect(Collectors.toList());
    }

}
