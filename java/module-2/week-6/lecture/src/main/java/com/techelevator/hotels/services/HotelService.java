package com.techelevator.hotels.services;

import com.techelevator.hotels.model.Hotel;
import com.techelevator.hotels.model.Reservation;
import com.techelevator.util.BasicLogger;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


public class HotelService {

    private static final String API_BASE_URL = "http://localhost:3000/";
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * List all hotels in the system
     */
    public Hotel[] listHotels() {
        // TODO: Implement method
        return null;
    }

    /**
     * List all reservations in the hotel reservation system
     */
    public Reservation[] listReservations() {
        // TODO: Implement method
        return null;
    }

    /**
     * List all reservations by hotel id.
     */
    public Reservation[] listReservationsByHotel(int hotelId) {
        // TODO: Implement method
        return null;
    }

    /**
     * Find a single reservation by the reservationId
     */
    public Reservation getReservation(int reservationId) {
        // TODO: Implement method
        return null;
    }

    /**
     * Create a new reservation in the hotel reservation system
     */
    public Reservation addReservation(Reservation newReservation) {
        // TODO: Implement method
        return null;
    }

    /**
     * Updates an existing reservation by replacing the old one with a new
     * reservation
     */
    public boolean updateReservation(Reservation updatedReservation) {
        // TODO: Implement method
        return false;
    }

    /**
     * Delete an existing reservation
     */
    public boolean deleteReservation(int id) {
        // TODO: Implement method
        return false;
    }
}
