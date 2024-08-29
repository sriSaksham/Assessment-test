package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.payload.response.UserActivityStatusResponse;
import com.bezkoder.springjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

public class ActivityStatus {

    @Autowired
    UserService userService;

    @GetMapping("/activity-status")
    public UserActivityStatusResponse getUserActivityStatus() {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Retrieve the username from the authentication

        // Fetch user ID from the username
        UserActivityStatusResponse response = userService.getUserActivityStatusByUsername(username);
        return response;
    }
}
