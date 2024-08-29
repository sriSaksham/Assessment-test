package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Email;
import com.bezkoder.springjwt.service.EmailServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailServe emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody Email emailRequest) {
        try {
            emailService.sendEmail(emailRequest);
            return ResponseEntity.ok("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }
}
