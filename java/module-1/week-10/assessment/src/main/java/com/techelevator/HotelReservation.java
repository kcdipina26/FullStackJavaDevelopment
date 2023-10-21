package com.techelevator;

public class HotelReservation {
    /*
    Fill in the class details here...
    Things to build on properties of hotel reservation class
    Name of the customer for the reservation .

     */
private String name;

private int numberOfNights; //reservation is for ? no of nights.

    private int nightlyRate; //Price of the room per night (in $)

    private int estimateTotal; //total price the estimate for the reservation ( in $)

    //Building the constructors name of custome and everythign else




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public int getNightlyRate() {
        return nightlyRate;
    }

    public void setNightlyRate(int nightlyRate) {
        this.nightlyRate = nightlyRate;
    }

    public int getEstimateTotal() {
        return estimateTotal;
    }

    public void setEstimateTotal(int estimateTotal) {
        this.estimateTotal = estimateTotal;
    }

    public HotelReservation(String name, int numberOfNights, int nightlyRate) {
        this.name = name;
        this.numberOfNights = numberOfNights;
        this.nightlyRate = nightlyRate;

        //we have to calculate the total estimate no of Nights and nightlyRte

        this.estimateTotal = numberOfNights * nightlyRate;

        // we have to set up getter and setters


    }


// calculation of additional charges....

    public String getActualTotal(boolean requiresCleaning, boolean usedMinibar) {
        int cleaningFee = 0;
        if (requiresCleaning) {
            cleaningFee = 25;
            if (usedMinibar) {
                cleaningFee *= 2;

            }
        }
    }
       // return this.estimateTotal + cleaningFee;

        //  int minibarFee = usedMinibar ? 15

        //  @Override

        //  public String toString() {
        //return this.name + ";" + this.estimateTotal;




}
