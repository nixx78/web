package lv.nixx.poc.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class LogTestController {

    private static final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @GetMapping("/log/message")
    public Collection<String> logMessage(@RequestParam String message, @RequestHeader(name = "custom.header") String customHeader) {
        log.info("CMP1 Message: {} header: {}", message, customHeader);

        return List.of(
                "MessageResponse:" + message,
                "HeaderResponse:" + customHeader
        );
    }

}
