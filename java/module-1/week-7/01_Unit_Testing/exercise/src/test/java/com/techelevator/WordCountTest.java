package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import java.util.Map;



public class WordCountTest {

    @Test
    public void testGetCount() {
        // Arrange
        WordCount wordCounter = new WordCount();
        String[] words = {"ba", "ba", "black", "sheep"};

        // Act
        Map<String, Integer> result = wordCounter.getCount(words);

        // Assert
        Assert.assertEquals(2, result.get("ba").intValue());
        Assert.assertEquals(1, result.get("black").intValue());
        Assert.assertEquals(1, result.get("sheep").intValue());
    }
}


