package lv.nixx.poc.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ExceptionHandlerController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);


    //FIXME think, how to inject data into Http Session using custom Annotation
    @GetMapping(path = "/rest/exception")
    public String process(HttpSession httpSession) {

        log.info("Rest exception handler call");

        httpSession.setAttribute("entity", "Entity.Value");

        if (true) {
            throw new IllegalStateException();
        }

        return "Success";
    }
}
