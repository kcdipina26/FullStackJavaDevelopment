package com.techelevator;

import org.junit.Assert;
import org.junit.Test;


public class Lucky13Test {

    @Test
      public void testGetLucky() {
            // Arrange
        Lucky13 lucky13 = new Lucky13();

            // Act and Assert
        Assert.assertTrue(lucky13.getLucky(new int[]{0, 2, 4}));   // No 1's or 3's, should return true
        Assert.assertFalse(lucky13.getLucky(new int[]{1, 2, 3}));  // Contains 1 and 3, should return false
        Assert.assertFalse(lucky13.getLucky(new int[]{1, 2, 4}));  // Contains 1, should return false
    }
        }




