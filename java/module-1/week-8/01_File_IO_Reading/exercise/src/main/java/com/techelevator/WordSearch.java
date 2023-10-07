package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordSearch {

	// Use this scanner for all user input. Don't create additional Scanners with System.in
	private final Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		WordSearch wordSearch = new WordSearch();
		wordSearch.run();
	}

	public void run() {
		/* Your code goes here */

				// Prompt the user for the file path
				System.out.println("Enter the fully qualified name of the file to be searched:");
				String filePath = userInput.nextLine();

				// Prompt the user for the search word
				System.out.println("What is the search word you are looking for?");
				String searchWord = userInput.nextLine();

				// Prompt the user for case sensitivity
				System.out.println("Should the search be case sensitive? (Y/N)");
				String caseSensitiveInput = userInput.nextLine();
				boolean caseSensitive = caseSensitiveInput.equalsIgnoreCase("Y");

				// Defined the separator character from instructions
				String separator = ")"; // Use ")" as the separator

				try {
					Scanner fileScanner = new Scanner(new File(filePath));
					int lineNumber = 1;

					while (fileScanner.hasNextLine()) {
						String line = fileScanner.nextLine();
						String lineToSearch = caseSensitive ? line : line.toLowerCase();
						String searchWordToLowerCase = caseSensitive ? searchWord : searchWord.toLowerCase();

						if (lineToSearch.contains(searchWordToLowerCase)) {
							// Output the matching line number and text with the specified separator
							System.out.println(lineNumber + separator + " " + line);
						}

						lineNumber++;
					}

					fileScanner.close();
				} catch (FileNotFoundException e) {
					System.out.println("The specified file was not found.");
				}
			}
		}















