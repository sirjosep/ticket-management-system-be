package com.lawencon.ticketjosep.validation;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {
	
	@SuppressWarnings("unused")
	private String msg;
	
	public CustomException() {
		
	}
	
	public CustomException(String msg) {
		super(msg);
		this.msg = msg;
	}
}
