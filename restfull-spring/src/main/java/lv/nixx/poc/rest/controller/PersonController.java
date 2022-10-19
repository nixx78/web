package lv.nixx.poc.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lv.nixx.poc.rest.PersonDAO;
import lv.nixx.poc.rest.domain.Action;
import lv.nixx.poc.rest.domain.Person;
import lv.nixx.poc.rest.domain.PersonDTO;
import lv.nixx.poc.rest.domain.Status;
import lv.nixx.poc.rest.exception.PersonNotFoundException;
import lv.nixx.poc.rest.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static lv.nixx.poc.rest.controller.PersonController.BASE_URL;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(BASE_URL)
@Validated
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    static final String BASE_URL = "/rest/person";

    private PersonDAO personDAO;
    private PersonService service;
    private ObjectMapper objectMapper;

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setService(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PersonDTO> addPerson(@RequestBody @Valid PersonDTO p, UriComponentsBuilder builder) {

        log.debug("Adding person [{}]", p);

        PersonDTO saved = service.save(p);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(saved.getId()).toUri());

        return new ResponseEntity<>(saved, headers, HttpStatus.CREATED);
    }

    @PostMapping(value = "processActions")
    public List<Action<String, Person>> processActions(@RequestBody List<Action<String, Person>> actions, HttpServletResponse response) {
        log.debug("Actions [{}]", actions);
        actions.forEach(t -> t.setStatus(Status.SUCCESS));
        response.addCookie(new Cookie("server.cookie1", "cookie1.value"));
        response.addCookie(new Cookie("server.cookie2", "cookie2.value"));
        return actions;
    }

    @GetMapping(value = "/{id}/xml", produces = APPLICATION_XML_VALUE)
    public Person getPersonAsXML(@PathVariable int id) {
        log.debug("Get person by id [{}] as XML", id);
        return personDAO.getById(id);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Person getPerson(@PathVariable(name = "id") int id) {
        log.debug("Get person by id [{}]", id);
        return personDAO.getById(id);
    }

    @GetMapping(value = "/{id}/html", produces = TEXT_HTML_VALUE)
    public String getPersonAsHtml(@PathVariable(name = "id") int id) throws Exception {
        log.debug("Get person by id [{}]", id);
        return objectMapper.writeValueAsString(personDAO.getById(id));
    }


    @GetMapping(value = "/{id}/test", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPersonTest(@PathVariable(name = "id") int id) {
        log.debug("Get person by id [{}]", id);
        try {
            return new ResponseEntity<>(personDAO.getById(id), HttpStatus.OK);
        } catch (PersonNotFoundException ex) {
            return new ResponseEntity<>("Person with id [" + id + "] not found", NOT_FOUND);
        }
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Person[] getAllPersons() {
        log.debug("Get all persons");
        Collection<Person> allPersons = personDAO.getAllPersons();
        return allPersons.toArray(new Person[0]);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody @Valid Person person, @PathVariable String id) {
        log.debug("Update person, id [{}]", id);
        personDAO.update(person);
        return person;
    }

    @DeleteMapping("/{id}")
    public void removePerson(@PathVariable Integer id) {
        log.debug("remove person, id [{}]", id);
        personDAO.delete(id);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> postPersonRemoveBatch(@RequestBody Integer[] ids, UriComponentsBuilder builder) {

        if (log.isDebugEnabled()) {
            log.debug("Ids for delete [{}]", Arrays.toString(ids));
        }

        UUID batchId = personDAO.addToDeleteBatch(ids);

        log.debug("Add persons to batch id [{}]", batchId);

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path(BASE_URL + "/delete/{id}").buildAndExpand(batchId).toUri());

        return new ResponseEntity<>(batchId.toString(), headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{batchId}")
    public ResponseEntity<String> removePersonBatch(@PathVariable(name = "batchId") UUID batchId) {
        log.debug("Delete persons from batch id [{}]", batchId);

        if (personDAO.isBatchExists(batchId)) {
            Integer[] uuids = personDAO.deletePersonBatch(batchId);
            Arrays.stream(uuids).forEach(t -> log.debug(t.toString()));
            return new ResponseEntity<>(batchId.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>(batchId.toString(), NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<Person> searchPerson() {
        throw new IllegalStateException("Method 'searchPerson' not yet supported");
    }

    @PostMapping("/upload")
    //FIXME Migrate to OpenDoc
    /*
    @ApiOperation(value = "Make a POST request to upload the file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiParam(name = "file", value = "Select file to upload", required = true)
    */
    public Collection<PersonDTO> uploadPersons(@RequestPart(name = "file") MultipartFile file) throws IOException {
        String c = new String(file.getBytes());
        return this.service.save(c);
    }

    @GetMapping("/{date}")
    public String response(
            @Parameter(schema = @Schema(type = "string", format = "date", example = "20200217"), description = "Date for test")
            @DateTimeFormat(pattern = "yyyyMMdd") @PathVariable("date") Date date
    ) {
        return "Response:" + date;
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download() throws IOException {
        File file = new File("./data/persons.csv");
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        HttpHeaders headers = new HttpHeaders();
        headers.add("path", file.getAbsolutePath());
        /*
            Generic approach
            headers.add("Content-disposition", "attachment; filename="+ file.getName());
         */

        // Spring specific way how to set filename
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(file.getName())
                .build()
        );

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @Hidden
    @GetMapping("/notVisibleInSwaggerEndpoint")
    public String hiddenEndpoint() {
        return "NotVisibleInSwaggerEndpoint:" + System.currentTimeMillis();
    }

    @GetMapping("/hidden/hidden1")
    public String hidden1() {
        return "Hidden1:" + System.currentTimeMillis();
    }

}
