package com.techelevator;

import org.junit.Assert;
import org.junit.Test;


public class CigarPartyTest {
    @Test

    public void party_successful_between_40_and_60(){
        //Arrange
        CigarParty cigarparty = new CigarParty();

        //Act

        //Assert
        Assert.assertFalse(cigarparty.haveParty(30, false));
        Assert.assertTrue(cigarparty.haveParty(50, false));
        Assert.assertTrue(cigarparty.haveParty(70, true));
    }
}
