package com.bezkoder.springjwt.service;


import com.bezkoder.springjwt.models.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;




@Service
public class EmailServe {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(Email emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getText());
		/* message.setFrom("test576418@gmail.com"); */
        javaMailSender.send(message);
    }
}
