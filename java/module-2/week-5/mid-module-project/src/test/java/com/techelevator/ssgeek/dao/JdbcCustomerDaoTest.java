package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class JdbcCustomerDaoTest extends BaseDaoTests {

    private JdbcCustomerDao dao;

    private static final Customer CUSTOMER_1 = new Customer(1, "Customer 1", "Addr 1-1", "Addr 1-2", "City 1", "S1", "11111");
    private static final Customer CUSTOMER_2 = new Customer(2, "Customer 2", "Addr 2-1", "Addr 2-2", "City 2", "S2", "22222");
    private static final Customer CUSTOMER_3 = new Customer(3, "Customer 3", "Addr 3-1", null, "City 3", "S3", "33333");
    private static final Customer CUSTOMER_4 = new Customer(4, "Customer 4", "Addr 4-1", null, "City 4", "S4", "44444");

    @Before
    public void setup() {
        dao = new JdbcCustomerDao(dataSource);
    }

    @Test
    public void getCustomerById_returns_correct_customer() {
        Customer customer = dao.getCustomerById(CUSTOMER_1.getCustomerId());
        assertCustomerMatch(CUSTOMER_1, customer);

        customer = dao.getCustomerById(CUSTOMER_2.getCustomerId());
        assertCustomerMatch(CUSTOMER_2, customer);
    }

    @Test
    public void getCustomers_returns_all_customers() {
        List<Customer> customers = dao.getCustomers();
        Assert.assertEquals("getCustomers should return the correct number of customers", 4, customers.size());
        assertCustomerMatch(CUSTOMER_1, customers.get(0));
        assertCustomerMatch(CUSTOMER_2, customers.get(1));
        assertCustomerMatch(CUSTOMER_3, customers.get(2));
        assertCustomerMatch(CUSTOMER_4, customers.get(3));
    }

    @Test
    public void createCustomer_returns_new_customer_with_id() {
        Customer newCustomer = new Customer(0, "New Customer", "New Addr 1", null, "New City", "NC", "55555");
        Customer createdCustomer = dao.createCustomer(newCustomer);

        Assert.assertTrue(createdCustomer.getCustomerId() > 0);

        Customer retrievedCustomer = dao.getCustomerById(createdCustomer.getCustomerId());
        assertCustomerMatch(createdCustomer, retrievedCustomer);
    }

    @Test
    public void updateCustomer_has_expected_values() {
        Customer customerToUpdate = dao.getCustomerById(CUSTOMER_1.getCustomerId());
        customerToUpdate.setName("Updated Name");
        customerToUpdate.setStreetAddress1("Updated Address");
        customerToUpdate.setCity("Updated City");

        dao.updateCustomer(customerToUpdate);

        Customer updatedCustomer = dao.getCustomerById(customerToUpdate.getCustomerId());
        assertCustomerMatch(customerToUpdate, updatedCustomer);
    }

    private void assertCustomerMatch(Customer expected, Customer actual) {
        Assert.assertEquals(expected.getCustomerId(), actual.getCustomerId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getStreetAddress1(), actual.getStreetAddress1());
        Assert.assertEquals(expected.getStreetAddress2(), actual.getStreetAddress2());
        Assert.assertEquals(expected.getCity(), actual.getCity());
        Assert.assertEquals(expected.getState(), actual.getState());
        Assert.assertEquals(expected.getZipCode(), actual.getZipCode());
    }
}

