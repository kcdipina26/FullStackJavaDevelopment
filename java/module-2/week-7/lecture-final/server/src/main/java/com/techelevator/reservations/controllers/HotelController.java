package com.techelevator.reservations.controllers;

import com.techelevator.reservations.dao.HotelDao;
import com.techelevator.reservations.dao.ReservationDao;
import com.techelevator.reservations.model.Hotel;
import com.techelevator.reservations.model.Reservation;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HotelController {

    private HotelDao hotelDao;
    private ReservationDao reservationDao;

    public HotelController (HotelDao hotelDao,  ReservationDao reservationDao){
        this.hotelDao = hotelDao;
        this.reservationDao = reservationDao;
    }


    @GetMapping(path = "/hotels")
    public List<Hotel> getHotels(@RequestParam(required = false) String state, @RequestParam(required = false) String city){
        return hotelDao.getFilteredList(state, city);
    }

    @RequestMapping(path = "/hotels/{id}", method = RequestMethod.GET)
    public Hotel getHotel(@PathVariable int id) {
        Hotel hotel = hotelDao.get(id);
        if(hotel == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found");
        }
        return hotel;
    }

    @RequestMapping(path = "/hotels/{id}/reservations", method = RequestMethod.GET)
    public List<Reservation> reservations(@PathVariable() int id) {
        List <Reservation> reservations = reservationDao.findByHotel(id);
        return reservations;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/reservations")
    public Reservation addReservation(@Valid @RequestBody Reservation reservation) {
        return reservationDao.create(reservation);
    }

    @PutMapping(path = "reservations/{id}")
    public Reservation update(@Valid @RequestBody Reservation reservation, @PathVariable int id){
        Reservation updatedReservation = reservationDao.update(reservation, id);
        if(updatedReservation == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found");
        }
        return updatedReservation;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/reservations/{id}")
    public void delete(@Valid @PathVariable int id) {
        reservationDao.delete(id);
    }
}
