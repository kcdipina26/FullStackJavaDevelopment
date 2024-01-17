package com.techelevator.dao;

import com.techelevator.model.Product;
import java.util.List;

public interface ProductDao {
    List<Product> getProducts();

    List<Product> getProductBySkuAndName(String sku, String name);

    Product getProductById(int productId);
}
