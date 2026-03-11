package com.sthi.re.service;

import org.springframework.stereotype.Service;

@Service
public class ConsoleSmsService implements SmsService {

    @Override
    public void sendSms(String phoneNumber, String message) {
        // POC ONLY – simulate SMS
        System.out.println("📩 SMS SENT");
        System.out.println("To      : " + phoneNumber);
        System.out.println("Message : " + message);
        System.out.println("------------------------------");
    }
}
