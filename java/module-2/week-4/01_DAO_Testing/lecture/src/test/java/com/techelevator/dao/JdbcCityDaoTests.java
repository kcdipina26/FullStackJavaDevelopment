package com.techelevator.dao;

import com.techelevator.model.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JdbcCityDaoTests extends BaseDaoTests {

    private static final City CITY_1 = new City(1, "City 1", "AA", 11, 111);
    private static final City CITY_2 = new City(2, "City 2", "BB", 22, 222);
    private static final City CITY_4 = new City(4, "City 4", "AA", 44, 444);

    private City testCity;

    private JdbcCityDao sut;

    @Before
    public void setup() {
        sut = new JdbcCityDao(dataSource);
        testCity = new City(0, "Test City", "CC", 99, 999);
    }

    @Test
    public void getCityById_returns_correct_city() {
        City city = sut.getCityById(1);
        assertCitiesMatch(CITY_1, city);

        city = sut.getCityById(2);
        assertCitiesMatch(CITY_2, city);
    }

    @Test
    public void getCityById_returns_null_when_id_not_found() {
        City city = sut.getCityById(99);
        Assert.assertNull(city);
    }

    @Test
    public void getCitiesByState_returns_all_cities_for_state() {
        List<City> cities = sut.getCitiesByState("AA");
        Assert.assertEquals(2, cities.size());
        assertCitiesMatch(CITY_1, cities.get(0));
        assertCitiesMatch(CITY_4, cities.get(1));

        cities = sut.getCitiesByState("BB");
        Assert.assertEquals(1, cities.size());
        assertCitiesMatch(CITY_2, cities.get(0));
    }

    @Test
    public void getCitiesByState_returns_empty_list_for_abbreviation_not_in_db() {
        List<City> cities = sut.getCitiesByState("XX");
        Assert.assertEquals(0, cities.size());
    }

    @Test
    public void createCity_returns_city_with_id_and_expected_values() {
        City createdCity = sut.createCity(testCity);

        int newId = createdCity.getCityId();
        Assert.assertTrue(newId > 0);

        City retrievedCity = sut.getCityById(newId);
        assertCitiesMatch(createdCity, retrievedCity);
    }

    @Test
    public void updated_city_has_expected_values_when_retrieved() {
        City cityToUpdate = sut.getCityById(1);

        cityToUpdate.setCityName("Updated");
        cityToUpdate.setStateAbbreviation("CC");
        cityToUpdate.setPopulation(99);
        cityToUpdate.setArea(999);

        sut.updateCity(cityToUpdate);

        City retrievedCity = sut.getCityById(1);
        assertCitiesMatch(cityToUpdate, retrievedCity);
    }

    @Test
    public void deleted_city_cant_be_retrieved() {
        sut.deleteCityById(4);

        City retrievedCity = sut.getCityById(4);
        Assert.assertNull(retrievedCity);
    }

    private void assertCitiesMatch(City expected, City actual) {
        Assert.assertEquals(expected.getCityId(), actual.getCityId());
        Assert.assertEquals(expected.getCityName(), actual.getCityName());
        Assert.assertEquals(expected.getStateAbbreviation(), actual.getStateAbbreviation());
        Assert.assertEquals(expected.getPopulation(), actual.getPopulation());
        Assert.assertEquals(expected.getArea(), actual.getArea(), 0.1);
    }
}
