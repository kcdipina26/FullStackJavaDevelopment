package com.techelevator.dao;

import com.techelevator.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> allProducts = new ArrayList<>();
        String sql = "SELECT * FROM product";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            allProducts.add(mapRowToProduct(results));
        }
        return allProducts;
    }

    @Override
    public List<Product> getProductBySkuAndName(String sku, String name) {
        List<Product> matchedProducts = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_sku LIKE ? AND name LIKE ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + sku + "%", "%" + name + "%");
        while (results.next()) {
            matchedProducts.add(mapRowToProduct(results));
        }
        return matchedProducts;
    }

    @Override
    public Product getProductById(int productId) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE product_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
        if (results.next()) {
            product = mapRowToProduct(results);
        }
        return product;
    }
    private Product mapRowToProduct(SqlRowSet results) {
        Product product = new Product();
        product.setProductId(results.getInt("product_id"));
        product.setSku(results.getString("product_sku"));
        product.setName(results.getString("name"));
        product.setDescription(results.getString("description"));
        product.setPrice(results.getBigDecimal("price"));
        product.setImageName(results.getString("image_name"));
        return product;

    }
}
