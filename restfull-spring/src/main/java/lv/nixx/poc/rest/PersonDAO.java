package lv.nixx.poc.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.*;

import lv.nixx.poc.rest.domain.Person;
import lv.nixx.poc.rest.exception.PersonNotFoundException;

@Component
public class PersonDAO {

    private static final AtomicInteger id = new AtomicInteger();
    private static final Map<Integer, Person> map = new ConcurrentHashMap<>();
    private static final Map<UUID, Integer[]> removeBatch = new HashMap<>();

    static {
        try {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            addPerson("name1", "surname1", df.parse("05.10.1979"));
            addPerson("name2", "surname2", df.parse("07.10.1980"));
            addPerson("name3", "surname3", df.parse("15.05.1980"));
            addPerson("name4", "surname4", df.parse("06.12.1978"));
            addPerson("name5", "", df.parse("06.12.1979"));
            addPerson("name6", null, df.parse("06.12.1960"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Person save(Person person) {
        person.setId(id.incrementAndGet());
        map.put(person.getId(), person);

        return person;
    }

    public Person getById(int id) {
        if (map.containsKey(id)) {
            return map.get(id);
        }
        throw new PersonNotFoundException("Person with id [" + id + "] not found");
    }

    public void delete(Integer id) {
        map.remove(id);
    }

    public void update(Person person) {
        map.put(person.getId(), person);
    }

    public Collection<Person> getAllPersons() {
        return map.values();
    }

    public UUID addToDeleteBatch(Integer[] ids) {
        UUID batchId = UUID.randomUUID();
        removeBatch.put(batchId, ids);
        return batchId;
    }

    public boolean isBatchExists(UUID batchId) {
        return removeBatch.containsKey(batchId);
    }

    public Integer[] deletePersonBatch(UUID batchId) {
        return removeBatch.remove(batchId);
    }

    private static void addPerson(String name, String surname, Date dateOfBirth) {
        int id = PersonDAO.id.incrementAndGet();
        Person p = new Person(name, surname, dateOfBirth);
        p.setId(id);
        map.put(id, p);
    }

}
