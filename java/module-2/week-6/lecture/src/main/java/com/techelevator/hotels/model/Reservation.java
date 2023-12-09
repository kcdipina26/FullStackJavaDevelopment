package com.techelevator.hotels.model;

public class Reservation {

    private int id;
    private int hotelId;
    private String fullName;
    // Use LocalDate for dates but storing as a string to keep this example simple
    private String checkInDate;
    private String checkoutDate;
    private int guests;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getcheckInDate() {
        return checkInDate;
    }

    public void setcheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------" +
                "\n Reservation Details" +
                "\n--------------------------------------------" +
                "\n Id: " + id +
                "\n Hotel Id: " + hotelId +
                "\n Full Name: " + fullName +
                "\n Check in Date: " + checkInDate +
                "\n Checkout Date: " + checkoutDate +
                "\n Guests: " + guests;
    }
}
