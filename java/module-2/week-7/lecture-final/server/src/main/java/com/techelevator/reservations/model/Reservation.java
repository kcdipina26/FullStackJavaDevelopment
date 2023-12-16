package com.techelevator.reservations.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Reservation {

    private int id;
    @Min (value=1, message="The field hotelId is required")
    private int hotelId;
    @NotBlank( message = "Name field is required")
    private String fullName;
    @NotBlank( message = "Check in field is required")
    private String checkinDate;
    @NotBlank( message = "Checkout field is required")
    private String checkoutDate;
    @Min(value = 1, message ="Minimum number of guests is 1")
    @Max(value = 5, message = "Max number of guests is 5")
    private int guests;

    public Reservation() {

    }

    public Reservation(int id, int hotelId, String fullName, String checkinDate, String checkoutDate, int guests) {
        this.id = id;
        this.hotelId = hotelId;
        this.fullName = fullName;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.guests = guests;
    }

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

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
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
                "\n Checkin Date: " + checkinDate +
                "\n Checkout Date: " + checkoutDate +
                "\n Guests: " + guests;
    }
}
