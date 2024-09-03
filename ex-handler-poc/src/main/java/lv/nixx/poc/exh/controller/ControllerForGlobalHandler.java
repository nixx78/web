package lv.nixx.poc.exh.controller;

import io.swagger.v3.oas.annotations.Operation;
import lv.nixx.poc.exh.handler.annotation.Descriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class ControllerForGlobalHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerForGlobalHandler.class);

    @GetMapping(path = "/rest/process1")
    @Operation(description = "Test method Process1")
    @Descriptor(action = "Action1", entity = "Person")
    public String process1() {

        LOG.info("Rest process1 call");

        if (true) {
            throw new IllegalStateException("Error message");
        }

        return "Success";
    }

    @GetMapping(path = "/rest/process2")
    @Operation(description = "Test method Process2")
    @Descriptor(action = "Action2", entity = "Account")
    public String process2() {

        LOG.info("Rest process2 call");

        if (true) {
            throw new IllegalStateException("Error message");
        }

        return "Success";
    }

    @GetMapping(path = "/rest/process3")
    @Operation(description = "Test method, throw exception in different thread")
    @Descriptor(action = "Action2", entity = "Account")
    public String process3() throws Exception {

        LOG.info("Rest process3 call");

        ExecutorService es = Executors.newSingleThreadExecutor();

        try {
            es.submit(() -> {
                throw new IllegalStateException("Error message from new Thread");
            }).get();

        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }

        return "Success";
    }

}
