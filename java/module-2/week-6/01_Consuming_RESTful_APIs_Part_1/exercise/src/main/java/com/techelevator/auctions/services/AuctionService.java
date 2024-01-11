package com.techelevator.auctions.services;

import org.springframework.web.client.RestTemplate;

import com.techelevator.auctions.model.Auction;

public class AuctionService {

    public static String API_BASE_URL = "http://localhost:3000/auctions";
    private RestTemplate restTemplate = new RestTemplate();


    public Auction[] getAllAuctions() {
        // call api here
        return restTemplate.getForObject(API_BASE_URL, Auction[].class);
    }

    public Auction getAuction(int id) {
        // call api here
       String url = API_BASE_URL + "/" + id;
        return restTemplate.getForObject(url, Auction.class);
    }

    public Auction[] getAuctionsMatchingTitle(String title) {
        // call api here
        String url = API_BASE_URL + "?title_like=" + title;
        return restTemplate.getForObject(url, Auction[].class);
    }

    public Auction[] getAuctionsAtOrBelowPrice(double price) {
        // call api here
        String url = API_BASE_URL + "?currentBid_lte=" + price;
        return restTemplate.getForObject(url, Auction[].class);
    }

}
