package com.techelevator;

import org.junit.Assert;
import  org.junit.Test;


public class SameFirstLastTest {
    @Test
    public void testIsItTheSame() {
        // Arrange
        SameFirstLast sameFirstLast = new SameFirstLast();

        // Act
        boolean result1 = sameFirstLast.isItTheSame(new int[]{1, 2, 3});
        boolean result2 = sameFirstLast.isItTheSame(new int[]{1, 2, 3, 1});
        boolean result3 = sameFirstLast.isItTheSame(new int[]{1, 2, 1});

        // Assert
        Assert.assertFalse(result1);  // Array has different first and last elements
        Assert.assertTrue(result2);   // Array has the same first and last elements
        Assert.assertTrue(result3);   // Array has the same first and last elements
    }
}


