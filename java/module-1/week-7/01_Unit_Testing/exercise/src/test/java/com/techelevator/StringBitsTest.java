package com.techelevator;

import org.junit.Assert;
import org.junit.Test;


public class StringBitsTest {

    @Test
    public void testGetBits() {
        // Arrange
        StringBits getBits = new StringBits();

        // Act
        String result1 = getBits.getBits("Hello");
        String result2 = getBits.getBits("Hi");
        String result3 = getBits.getBits("Heeololeo");


        // Assert
        Assert.assertEquals("Hlo", result1);  // Every other char starting with the first
        Assert.assertEquals("H", result2);     // Only the first character
        Assert.assertEquals("Hello", result3); // Every other char starting with the first
    }
}

