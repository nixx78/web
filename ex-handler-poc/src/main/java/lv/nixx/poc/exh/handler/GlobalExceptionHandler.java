package lv.nixx.poc.exh.handler;

import lv.nixx.poc.exh.exception.PersonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {PersonNotFoundException.class})
    protected ResponseEntity<Object> persistenceExceptionHandler(RuntimeException e, WebRequest request) {
        log.error("Internal system error [{}]", e.getMessage());

        return handleExceptionInternal(e, new ErrorResponse(e.getMessage(),
                        INTERNAL_SERVER_ERROR.toString(), null, null, null),
                new HttpHeaders(),
                INTERNAL_SERVER_ERROR,
                request);
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    protected ResponseEntity<Object> illegalStateExceptionHandler(RuntimeException e, WebRequest request) {

        Map<String, String> map = new HashMap<>();
        map.put("action", getAttribute(request, "action"));
        map.put("entity", getAttribute(request, "entity"));
        map.put("description", getAttribute(request, "description"));

        log.error("Internal system error (IllegalStateException) [{}]", e.getMessage());

        return handleExceptionInternal(e, map, new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
    }

    private String getAttribute(WebRequest request, String attribute) {
        return Optional.ofNullable(request)
                .map(t -> t.getAttribute(attribute, SCOPE_REQUEST))
                .map(Object::toString)
                .orElse("Unknown");
    }


}
