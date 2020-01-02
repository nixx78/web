package lv.nixx.poc.exh.handler;

import lv.nixx.poc.exh.exception.PersonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = {PersonNotFoundException.class})
    protected ResponseEntity<Object> persistenceExceptionHandler(RuntimeException e, WebRequest request) {
        log.error("Internal system error [{}]", e.getMessage());

        return handleExceptionInternal(e, new ErrorResponse(e.getMessage(),
                        INTERNAL_SERVER_ERROR.toString(), null, null, null),
                new HttpHeaders(),
                INTERNAL_SERVER_ERROR,
                request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> exceptionHandler(RuntimeException e, WebRequest request) {

        Map<String, String> map = new HashMap<>();
        map.put("action", getAttribute("action"));
        map.put("entity", getAttribute("entity"));
        map.put("description", getAttribute("description"));
        map.put("exception", e.getMessage());

        log.error("Internal system error (IllegalStateException) [{}]", e.getMessage());

        return handleExceptionInternal(e, map, new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
    }

    private String getAttribute(String attribute) {
        return MDC.get(attribute);
    }


}
