package com.techelevator.dao;

import com.techelevator.model.Park;

import java.util.List;

public interface ParkDao {


    // Create park
    Park createPark(Park park);

    // Read from database
    Park getParkById(int parkId);
    List<Park> getParksByState(String stateAbbreviation);

    // Update park
    Park updatePark (Park park);

    // Delete park
    int deleteParkById(int parkId);

    void linkParkState(int parkId, String stateAbbreviation);

    void unlinkParkState(int parkId, String stateAbbreviation);

}
