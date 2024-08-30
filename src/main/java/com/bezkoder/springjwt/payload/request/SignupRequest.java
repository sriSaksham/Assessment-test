package com.bezkoder.springjwt.payload.request;

import java.util.Set;

import jakarta.validation.constraints.*;

public class SignupRequest {
  @NotBlank(message = "Username is mandatory")
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank(message = "Email is mandatory")
  @Size(max = 50)
  @Email(message = "Email should be valid")
  private String email;

  private Set<String> role;

  @NotBlank(message = "Password is mandatory")
  @Size(min = 8, max = 40)
  @Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=]).*$",
          message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character")
  private String password;

  @NotBlank
  @Size(min = 12, max =12)
  private String aadharPan;

  @NotBlank(message = "First name is mandatory")
  @Size(max = 50)
  private String firstName;

  @NotBlank(message = "Last name is mandatory")
  @Size(max = 50)
  private String lastName;

  @NotBlank(message = "Phone number is mandatory")
  @Size(min = 10, max = 10)
  private String phoneNumber;

  public  String getAadharPan() {
    return aadharPan;
  }
  public void setAadharPan(String aadharPan) {
    this.aadharPan = aadharPan;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}
}

