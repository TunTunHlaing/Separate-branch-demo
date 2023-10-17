package com.example.sms_sender;

public record SmsMessage(
        String to,
        String message,
        String sender
)
{}
