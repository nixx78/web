package lv.nixx.poc.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
	
	private final String errorMessage;
	private final String status;
	private final String action;
	private final String entity;
	private final String description;


}
