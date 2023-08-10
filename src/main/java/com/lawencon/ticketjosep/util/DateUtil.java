package com.lawencon.ticketjosep.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	public static String dateFormat (LocalDateTime localDateTime) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
		
		return localDateTime.format(formatter);
	}
}
