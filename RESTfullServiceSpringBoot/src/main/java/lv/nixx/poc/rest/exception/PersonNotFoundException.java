package lv.nixx.poc.rest.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Person") 
public class PersonNotFoundException extends RuntimeException {

	public PersonNotFoundException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
