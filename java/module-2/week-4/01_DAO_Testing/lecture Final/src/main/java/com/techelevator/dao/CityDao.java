package com.techelevator.dao;

import com.techelevator.model.City;

import java.util.List;

public interface CityDao {

    City getCityById(int cityId);

    List<City> getCitiesByState(String stateAbbreviation);

    City createCity(City city);

    City updateCity(City city);

    int deleteCityById(int cityId);
}
