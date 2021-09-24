package lv.nixx.poc.rest.exception;

import lv.nixx.poc.rest.domain.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

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

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalStateException.class})
    public @ResponseBody String handleIllegalStateException(Exception ex) {
        return ex.getMessage();
    }


}
