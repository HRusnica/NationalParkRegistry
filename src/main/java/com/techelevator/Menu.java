package com.techelevator;

import java.util.Date;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	public BigDecimal getDecimalFromUser(String message) {
		BigDecimal inputtedDecimal = null;
		out.println(message);
		do {
			try {
				String userInput = in.nextLine();
				inputtedDecimal = new BigDecimal(userInput);
			} catch(NumberFormatException ex) {
				out.print("Please input a valid number >>> ");
				out.flush();
			}
		} while (inputtedDecimal == null);
		
		return inputtedDecimal;
	}

	public Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}

	public void displayMenuOptions(Object[] options) {
		out.println();
		for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
	
	public Date getDateFromUserInput(String input) throws ParseException {
		Date choice = null;
		String userInput = in.nextLine();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			int selectedOption = Integer.valueOf(input);
			if(selectedOption > 0 && selectedOption <= input.length()) {
				choice = formatter.parse(input);
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+input+" is not a valid option ***\n");
		}
		return choice;
	}
}
