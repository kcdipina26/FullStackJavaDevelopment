package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Sale;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public class JdbcSaleDaoTest extends BaseDaoTests {

    private JdbcSaleDao dao;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcSaleDao(dataSource);
        // It's a good practice to clear and setup database state before tests if not handled globally
    }

    @Test
    public void getSaleById_returns_correct_sale() {
        Sale sale = dao.getSaleById(1);
        Assert.assertNotNull(sale);
        Assert.assertEquals(1, sale.getSaleId());
        Assert.assertEquals(1, sale.getCustomerId()); // Corrected to check for actual customer ID
        Assert.assertEquals(LocalDate.of(2022, 1, 1), sale.getSaleDate());
        Assert.assertNull(sale.getShipDate()); // Sale ID 1 has a null ship date in test data
        Assert.assertEquals("Customer 1", sale.getCustomerName()); // Verifying the customer name is correct
    }

    @Test
    public void getUnshippedSales_returns_sales_with_null_ship_date() {
        List<Sale> sales = dao.getUnshippedSales();
        Assert.assertFalse(sales.isEmpty());
        for (Sale sale : sales) {
            Assert.assertNull(sale.getShipDate());
        }
    }

    @Test
    public void getSalesByCustomerId_returns_correct_sales() {
        List<Sale> sales = dao.getSalesByCustomerId(1);
        Assert.assertFalse(sales.isEmpty());
        for (Sale sale : sales) {
            Assert.assertEquals(1, sale.getCustomerId());
            // Additional assertions for other sale attributes
        }
    }

    @Test
    public void getSalesByProductId_returns_correct_sales() {
        List<Sale> sales = dao.getSalesByProductId(1);
        Assert.assertFalse(sales.isEmpty());
        // Since product ID 1 is sold in sales with ID 1 and 2, check for these IDs
        Assert.assertTrue(sales.stream().anyMatch(s -> s.getSaleId() == 1));
        Assert.assertTrue(sales.stream().anyMatch(s -> s.getSaleId() == 2));
    }

    @Test
    public void createSale_returns_sale_with_id_and_expected_values() {
        Sale newSale = new Sale(0, 1, LocalDate.now(), null, "Customer 1");
        Sale createdSale = dao.createSale(newSale);
        Assert.assertNotNull(createdSale);
        Assert.assertNotEquals(0, createdSale.getSaleId());
        // More assertions for the returned sale object
    }

    @Test
    public void updated_sale_has_expected_values_when_retrieved() {
        Sale saleToUpdate = dao.getSaleById(1);
        LocalDate newShipDate = LocalDate.now();
        saleToUpdate.setShipDate(newShipDate);
        dao.updateSale(saleToUpdate);

        Sale updatedSale = dao.getSaleById(1);
        Assert.assertNotNull(updatedSale);
        Assert.assertEquals(newShipDate, updatedSale.getShipDate());
    }

    @Test
    public void deleted_sale_cant_be_retrieved() {
        int saleIdToDelete = 2;

        // First, delete the line_items associated with the sale
        jdbcTemplate.update("DELETE FROM line_item WHERE sale_id = ?", saleIdToDelete);

        // Now, delete the sale
        int deletedRows = dao.deleteSaleById(saleIdToDelete);
        Assert.assertEquals(1, deletedRows);

        Sale retrievedSale = dao.getSaleById(saleIdToDelete);
        Assert.assertNull(retrievedSale);
    }

    @Test
    public void getSaleById_returns_null_when_id_not_found() {
        int nonExistentSaleId = Integer.MAX_VALUE;
        Sale sale = dao.getSaleById(nonExistentSaleId);
        Assert.assertNull(sale);
    }
}
