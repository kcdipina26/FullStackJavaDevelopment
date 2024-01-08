package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.LineItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

public class JdbcLineItemDaoTest extends BaseDaoTests {

  private JdbcLineItemDao sut;

  @Before
  public void setUp() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    sut = new JdbcLineItemDao(dataSource);
  }



  @Test
  public void getLineItemsBySaleId_returns_correct_line_items() {
    List<LineItem> lineItems = sut.getLineItemsBySaleId(1);

    Assert.assertEquals("getLineItemsBySaleId should return the correct number of line items", 3, lineItems.size());
    assertLineItemMatch(new LineItem(1, 1, 1, 1, "Product 1", new BigDecimal("9.99")), lineItems.get(0));
    assertLineItemMatch(new LineItem(2, 1, 2, 1, "Product 2", new BigDecimal("19.00")), lineItems.get(1));
    assertLineItemMatch(new LineItem(3, 1, 4, 1, "Product 4", new BigDecimal("0.99")), lineItems.get(2));
  }

  private void assertLineItemMatch(LineItem expected, LineItem actual) {
    Assert.assertEquals("Line item ID should match", expected.getLineItemId(), actual.getLineItemId());
    Assert.assertEquals("Sale ID should match", expected.getSaleId(), actual.getSaleId());
    Assert.assertEquals("Product ID should match", expected.getProductId(), actual.getProductId());
    Assert.assertEquals("Quantity should match", expected.getQuantity(), actual.getQuantity());
    Assert.assertEquals("Product Name should match", expected.getProductName(), actual.getProductName());
    Assert.assertEquals("Price should match", expected.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP), actual.getPrice());
  }
}
