package lv.nixx.poc.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
	
	private final String errorMessage;
	private final String status;

}
