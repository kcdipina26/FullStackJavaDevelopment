package com.techelevator.auctions.services;

import com.techelevator.auctions.model.Auction;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class AuctionService {

    public static final String API_BASE_URL = "http://localhost:8080/auctions/";
    private RestTemplate restTemplate = new RestTemplate();

    private String authToken = null;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Auction[] getAllAuctions() {
        Auction[] auctions = null;
        try {
            ResponseEntity<Auction[]> response =
                    restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(), Auction[].class);
            auctions = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return auctions;
    }

    public Auction getAuction(int id) {
        Auction auction = null;
        try {
            // Add code here to send the request to the API and get the auction from the response.
                // Construct the URL for the specific auction ID
                String url = API_BASE_URL + id;
                // Create a new HttpEntity with the headers needed for authentication
                HttpEntity<Void> entity = makeAuthEntity();
                // Send the GET request to retrieve the auction, and capture the response
                ResponseEntity<Auction> response = restTemplate.exchange(url, HttpMethod.GET, entity, Auction.class);
                // Extract the auction from the response body
                auction = response.getBody();
            } catch (RestClientResponseException e) {
                // If there's an HTTP error, log the response error status and message
                BasicLogger.log("Error getting auction: " + e.getRawStatusCode() + " : " + e.getStatusText());
            } catch (ResourceAccessException e) {
                // If there's a problem with the resource access, log the exception message
                BasicLogger.log("Error getting auction: " + e.getMessage());
            }

            return auction;

        }



    public Auction[] getAuctionsMatchingTitle(String title) {
        Auction[] auctions = null;
        try {
            ResponseEntity<Auction[]> response =
                    restTemplate.exchange(API_BASE_URL + "?title_like=" + title, HttpMethod.GET,
                            makeAuthEntity(), Auction[].class);
            auctions = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return auctions;
    }

    public Auction[] getAuctionsAtOrBelowPrice(double price) {
        Auction[] auctions = null;
        try {
            ResponseEntity<Auction[]> response =
                    restTemplate.exchange(API_BASE_URL + "?currentBid_lte=" + price, HttpMethod.GET,
                            makeAuthEntity(), Auction[].class);
            auctions = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return auctions;
    }

    public Auction add(Auction newAuction) {
        HttpEntity<Auction> entity = makeAuctionEntity(newAuction);
        Auction returnedAuction = null;
        try {
            returnedAuction = restTemplate.postForObject(API_BASE_URL, entity, Auction.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return returnedAuction;
    }

    public boolean update(Auction updatedAuction) {
        HttpEntity<Auction> entity = makeAuctionEntity(updatedAuction);
        boolean success = false;
        try {
            restTemplate.put(API_BASE_URL + updatedAuction.getId(), entity);
            success = true;
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }

    public boolean delete(int auctionId) {
        boolean success = false;
        try {
            restTemplate.exchange(API_BASE_URL + auctionId, HttpMethod.DELETE, makeAuthEntity(), Void.class);
            success = true;
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }

    private HttpEntity<Auction> makeAuctionEntity(Auction auction) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(auction, headers);
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

}
