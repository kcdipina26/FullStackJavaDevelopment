package com.techelevator;

public class Airplane {

    private String planeNumber;
    private int totalFirstClassSeats;
    private int bookedFirstClassSeats;
    private int totalCoachSeats;
    private int bookedCoachSeats;

    // Here we are using the constructor and our methods
    public Airplane(String planeNumber, int totalFristClassSeats, int totalCoachSeats) {
        this.planeNumber = planeNumber;
        this.totalFirstClassSeats = totalFristClassSeats;
        this.totalCoachSeats = totalCoachSeats;
        this.bookedFirstClassSeats = 0;
        this.bookedCoachSeats = 0;


    }
// Now describing getter metthod ...

    public String getPlaneNumber() {
        return planeNumber;
    }

    public int getTotalFirstClassSeats() {
        return totalFirstClassSeats;
    }

    public int getBookedFirstClassSeats() {
        return bookedFirstClassSeats;
    }

    public int getTotalCoachSeats() {
        return totalCoachSeats;
    }

    public int getBookedCoachSeats() {
        return bookedCoachSeats;
    }

    public int getAvailableFirstClassSeats() {
        return totalFirstClassSeats - bookedFirstClassSeats;

    }

    public int getAvailableCoachSeats() {
        return totalCoachSeats - bookedCoachSeats;

    }


    //Now describing our methods

    public int calculateAvailableFirstClassSeats() {
       return totalFirstClassSeats - bookedFirstClassSeats;
    }
    public int calculateAvailableCoachSeats() {
       return totalCoachSeats - bookedCoachSeats;
    }
    // Describing reserveSeats method

    public boolean reserveSeats(boolean forFirstClass, int totalNumberOfSeats) {
        if (forFirstClass) {
            if (totalNumberOfSeats <= calculateAvailableFirstClassSeats()) {
                bookedFirstClassSeats += totalNumberOfSeats;
                return true;
            }

        } else {
            if (totalNumberOfSeats <= calculateAvailableCoachSeats()) {
                bookedCoachSeats += totalNumberOfSeats;
                return true;
            }
        }
        return false;
    }

}

