package com.techelevator.dao;

import com.techelevator.model.City;
import com.techelevator.model.Park;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class JdbcParkDaoTests extends BaseDaoTests {


    public static final Park PARK_1 = new Park(1, "Park 1", LocalDate.parse("1800-01-02"), 100, true);
    public static final Park PARK_2 = new Park(2, "Park 2", LocalDate.parse("1900-12-31"), 200, false);
    public static final Park PARK_3 = new Park(3, "Park 3", LocalDate.parse("2000-06-15"), 300, false);

    private Park testPark;

    private JdbcParkDao sut;

    @Before
    public void setup() {
        sut = new JdbcParkDao(dataSource);
        testPark = new Park(0, "Test Park" , LocalDate.now(), 400, true);
    }

    @Test
    public void getParkById_returns_correct_park(){
        Park park = sut.getParkById(1);
        assertParksMatch(PARK_1, park);

        park = sut.getParkById(2);
        assertParksMatch(PARK_2, park);
    }

    @Test
    public void getParkById_returns_null_when_id_not_found() {
        Park park = sut.getParkById(99);
        Assert.assertNull(park);

    }

    @Test
    public void getParksByState_returns_all_parks_for_state(){
        List<Park> parks = sut.getParksByState("AA");
        Assert.assertEquals(2, parks.size());
        assertParksMatch(PARK_1, parks.get(0));
        assertParksMatch(PARK_3, parks.get(1));

        parks = sut.getParksByState("BB");
        Assert.assertEquals(1, parks.size());
        assertParksMatch(PARK_2, parks.get(0));
    }

    @Test
    public void createPark_returns_park_with_expected_values(){
        Park createdPark = sut.createPark(testPark);

        int newId = createdPark.getParkId();
        Assert.assertTrue(newId > 0);

        Park retrievedPark = sut.getParkById(newId);
        assertParksMatch(createdPark, retrievedPark);
    }


    @Test
    public void getParksByState_returns_empty_list_for_state_not_in_database(){
        List<Park> parks = sut.getParksByState("XX");
        Assert.assertEquals(0, parks.size());
    }


    @Test
    public void updatePark_has_expected_values(){
        Park parkToUpdate = sut.getParkById(1);
        parkToUpdate.setParkName("Test Park");
        parkToUpdate.setDateEstablished(LocalDate.now());
        parkToUpdate.setArea(400);
        parkToUpdate.setHasCamping(false);
        sut.updatePark(parkToUpdate);

        Park retrievedPark = sut.getParkById(1);
        assertParksMatch(parkToUpdate, retrievedPark);
    }

    @Test
    public void deletePark_deleted_park_not_retrieved(){
        sut.deleteParkById(1);
        Park retrievedPark = sut.getParkById(1);
        Assert.assertNull(retrievedPark);
    }

    @Test
    public void linkPark_park_is_added_to_park_state(){
        sut.linkParkState(1, "CC");

        List<Park> parks = sut.getParksByState("CC");
        Assert.assertEquals(2, parks.size());
        assertParksMatch(PARK_1, parks.get(0));
    }

    @Test
    public void unlinkPark_park_removed_from_park_state(){
        sut.unlinkParkState(1,"AA");
        List<Park> parks = sut.getParksByState("AA");
        Assert.assertEquals(1, parks.size());
        assertParksMatch(PARK_3, parks.get(0));
    }

    private void assertParksMatch(Park expected, Park actual) {
        Assert.assertEquals(expected.getParkId(), actual.getParkId());
        Assert.assertEquals(expected.getParkName(), actual.getParkName());
        Assert.assertEquals(expected.getDateEstablished(), actual.getDateEstablished());
        Assert.assertEquals(expected.isHasCamping(), actual.isHasCamping());
        Assert.assertEquals(expected.getArea(), actual.getArea(), 0.1);
    }








}
