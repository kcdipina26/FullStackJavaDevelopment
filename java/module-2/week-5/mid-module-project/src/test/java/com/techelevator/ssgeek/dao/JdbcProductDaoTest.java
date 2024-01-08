package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;

public class JdbcProductDaoTest extends BaseDaoTests {

  private JdbcProductDao dao;


  private static final Product PRODUCT_1 = new Product(1, "Product 1", "Description 1", new BigDecimal("9.99"), "product-1.png");
  private static final Product PRODUCT_2 = new Product(2, "Product 2", "Description 2", new BigDecimal("19.00"), "product-2.png");
  private static final Product PRODUCT_3 = new Product(3, "Product 3", "Description 3", new BigDecimal("123.45"), "product-3.png");
  private static final Product PRODUCT_4 = new Product(4, "Product 4", "Description 4", new BigDecimal("0.99"), "product-4.png");

  @Before
  public void setup() {
    dao = new JdbcProductDao(dataSource);
  }

  @Test
  public void getProductById_returns_correct_product() {
    Product product = dao.getProductById(PRODUCT_1.getProductId());
    assertProductMatch(PRODUCT_1, product);

    product = dao.getProductById(PRODUCT_2.getProductId());
    assertProductMatch(PRODUCT_2, product);
  }

  @Test
  public void getProducts_returns_all_products() {
    List<Product> products = dao.getProducts();
    Assert.assertEquals("getProducts should return the correct number of products", 4, products.size());
    assertProductMatch(PRODUCT_1, products.get(0));
    assertProductMatch(PRODUCT_2, products.get(1));
    assertProductMatch(PRODUCT_3, products.get(2));
    assertProductMatch(PRODUCT_4, products.get(3));
  }

  @Test
  public void createProduct_returns_new_product_with_id() {
    Product newProduct = new Product(0, "New Product", "New Description", new BigDecimal("250.00"), "new-product.png");
    Product createdProduct = dao.createProduct(newProduct);

    Assert.assertTrue(createdProduct.getProductId() > 0);

    Product retrievedProduct = dao.getProductById(createdProduct.getProductId());
    assertProductMatch(createdProduct, retrievedProduct);
  }

  @Test
  public void updateProduct_has_expected_values() {
    Product productToUpdate = dao.getProductById(PRODUCT_1.getProductId());
    productToUpdate.setName("Updated Product");
    productToUpdate.setDescription("Updated Description");
    productToUpdate.setPrice(new BigDecimal("299.99"));
    productToUpdate.setImageName("updated-product.png");

    dao.updateProduct(productToUpdate);

    Product updatedProduct = dao.getProductById(productToUpdate.getProductId());
    assertProductMatch(productToUpdate, updatedProduct);
  }

  private void assertProductMatch(Product expected, Product actual) {
    Assert.assertEquals(expected.getProductId(), actual.getProductId());
    Assert.assertEquals(expected.getName(), actual.getName());
    Assert.assertEquals(expected.getDescription(), actual.getDescription());
    Assert.assertEquals(0, expected.getPrice().compareTo(actual.getPrice()));
    Assert.assertEquals(expected.getImageName(), actual.getImageName());
  }
}
