package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.bezkoder.springjwt.models.*;
import com.bezkoder.springjwt.payload.request.*;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.EmailService;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import com.bezkoder.springjwt.security.services.ForgotPasswordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;
//new
  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  EmailService emailService;

  @Autowired
  ForgotPasswordService forgotPasswordService;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    try{

    // Authenticate the user using either username or email
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

    // Set the authentication in the security context
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Generate JWT token
    String jwt = jwtUtils.generateJwtToken(authentication);

    // Get user details
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    // Return the JWT and user details
    return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
    } catch (BadCredentialsException e) {
      // Handle invalid username/password
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
    } catch (DisabledException e) {
      // Handle disabled accounts
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User account is disabled.");
    } catch (Exception e) {
      // Handle any other errors
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the login request.");
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(
          @RequestParam("userData") String userData,
          @RequestParam("file") MultipartFile file) throws JsonProcessingException {

    // Parse userData from JSON to Java object
    ObjectMapper objectMapper = new ObjectMapper();
    SignupRequest signUpRequest = objectMapper.readValue(userData, SignupRequest.class);

    // Check for existing username and email
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    user.setFirstName(signUpRequest.getFirstName());
    user.setLastName(signUpRequest.getLastName());
    user.setPhoneNumber(signUpRequest.getPhoneNumber());
    user.setAadharPan(signUpRequest.getAadharPan());

    user.setPlanStatus("INACTIVE");

    // Handle file upload
    if (!file.isEmpty()) {
      String fileName = saveFile(file); // Implement this method to save the file and return the path
      user.setFilePath(fileName);
    }

    // Assign default role
    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(userRole);
    user.setRoles(roles);

    userRepository.save(user);

    // Send confirmation email
    String subject = "Registration Successful";
    String body = "Dear " + user.getFirstName() + " " + user.getLastName() + ",\n\n" +
            "Your registration was successful.\n\n" +
            "Best regards,\nEduTech Team";
    emailService.sendEmail(user.getEmail(), subject, body);
    user.setPlanStatus("INACTIVE");
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  private String saveFile(MultipartFile file) {
    // Ensure the file is of the correct type
    String contentType = file.getContentType();
    if (!Arrays.asList("application/pdf", "image/png", "image/jpeg").contains(contentType)) {
      throw new RuntimeException("Invalid file type. Only PDF, PNG, and JPEG are allowed.");
    }
    try {
      String uploadDir = "uploads/";  // Customize this directory path
      Path filePath = Paths.get(uploadDir + file.getOriginalFilename());
      Files.createDirectories(filePath.getParent());
      Files.write(filePath, file.getBytes());
      return filePath.toString();
    } catch (IOException e) {
      throw new RuntimeException("Error saving file: " + e.getMessage());
    }
  }


  @GetMapping("/users")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userRepository.findAll();
    return ResponseEntity.ok(users);
  }

  @PutMapping("/update-password")
  public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest) {
    Optional<User> userOptional = userRepository.findByEmail(updatePasswordRequest.getUsername());
    if (userOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: User not found."));
    }
    User user = userOptional.get();
    user.setPassword(encoder.encode(updatePasswordRequest.getNewPassword()));
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Password updated successfully!"));
  }

//  @DeleteMapping("/delete-user/{username}")
//  @PreAuthorize("hasRole('ADMIN')")
//  public ResponseEntity<?> deleteUser(@PathVariable String username) {
//    if (!userRepository.existsByUsername(username)) {
//      return ResponseEntity
//              .badRequest()
//              .body(new MessageResponse("Error: Username not found!"));
//    }
//
//    userRepository.deleteByUsername(username);
//    return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
//  }


  @PostMapping("/forgot-password")
  public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
    forgotPasswordService.forgotPassword(request.getEmail());
    return ResponseEntity.ok("OTP sent to your email.");
  }

  @PostMapping("/reset-password")
  public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
    forgotPasswordService.resetPassword(request.getEmail(), request.getOtp(), request.getNewPassword());
    return ResponseEntity.ok("Password reset successful.");
  }
}
