package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class DateFashionTest {

    @Test
    public void testGetATable_NoTableIfEitherNotStylish() {
        // Arrange
        DateFashion dateFashion = new DateFashion();

        // Act and Assert
        Assert.assertEquals(0, dateFashion.getATable(2, 5));  // You not stylish
        Assert.assertEquals(0, dateFashion.getATable(5, 2));  // Date not stylish
        Assert.assertEquals(0, dateFashion.getATable(2, 2));  // Both not stylish
    }

    @Test
    public void testGetATable_MaybeTableIfBothStylishButNotVery() {
        // Arrange
        DateFashion dateFashion = new DateFashion();

        // Act and Assert
        Assert.assertEquals(1, dateFashion.getATable(5, 5));  // Both stylish but not very
        Assert.assertEquals(1, dateFashion.getATable(7, 7));  // Both stylish but not very
    }

    @Test
    public void testGetATable_GetTableIfEitherVeryStylish() {
        // Arrange
        DateFashion dateFashion = new DateFashion();

        // Act and Assert
        Assert.assertEquals(2, dateFashion.getATable(8, 5));  // You very stylish
        Assert.assertEquals(2, dateFashion.getATable(5, 8));  // Date very stylish
        Assert.assertEquals(2, dateFashion.getATable(8, 8));  // Both very stylish
    }
}
