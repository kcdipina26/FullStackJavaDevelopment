package com.techelevator.services;

import com.techelevator.dao.JdbcUserDao;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class TaxService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final JdbcUserDao jdbcUserDao;
    private final String taxApiUrl = "https://teapi.netlify.app/api/statetax?state=";

    public TaxService(JdbcUserDao jdbcUserDao) {
        this.jdbcUserDao = jdbcUserDao;
    }

    public BigDecimal getTaxForState(int userId) {
        String userState = getUserState(userId);
        if (userState != null) {
            String url = taxApiUrl + userState;
            TaxRate taxRate = restTemplate.getForObject(url, TaxRate.class);
            return taxRate != null ? taxRate.getSalesTax().divide(new BigDecimal("100")) : BigDecimal.ZERO; // Handling null and dividing by 100
        }
        return BigDecimal.ZERO;
    }

    private String getUserState(int userId) {
        return jdbcUserDao.getStateCodeByUserId(userId);
    }

}

