package com.techelevator.controller;

import com.techelevator.dao.ProductDao;
import com.techelevator.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public List<Product> list(@RequestParam(defaultValue = "") String sku,
                              @RequestParam(defaultValue = "") String name) {
        if (sku.isEmpty() && name.isEmpty()) {
            return productDao.getProducts();
        }
        return productDao.getProductBySkuAndName(sku, name);
    }

    @GetMapping("/products/{productId}")
    public Product get(@PathVariable int productId) {
        return productDao.getProductById(productId);
    }
}
