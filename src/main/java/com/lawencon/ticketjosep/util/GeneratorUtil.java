package com.lawencon.ticketjosep.util;

public class GeneratorUtil {
	//generate random string consist of string and integer
		public static final String generateAlphaNum(int length) {
			//create char array that has alphabet and number from 0 to 9
			final char[] ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
			
			//initiate string builder to make string
			final StringBuilder randomStr = new StringBuilder();
			String generatedString = "";
			
			//build string from char array
			for (int i = 0; i < length; i++) {
				final int index = (int) (Math.random() * ALPHANUMERIC.length);
				randomStr.append(ALPHANUMERIC[index]);
			}

			//convert the char to string
			generatedString = randomStr.toString();
			
			return generatedString.toUpperCase();
		}
}
