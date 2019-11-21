package lv.nixx.poc.rest.controller;

import lv.nixx.poc.rest.annotation.RequestDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleControllerWithException {

    private static final Logger log = LoggerFactory.getLogger(SampleControllerWithException.class);


    @GetMapping(path = "/rest/process1")
    @RequestDescriptor(action = "Action1", entity = "Person")
    public String process1() {

        log.info("Rest process1 call");

        if (true) {
            throw new IllegalStateException("Error message");
        }

        return "Success";
    }

    @GetMapping(path = "/rest/process2")
    @RequestDescriptor(action = "Action2", entity = "Account")
    public String process2() {

        log.info("Rest process2 call");

        if (true) {
            throw new IllegalStateException("Error message");
        }

        return "Success";
    }



}
