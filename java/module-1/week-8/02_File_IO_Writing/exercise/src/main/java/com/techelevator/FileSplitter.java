package com.techelevator;


import java.io.*;
import java.util.Scanner;
public class FileSplitter {

	// Use this scanner for all user input. Don't create additional Scanners with System.in
	private final Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		FileSplitter fileSplitter = new FileSplitter();
		fileSplitter.run();
	}

	public void run() {
		/* Your code goes here */

		// Prompt the user for the input file path
		System.out.print("Enter the input file path: ");
		String inputFilePath = userInput.nextLine();

		// Prompt the user for the maximum lines per split file
		System.out.print("Enter the maximum lines per split file: ");
		int maxLinesPerSplit = userInput.nextInt();
		userInput.nextLine(); // Consume newline

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
			String line;
			int fileNumber = 1;
			BufferedWriter writer = null;

			while ((line = reader.readLine()) != null) {
				 {
					// Close the previous writer and open a new one
					if (writer != null) {
						writer.close();
					}
					String outputFilePath = getOutputFilePath(inputFilePath, fileNumber);
					writer = new BufferedWriter(new FileWriter(outputFilePath));
					fileNumber++;
				}

				writer.write(line);
				writer.newLine();
			}

			// Close the last writer
			if (writer != null) {
				writer.close();
			}

			System.out.println("Input file has been split into smaller files.");
		} catch (IOException e) {
			System.err.println("Error reading or writing files: " + e.getMessage());
		}
	}

	private String getOutputFilePath(String inputFilePath, int fileNumber) {
		int dotIndex = inputFilePath.lastIndexOf(".");
		String extension = (dotIndex != -1) ? inputFilePath.substring(dotIndex) : "";
		String fileName = inputFilePath.substring(0, dotIndex);
		return fileName + "-" + fileNumber + extension;
	}
}

