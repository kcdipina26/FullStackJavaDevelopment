package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerDao implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCustomerDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT customer_id, name, street_address1, street_address2, city, state, zip_code FROM customer WHERE customer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
        if (results.next()) {
            return mapRowToCustomer(results);
        }
        return null;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, name, street_address1, street_address2, city, state, zip_code FROM customer ORDER BY customer_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            customers.add(mapRowToCustomer(results));
        }
        return customers;
    }

    @Override
    public Customer createCustomer(Customer newCustomer) {
        String sql = "INSERT INTO customer (name, street_address1, street_address2, city, state, zip_code) VALUES (?, ?, ?, ?, ?, ?) RETURNING customer_id";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                newCustomer.getName(),
                newCustomer.getStreetAddress1(),
                newCustomer.getStreetAddress2(),
                newCustomer.getCity(),
                newCustomer.getState(),
                newCustomer.getZipCode());
        newCustomer.setCustomerId(newId); // Corrected to setCustomerId
        return newCustomer;
    }

    @Override
    public Customer updateCustomer(Customer updatedCustomer) {
        String sql = "UPDATE customer SET name = ?, street_address1 = ?, street_address2 = ?, city = ?, state = ?, zip_code = ? WHERE customer_id = ?";
        jdbcTemplate.update(sql,
                updatedCustomer.getName(),
                updatedCustomer.getStreetAddress1(),
                updatedCustomer.getStreetAddress2(),
                updatedCustomer.getCity(),
                updatedCustomer.getState(),
                updatedCustomer.getZipCode(),
                updatedCustomer.getCustomerId()); // Corrected to getCustomerId
        return updatedCustomer;
    }

    private Customer mapRowToCustomer(SqlRowSet rs) {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id")); // Corrected to setCustomerId
        customer.setName(rs.getString("name"));
        customer.setStreetAddress1(rs.getString("street_address1"));
        customer.setStreetAddress2(rs.getString("street_address2"));
        customer.setCity(rs.getString("city"));
        customer.setState(rs.getString("state"));
        customer.setZipCode(rs.getString("zip_code"));
        return customer;
    }
}
