package com.techelevator;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FindAndReplace {

    // Use this scanner for all user input. Don't create additional Scanners with System.in
    private final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        FindAndReplace findAndReplace = new FindAndReplace();
        findAndReplace.run();
    }

    public void run() {
        /* Your code goes here */
        try {
            // Prompt the user for input
            System.out.println("What is the search word?");
            String searchWord = userInput.nextLine();

            System.out.println("What is the replacement word?");
            String replacementWord = userInput.nextLine();

            System.out.println("What is the source file?");
            String sourceFilePath = userInput.nextLine();

            System.out.println("What is the destination file?");
            String destinationFilePath = userInput.nextLine();

            // Open the source file for reading
            try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath))) {
                // Open the destination file for writing
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Replace the search word with the replacement word and write to the destination file
                        String modifiedLine = line.replace(searchWord, replacementWord);
                        writer.write(modifiedLine);
                        writer.newLine(); // Add a newline character to separate lines
                    }
                }
            }

            System.out.println("Replacement completed successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}



