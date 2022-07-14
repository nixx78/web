package lv.nixx.poc.rest.controller;

import lv.nixx.poc.rest.service.ServiceWithAsyncMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerWithAsyncEndpoint {

    private final ServiceWithAsyncMethod service;

    @Autowired
    public ControllerWithAsyncEndpoint(ServiceWithAsyncMethod service) {
        this.service = service;
    }

    @GetMapping("/asyncEndpoint")
    public String asyncEndpoint(@RequestParam String requestId) {
        service.asyncMethod(requestId);
        return String.format("completed, id [%s] : %s", requestId, System.currentTimeMillis());
    }

}
