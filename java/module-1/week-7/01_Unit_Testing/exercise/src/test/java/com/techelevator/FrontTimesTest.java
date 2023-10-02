package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class FrontTimesTest {

    @Test
    public void first_3_chars_return_substring_x_number_of_times() {

        //Arrange
        FrontTimes frontTimes = new FrontTimes();

        //Act

        //Assert

        Assert.assertEquals(frontTimes.generateString("Chocolate", 2), "ChoCho");
        Assert.assertEquals(frontTimes.generateString("Chocolate", 3), "ChoChoCho");
        Assert.assertEquals(frontTimes.generateString("Abc", 3), "AbcAbcAbc");
    }
}
