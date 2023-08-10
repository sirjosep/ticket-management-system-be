package com.lawencon.ticketjosep.constant;

public enum Status {
	OPEN("OPN"), PENDING_AGENT("PDA"), ON_PROGRESS("ONP"), PENDING_CUSTOMER("PDC"), CLOSED("CLS"), RE_OPEN("REO");
	
	public final String statusCode;
	
	Status(String statusCode) {
		this.statusCode = statusCode;
	}
}
