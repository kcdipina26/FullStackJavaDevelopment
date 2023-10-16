package com.lendingcatalog.util;



import com.lendingcatalog.util.exception.FileStorageException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class FileStorageService {
    public static void writeContentsToFile(Path filePath, String content) throws FileStorageException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            throw new FileStorageException("Error writing to file: " + filePath.toString(), e);
        }
    }

    // Implement the readContentsOfFile method similarly
    public static String readContentsOfFile(Path filePath) throws FileStorageException {
        try {
            return Files.lines(filePath)
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new FileStorageException("Error reading from file: " + filePath.toString(), e);
        }
    }
}


