package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Sale;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcSaleDao implements SaleDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcSaleDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Sale getSaleById(int saleId) {
        String sql = "SELECT s.sale_id, s.customer_id, s.sale_date, s.ship_date, c.name AS customer_name " +
                "FROM sale s " +
                "JOIN customer c ON s.customer_id = c.customer_id " +
                "WHERE s.sale_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, saleId);
        if (results.next()) {
            return mapRowToSale(results);
        }
        return null;
    }

    @Override
    public List<Sale> getUnshippedSales() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT s.sale_id, s.customer_id, s.sale_date, s.ship_date, c.name AS customer_name " +
                "FROM sale s " +
                "JOIN customer c ON s.customer_id = c.customer_id " +
                "WHERE s.ship_date IS NULL " +
                "ORDER BY s.sale_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            sales.add(mapRowToSale(results));
        }
        return sales;
    }
    @Override
    public List<Sale> getSalesByCustomerId(int customerId) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT s.sale_id, s.customer_id, s.sale_date, s.ship_date, c.name AS customer_name " +
                "FROM sale s " +
                "JOIN customer c ON s.customer_id = c.customer_id " +
                "WHERE s.customer_id = ? " +
                "ORDER BY s.sale_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
        while (results.next()) {
            sales.add(mapRowToSale(results));
        }
        return sales;
    }

    @Override
    public List<Sale> getSalesByProductId(int productId) {
        // This method requires a join with the line_item table to filter sales by product.
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT DISTINCT s.sale_id, s.customer_id, s.sale_date, s.ship_date, c.name AS customer_name " +
                "FROM sale s " +
                "JOIN line_item li ON s.sale_id = li.sale_id " +
                "JOIN customer c ON s.customer_id = c.customer_id " +
                "WHERE li.product_id = ? " +
                "ORDER BY s.sale_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
        while (results.next()) {
            sales.add(mapRowToSale(results));
        }
        return sales;
    }

    @Override
    public Sale createSale(Sale newSale) {
        String sql = "INSERT INTO sale (customer_id, sale_date, ship_date) VALUES (?, ?, ?) RETURNING sale_id";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                newSale.getCustomerId(),
                newSale.getSaleDate(),
                newSale.getShipDate());
        newSale.setSaleId(newId);
        return newSale;
    }

    @Override
    public Sale updateSale(Sale updatedSale) {
        String sql = "UPDATE sale SET customer_id = ?, sale_date = ?, ship_date = ? WHERE sale_id = ?";
        jdbcTemplate.update(sql,
                updatedSale.getCustomerId(),
                updatedSale.getSaleDate(),
                updatedSale.getShipDate(),
                updatedSale.getSaleId());
        return updatedSale;
    }

    @Override
    public int deleteSaleById(int saleId) {
        String sql = "DELETE FROM sale WHERE sale_id = ?";
        return jdbcTemplate.update(sql, saleId);
    }

    private Sale mapRowToSale(SqlRowSet rs) {
        Sale sale = new Sale();
        sale.setSaleId(rs.getInt("sale_id"));
        sale.setCustomerId(rs.getInt("customer_id"));
        sale.setSaleDate(rs.getDate("sale_date").toLocalDate());
        sale.setShipDate(rs.getDate("ship_date") != null ? rs.getDate("ship_date").toLocalDate() : null);
        sale.setCustomerName(rs.getString("customer_name"));
        return sale;
    }
}
