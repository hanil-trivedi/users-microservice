package com.hanil.demo.exception;

import java.util.Date;

public class ExceptionResponse {

	private Date timstamp;
	private String message;
	private String details;
	public Date getTimstamp() {
		return timstamp;
	}
	public void setTimstamp(Date timstamp) {
		this.timstamp = timstamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public ExceptionResponse(Date timstamp, String message, String details) {
		super();
		this.timstamp = timstamp;
		this.message = message;
		this.details = details;
	}
	
}
