package lv.nixx.poc.rest.controller;

import lv.nixx.poc.rest.exception.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@RestController
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class DifferentHttpCodesController {

    @GetMapping("/unavailableService")
    public String unavailableService() {
        return "Unavailable service";
    }

    @GetMapping("/notFound")
    @ResponseStatus(value = NOT_FOUND, reason = "Not found reason")
    public String notFound() {
        return "Not found body";
    }

    @GetMapping("/responseEntity")
    public ResponseEntity<String> withResponseEntity(@RequestParam String param) {
        return ResponseEntity.status(CREATED).body("Response body: " + param);
    }

    @GetMapping("/request/{type}")
    @ResponseStatus(HttpStatus.OK)
    public String processRequest(@PathVariable("type") Type type) {
        try {
            return process(type);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(FORBIDDEN, e.getMessage());
        } catch (IllegalArgumentException e1) {
            throw new ResponseStatusException(BAD_REQUEST, e1.getMessage());
        }
    }

    @GetMapping("/invalidRequest")
    public void invalidRequest() {
        throw new InvalidDataException("Invalid data");
    }

    protected String process(Type type) {

        if (type == Type.OK) {
            return type + ":" + System.currentTimeMillis();
        }

        if (type == Type.INVALID) {
            throw new IllegalArgumentException("Invalid request");
        }

        if (type == Type.NOT_SUPPORTED) {
            throw new IllegalStateException("Not supported type");
        }

        return null;
    }

    enum Type {
        OK, NOT_SUPPORTED, INVALID
    }

}
