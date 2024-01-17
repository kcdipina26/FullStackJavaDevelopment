package com.techelevator.services;

import java.math.BigDecimal;

public class TaxRate {

    private BigDecimal salesTax;

    public BigDecimal getSalesTax() {
        return salesTax;

    }
    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;

    }
    public TaxRate(){

    }
}
