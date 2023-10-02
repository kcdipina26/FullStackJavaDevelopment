package com.techelevator;

import org.junit.Assert;
import org.junit.Test;


public class NonStartTest {

        @Test
        public void testGetPartialString() {
            // Arrange
            NonStart nonStart = new NonStart();

            // Act
            String result1 = nonStart.getPartialString("Hello", "There");
            String result2 = nonStart.getPartialString("java", "code");
            String result3 = nonStart.getPartialString("shotl", "java");

            // Assert
            Assert.assertEquals("ellohere", result1);  // leave  first char of "Hello" and "There" and concatenate
            Assert.assertEquals("avaode", result2);    // leave first char of "java" and "code" and concatenate
            Assert.assertEquals("hotlava", result3);   // leave first char of "shotl" and "java" and concatenate
        }
    }


