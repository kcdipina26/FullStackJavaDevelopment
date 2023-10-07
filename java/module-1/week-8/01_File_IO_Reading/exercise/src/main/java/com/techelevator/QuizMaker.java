package com.techelevator;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuizMaker {

	// Use this scanner for all user input. Don't create additional Scanners with System.in
	private final Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		QuizMaker quizMaker = new QuizMaker();
		quizMaker.run();
	}

	public void run() {
		/* Your code goes here */

				System.out.println("Enter the fully qualified name of the file to read in for quiz questions:");
				String filePath = userInput.nextLine();

				int correctAnswers = 0;
				int totalQuestions = 0;

				try {
					Scanner fileScanner = new Scanner(new File(filePath));

					while (fileScanner.hasNextLine()) {
						String line = fileScanner.nextLine();
						String[] parts = line.split("\\|");

						System.out.println(parts[0]); // Print the question

						// Identify correct answer
						int correctIndex = -1;
						for (int i = 1; i < parts.length; i++) {
							if (parts[i].endsWith("*")) {
								correctIndex = i - 1;
								System.out.println(i + ". " + parts[i].substring(0, parts[i].length() - 1));
							} else {
								System.out.println(i + ". " + parts[i]);
							}
						}

						System.out.print("Your answer: ");
						int userAnswer = userInput.nextInt() - 1; // -1 to make it 0-based index
						userInput.nextLine(); // Clear the new line

						if (userAnswer == correctIndex) {
							System.out.println("RIGHT!\n");
							correctAnswers++;
						} else {
							System.out.println("WRONG!\n");
						}

						totalQuestions++;
					}
					fileScanner.close();
				} catch (FileNotFoundException e) {
					System.out.println("The specified file was not found.");
				}

				System.out.println("You got " + correctAnswers + " answer(s) correct out of " + totalQuestions + ".");
			}
		}





