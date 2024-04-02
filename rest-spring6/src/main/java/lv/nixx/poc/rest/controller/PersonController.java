package lv.nixx.poc.rest.controller;

import jakarta.validation.Valid;
import lv.nixx.poc.rest.model.PersonDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class PersonController {

    @PostMapping("/person")
    public PersonDTO addPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personDTO;
    }

}
