package com.example.bloodBank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public int sendVerificationEmail(String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail);
        message.setSubject("Email verification OTP for Blood Bank Management System");
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        System.out.println("Generated OTP: " + otp);
        message.setText("OTP: "+otp);
        mailSender.send(message);
        return  otp;
    }

    public void sendMailWithMessage(String email,String subject, String msg){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}
