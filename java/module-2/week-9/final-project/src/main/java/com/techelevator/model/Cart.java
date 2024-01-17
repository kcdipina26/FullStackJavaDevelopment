package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private int userId;
    private List<CartItem> items;
    private BigDecimal subtotal;
    private BigDecimal taxAmount;

    public Cart(int userId, List<CartItem> items, BigDecimal subtotal, BigDecimal taxAmount) {
        this.userId = userId;
        this.items = items;
        this.subtotal = subtotal;
        this.taxAmount = taxAmount;
    }

    public Cart(int userId, List<CartItem> cartItems) {
        this.userId = userId;
        this.items = cartItems; // Initialize items with cartItems
        this.subtotal = BigDecimal.ZERO; // Initialize subtotal to zero
        this.taxAmount = BigDecimal.ZERO; // Initialize taxAmount to zero

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }
}
