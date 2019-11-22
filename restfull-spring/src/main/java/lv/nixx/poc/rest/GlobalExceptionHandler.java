package lv.nixx.poc.rest;

import lv.nixx.poc.rest.exception.PersonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lv.nixx.poc.rest.domain.ErrorResponse;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value={PersonNotFoundException.class})
	protected ResponseEntity<Object> persistenceExceptionHandler(RuntimeException e, WebRequest request) {
		log.error("Internal system error [{}]", e.getMessage());
		
		return handleExceptionInternal(e, new ErrorResponse(e.getMessage(),
				INTERNAL_SERVER_ERROR.toString(), null, null, null),
				new HttpHeaders(),
				INTERNAL_SERVER_ERROR,
				request);
	}

	@ExceptionHandler(value={IllegalStateException.class})
	protected ResponseEntity<Object> illegalStateExceptionHandler(RuntimeException e, WebRequest request, HttpSession httpSession) {

		String entity = Optional.ofNullable(httpSession)
				.map(t -> t.getAttribute("entity"))
				.map(Object::toString)
				.orElse("Unknown");

		String action = Optional.ofNullable(httpSession)
				.map(t -> t.getAttribute("action"))
				.map(Object::toString)
				.orElse("Unknown");

		String description = Optional.ofNullable(httpSession)
				.map(t -> t.getAttribute("description"))
				.map(Object::toString)
				.orElse("Unknown");

		log.error("Internal system error (IllegalStateException) [{}], entity [{}]", e.getMessage(), entity);

		return handleExceptionInternal(e,
				new ErrorResponse(e.getMessage(),
				INTERNAL_SERVER_ERROR.toString(),
						action,
						entity,
						description
				),
		new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
	}


}
