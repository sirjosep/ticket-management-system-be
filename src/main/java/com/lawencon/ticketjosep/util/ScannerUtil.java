package com.lawencon.ticketjosep.util;

import java.util.Scanner;

public class ScannerUtil {
	
	/*
	 * scanner for string
	 * input type parameter is to decide if the inputted string is accepting alphanumeric or not, if 1 then no, if 0 then yes
	 */
	public static String strScanner(String text, int inputType) {
		System.out.print(text);
		String inputedString = "";
		final char [] inputedStringArr;
		boolean isNotAlphanumeric = true;

		final Scanner confirmScanner = new Scanner(System.in);	
		inputedString = confirmScanner.nextLine();
		
		//if the scanner accepting alphanumeric
		if (inputType == 0) {
			while (inputedString.trim().equals("") || inputedString.isEmpty()) {
				System.out.println("Error! data yang di input tidak boleh kosong, silahkan input ulang");
				return strScanner(text, inputType);
			}
		} 
		//if the scanner not accepting alphanumeric
		else if (inputType == 1) {
			//convert to char array
			inputedStringArr = inputedString.toCharArray();
			//check if it has number or not
			for (int i = 0; i < inputedStringArr.length; i++) {
				if (Character.isDigit(inputedStringArr[i])) {
					isNotAlphanumeric = false;
				}
			}
			
			while (inputedString.trim().equals("") || inputedString.isEmpty() || !isNotAlphanumeric) {
				System.out.println("Error! data yang di input tidak boleh terdiri dari gabungan huruf dan angka, silahkan input ulang");
				return strScanner(text, inputType);
			}
		} 
		
		return inputedString;
	}
	
	/*
	 * scanner for integer
	 * input type = 0 is to set the scanner to have a limit of number that can be inputted (1 - limit)
	 * input type = 1 is to set that the scanner has no limit (1 - ...)
	*/
	public static int intScanner(String text, int inputType, int limit) {
		System.out.print(text);
		int choosenNumber = 0;
		String validateNumber = "";
		
		try {
			Scanner myScanner = new Scanner(System.in);
			validateNumber = myScanner.nextLine();
			choosenNumber = Integer.valueOf(validateNumber);
			
			if (inputType == 0) {
				while (choosenNumber > limit || choosenNumber <= 0) {
					System.out.print("Error! Angka yang di input melebihi batas ( " + limit + " ), Silahkan input ulang : ");
					choosenNumber = myScanner.nextInt();
				}
			} else if (inputType == 1) {
				while (choosenNumber <= 0) {
					System.out.print("Error! Angka yang di input tidak boleh minus, Silahkan input ulang : ");
					choosenNumber = myScanner.nextInt();
				}	
			}
		} catch (Exception e){
			System.out.println("Error! data yang di input tidak boleh huruf atau kosong! ");
			return intScanner(text, inputType, limit);
		}
		
		return choosenNumber;
	}
	
	public static double doubleScanner(String text, int inputType, int limit) {
		System.out.print(text);
		double choosenNumber = 0;
		String validateNumber = "";
		
		try {
			final Scanner myScanner = new Scanner(System.in);
			validateNumber = myScanner.nextLine();
			choosenNumber = Double.valueOf(validateNumber);
			
			if (inputType == 0) {
				while (choosenNumber > limit || choosenNumber <= 0) {
					System.out.print("Error! Angka yang di input melebihi batas ( " + limit + " ), Silahkan input ulang : ");
					choosenNumber = myScanner.nextDouble();
				}
			} else if (inputType == 1) {
				while (choosenNumber <= 0) {
					System.out.print("Error! Angka yang di input tidak boleh minus, Silahkan input ulang : ");
					choosenNumber = myScanner.nextDouble();
				}	
			}
		} catch (Exception e){
			System.out.println("Error! data yang di input tidak boleh huruf atau kosong! ");
			return doubleScanner(text, inputType, limit);
		}
		
		return choosenNumber;
	}
	
	/*
	 * scanner for char type
	 * the input type parameter is to decide whether the scanner only accept a certain char or accept all alphabet, 1 if yes, 0 if no
	 */
	public static char charScanner(String text, int inputType, char[] charCondition) {
		System.out.print(text);
		String inputedItem = "";
		final char inputedChar;
		boolean isChar = false;
		
		//initiate char array to store alphabet list
		final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		
		//try - catch to handle error if the inputted type is not matching the accepted data type
		try {
			final Scanner confirmScanner = new Scanner(System.in);	
			inputedItem = confirmScanner.nextLine();
			inputedChar = inputedItem.toUpperCase().charAt(0);
			
			if (inputType == 0) {
				//check if the inputted char is fit the condition
				for (int i = 0; i < charCondition.length; i++) {
					if (inputedChar == charCondition[i]) {
						isChar = true;
					}
				}

				while (inputedItem.length() > 1 || !isChar) {
					System.out.println("Error! data yang di input hanya boleh sesuai dengan opsi yang tersedia, silahkan input ulang");
					return charScanner(text, inputType, charCondition);
				} 
				
			} 
			//check if the inputted char is on the alphabet
			else if (inputType == 1){
				for (int i = 0; i < alphabet.length; i++) {
					if (inputedChar == alphabet[i]) {
						isChar = true;
					}
				}
				
				while (inputedItem.length() > 1 || !isChar) {
					System.out.println("Error! data yang di input hanya boleh terdiri dari 1 huruf (A-Z), silahkan input ulang");
					return charScanner(text, inputType, charCondition);
				} 
			}

		} catch (Exception e){
			System.out.println("Error! data yang di input hanya boleh terdiri dari 1 huruf (A-Z), silahkan input ulang");
			return charScanner(text, inputType, charCondition);
		}
		
		return inputedChar;
	}	
}
