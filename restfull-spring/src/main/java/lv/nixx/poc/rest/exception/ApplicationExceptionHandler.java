package lv.nixx.poc.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler({IllegalStateException.class, ConstraintViolationException.class})
	public @ResponseBody  String handleException(Exception ex) {
		return ex.getMessage();
	}

}
