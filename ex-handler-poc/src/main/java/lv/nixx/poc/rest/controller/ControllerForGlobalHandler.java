package lv.nixx.poc.rest.controller;

import io.swagger.annotations.ApiOperation;
import lv.nixx.poc.rest.annotation.RequestDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerForGlobalHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerForGlobalHandler.class);

    @GetMapping(path = "/rest/process1")
    @ApiOperation(value = "Test method Process1")
    @RequestDescriptor(action = "Action1", entity = "Person")
    public String process1() {

        log.info("Rest process1 call");

        if (true) {
            throw new IllegalStateException("Error message");
        }

        return "Success";
    }

    @GetMapping(path = "/rest/process2")
    @ApiOperation(value = "Test method Process2")
    @RequestDescriptor(action = "Action2", entity = "Account")
    public String process2() {

        log.info("Rest process2 call");

        if (true) {
            throw new IllegalStateException("Error message");
        }

        return "Success";
    }



}
