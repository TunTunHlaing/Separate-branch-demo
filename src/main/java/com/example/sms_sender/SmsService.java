package com.example.sms_sender;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


@Service
public class SmsService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${sms.poh.token}")
    String token;

    public String sendSms(SmsMessage smsMessage) throws URISyntaxException {

        String url = "https://smspoh.com/api/v2/send";
        URI uri = new URI(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<SmsMessage> requestEntity = new HttpEntity<>(smsMessage, headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

            String responseBody = responseEntity.getBody();

            System.out.println("Response Body: " + responseBody);
            return responseBody;

        } catch (Exception e) {
            System.err.println("Error message: " + e.getMessage());
            return "Error occur!!!";
        }
    }

    public String getBalance() throws URISyntaxException {
        String url = "https://smspoh.com/api/v2/get-balance";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class );
            return responseEntity.getBody();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "Error Occour!!!";
        }
    }

}


