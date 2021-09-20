package lv.nixx.poc.rest.controller;

import lv.nixx.poc.rest.domain.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@Validated
public class ControllerWithValidation {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerWithValidation.class);

    @GetMapping("/pathVariableValidation/{id}/{name}")
    public String pathVariableValidation(@PathVariable("id") @Size(min = 2) String id,
                                         @PathVariable("name") @Size(min = 5) String name
    ) {

        return "success:" + id + ":" + name;
    }

    @GetMapping("/requestParamsValidation")
    public String requestParamsValidation(@RequestParam("id") @Size(min = 2) String id,
                                          @RequestParam("name") @Size(min = 5) String name
    ) {

        return "success:" + id + ":" + name;
    }

    @PostMapping("/request1")
    public String simpleRequest(@RequestBody  @Valid Request request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            LOG.info("Validation errors [{}]", bindingResult.getAllErrors());
            return "fail";
        }

        return "success";
    }

    @PostMapping("/request2")
    public String anotherRequest(@RequestBody @Valid Request request) {
        return "success";
    }

}
