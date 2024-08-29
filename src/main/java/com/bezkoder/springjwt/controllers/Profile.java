package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.UserProfileDto;
import com.bezkoder.springjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class Profile {

    @Autowired
    private UserService userService;
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

    @GetMapping("/api/auth/profile")
    public ResponseEntity<UserProfileDto> getUserProfile(Authentication authentication) {
        String username = authentication.getName(); // Get the logged-in user's username
        Optional<UserProfileDto> userProfileDTO = userService.getUserProfileByUsername(username);

        return userProfileDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}