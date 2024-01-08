package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.LineItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcLineItemDao implements LineItemDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcLineItemDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<LineItem> getLineItemsBySaleId(int saleId) {
        List<LineItem> lineItems = new ArrayList<>();
        String sql = "SELECT li.line_item_id, li.sale_id, li.product_id, li.quantity, p.name AS product_name, p.price " +
                "FROM line_item li " +
                "JOIN product p ON li.product_id = p.product_id " +
                "WHERE li.sale_id = ? " +
                "ORDER BY li.line_item_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, saleId);
        while (results.next()) {
            lineItems.add(mapRowToLineItem(results));
        }
        return lineItems;
    }

    private LineItem mapRowToLineItem(SqlRowSet rs) {
        LineItem lineItem = new LineItem();
        lineItem.setLineItemId(rs.getInt("line_item_id"));
        lineItem.setSaleId(rs.getInt("sale_id"));
        lineItem.setProductId(rs.getInt("product_id"));
        lineItem.setQuantity(rs.getInt("quantity"));
        lineItem.setProductName(rs.getString("product_name"));
        lineItem.setPrice(rs.getBigDecimal("price").setScale(2)); // Ensure the scale is set for currency values
        return lineItem;
    }
}

