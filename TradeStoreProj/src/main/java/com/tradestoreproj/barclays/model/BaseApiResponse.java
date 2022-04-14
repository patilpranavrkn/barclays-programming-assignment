package com.tradestoreproj.barclays.model;

public class BaseApiResponse {
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	
	public BaseApiResponse(String statusCode, String statusMessage, boolean isError) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.isError = isError;
	}
	public BaseApiResponse() {
		super();
	}
	private String statusMessage;
	private boolean isError;
	private String statusCode;

}
