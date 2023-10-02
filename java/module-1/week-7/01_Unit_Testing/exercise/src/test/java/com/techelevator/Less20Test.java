package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
public class Less20Test {
        @Test
        public void testIsLessThanMultipleOf20() {
            // Arrange
            Less20 less20 = new Less20();

            // Act and Assert
            Assert.assertTrue(less20.isLessThanMultipleOf20(18));  //  As  18 is one less than a multiple of 20
            Assert.assertTrue(less20.isLessThanMultipleOf20(19));  //  As 19 is two less than a multiple of 20
            Assert.assertTrue(less20.isLessThanMultipleOf20(38));  //  As 38 is one less than a multiple of 20
            Assert.assertTrue(less20.isLessThanMultipleOf20(39));  // As 39 is two less than a multiple of 20
            Assert.assertFalse(less20.isLessThanMultipleOf20(20));  // As  20 is not less than a multiple of 20
            Assert.assertFalse(less20.isLessThanMultipleOf20(40));  //  As 40 is not less than a multiple of 20
        }
    }


