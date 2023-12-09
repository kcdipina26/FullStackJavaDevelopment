package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Park;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcParkDao implements ParkDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcParkDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Park createPark(Park park) {
        Park newPark = null;
        String sql = "INSERT INTO park(park_name, date_established, area, has_camping) " +
                     "VALUES(?,?,?,?) RETURNING park_id;";

        int newParkId = jdbcTemplate.queryForObject(sql, int.class, park.getParkName(), park.getDateEstablished(), park.getArea(), park.isHasCamping());

        newPark = getParkById(newParkId);
        return newPark;
    }

    @Override
    public Park getParkById(int parkId) {
        Park park = null;
        String sql = "SELECT park_id, park_name, date_established, area, has_camping FROM park WHERE park_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
        if (results.next()){
            park = mapRowToPark(results);
        }
        return park;
    }

    @Override
    public List<Park> getParksByState(String stateAbbreviation) {
        List<Park> parks = new ArrayList<>();
        String sql = "SELECT p.park_id, p.park_name, p.date_established, p.area, p.has_camping " +
                "FROM park p " +
                "JOIN park_state ps USING (park_id) " +
                "WHERE state_abbreviation = ? " +
                "ORDER BY p.park_id;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stateAbbreviation);
        while (results.next()){
            parks.add(mapRowToPark(results));
        }
        return parks;
    }

    @Override
    public Park updatePark(Park park) {
        Park updatedPark = null;
        String sql = "UPDATE park SET park_name = ?, date_established = ?, area = ?, has_camping = ? " +
        "WHERE park_id = ?";

        int numberOfRows =  jdbcTemplate.update(sql, park.getParkName(), park.getDateEstablished(), park.getArea(), park.isHasCamping(), park.getParkId());
        if(numberOfRows == 0){
            throw new DaoException("no rows updated");
        }
        updatedPark = getParkById(park.getParkId());

        return updatedPark;
    }

    @Override
    public int deleteParkById(int parkId) {
        int numberOfRows = 0;
        String parkStateSql = "DELETE FROM park_state WHERE park_id = ?";
        String parkSql = "DELETE FROM park WHERE park_id = ?";

        jdbcTemplate.update(parkStateSql, parkId);
        numberOfRows = jdbcTemplate.update(parkSql, parkId);

        return numberOfRows;

    }

    @Override
    public void linkParkState(int parkId, String stateAbbreviation) {
        String sql = "INSERT INTO park_state (park_id, state_abbreviation) VALUES(?,?)";
        jdbcTemplate.update(sql, parkId, stateAbbreviation);
    }

    @Override
    public void unlinkParkState(int park, String stateAbbreviation) {
        String sql = "DELETE FROM park_state WHERE park_id = ? AND state_abbreviation = ?";
        jdbcTemplate.update(sql, park, stateAbbreviation);
    }

    private Park mapRowToPark(SqlRowSet rowSet){
        Park park = new Park();
        park.setParkId(rowSet.getInt("park_id"));
        park.setParkName(rowSet.getString("park_name"));
        if(rowSet.getDate("date_established") != null) {
            park.setDateEstablished(rowSet.getDate("date_established").toLocalDate());
        }
        park.setArea(rowSet.getDouble("area"));
        park.setHasCamping(rowSet.getBoolean("has_camping"));


        return park;
    }

}
