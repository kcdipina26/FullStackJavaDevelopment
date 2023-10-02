package com.techelevator;

import org.junit.Assert;
import org.junit.Test;


public class MaxEnd3Test {
        @Test
        public void testMakeArray() {
            // Arrange
            MaxEnd3 maxEnd3 = new MaxEnd3();

            // Act
            int[] result1 = maxEnd3.makeArray(new int[]{1, 2, 3});
            int[] result2 = maxEnd3.makeArray(new int[]{11, 5, 9});
            int[] result3 = maxEnd3.makeArray(new int[]{2, 11, 3});

            // Assert
            Assert.assertArrayEquals(new int[]{3, 3, 3}, result1);  // Here First element (1) is smaller, replace with 3
            Assert.assertArrayEquals(new int[]{11, 11, 11}, result2); // Now Last element (9) is smaller, replace with 11
            Assert.assertArrayEquals(new int[]{3, 3, 3}, result3);  // Again Last element (3) is smaller, replace with 3
        }
    }


