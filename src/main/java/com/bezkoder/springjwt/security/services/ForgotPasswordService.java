package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;


@Service
public class ForgotPasswordService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ForgotPasswordService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public void forgotPassword(String email) {
        boolean userExists = userRepository.existsByEmail(email);

        if (!userExists) {
            throw new RuntimeException("User not found with email: " + email);
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Generate a 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);

        // Send OTP via email
        emailService.sendEmail(user.getEmail(), "Your OTP Code", "Your OTP code is: " + otp);
    }

    public void resetPassword(String email, String otp, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Verify OTP
        if (!otp.equals(user.getOtp())) {
            throw new RuntimeException("Invalid OTP.");
        }

        // Check if OTP is expired (e.g., 10 minutes validity)
        Duration duration = Duration.between(user.getOtpGeneratedTime(), LocalDateTime.now());
        if (duration.toMinutes() > 10) {
            throw new RuntimeException("OTP expired.");
        }

        // Reset password
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setOtp(null);  // Clear the OTP
        user.setOtpGeneratedTime(null);
        userRepository.save(user);
    }
}
