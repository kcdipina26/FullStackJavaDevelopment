package com.techelevator;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FizzWriter {

	// Use this scanner for all user input. Don't create additional Scanners with System.in
	private final Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		FizzWriter fizzWriter = new FizzWriter();
		fizzWriter.run();
	}

	public void run() {
		/* Your code goes here */
		// Prompt the user for the destination file path
		System.out.print("Enter the destination file path: ");
		String filePath = userInput.nextLine();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (int i = 1; i <= 300; i++) {
				String output = "";
				if (i % 3 == 0) {
					output += "Fizz";
				}
				if (i % 5 == 0) {
					output += "Buzz";
				}
				if (output.isEmpty()) {
					output = String.valueOf(i);
				}
				writer.write(output);
				writer.newLine();
			}
			System.out.println("FizzBuzz output has been written to " + filePath);
		} catch (IOException e) {
			System.err.println("Error writing to the file: " + e.getMessage());
		}
	}

	}

