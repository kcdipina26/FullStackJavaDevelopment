package com.techelevator.auctions.services;

import com.techelevator.auctions.model.CredentialsDto;
import com.techelevator.auctions.model.TokenDto;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


public class AuthenticationService {

    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();

    public String login(String username, String password) {
        CredentialsDto credentialsDto = new CredentialsDto();
        credentialsDto.setUsername(username);
        credentialsDto.setPassword(password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CredentialsDto> entity = new HttpEntity<>(credentialsDto, headers);
        String token = null;
        try {
            // Add code here to send the request to the API and get the token from the response.
            //Send the Post request to the /login endpoint and except a TokenDto in response.
            ResponseEntity<TokenDto> response = restTemplate.postForEntity(API_BASE_URL + "login", entity, TokenDto.class);

            // Set the token variable to the token received in the response body.
            token = response.getBody().getToken();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return token;
    }

}
