package com.example.sms_sender;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class SmsController {

    @Autowired
    private SmsService service;

    @PostMapping
    public String sendSms(@RequestBody SmsMessage message) throws URISyntaxException {
        return service.sendSms(message);
    }

    @GetMapping("get")
    public String getBalance() throws URISyntaxException {
        return service.getBalance();
    }
}
