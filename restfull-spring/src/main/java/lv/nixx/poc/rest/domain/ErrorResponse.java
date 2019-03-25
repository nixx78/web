package lv.nixx.poc.rest.domain;

public class ErrorResponse {
	
	private String errorMessage;
	private String status;
	
	public ErrorResponse(String errorMessage, String status) {
		super();
		this.errorMessage = errorMessage;
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public String getStatus() {
		return status;
	}
	
	

}
