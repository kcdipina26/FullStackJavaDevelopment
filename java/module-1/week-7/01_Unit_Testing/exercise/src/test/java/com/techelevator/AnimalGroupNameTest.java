package com.techelevator;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimalGroupNameTest {

    private final AnimalGroupName animalGroupName = new AnimalGroupName();

    @Test
    public void testAnimalNameShouldReturnGroupName() {
        // Arrange
        // Nothing to arrange in this test, as you're testing a method in isolation

        // Act
        String group = animalGroupName.getHerd("Elephant");

        // Assert
        Assert.assertEquals("Herd", group);
    }

    @Test
    public void testAnimalGroupNameReturnsCaseInsensitive() {
        // Arrange
        // Nothing to arrange in this test, as you're testing a method in isolation

        // Act
        String group = animalGroupName.getHerd("Cow");

        // Assert
        Assert.assertEquals("unknown", group);
    }
}

