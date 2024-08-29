package com.bezkoder.springjwt.models;

import jakarta.persistence.*;


import java.util.Date;

@Entity
public class PassedStudent {

    @Id
    private long userId;

    private int score; // Store the score here directly
    private String email;
    private String username;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Constructors
    public PassedStudent() {}

    public PassedStudent(long userId, int score, String email, String username, Date createdAt) {
        this.userId = userId;
        this.score = score;  // Set the score directly from the User entity
        this.email = email;
        this.username = username;
        this.createdAt = createdAt;
    }


    public long getUserId() {
        return userId;
    }

    public void setUser(long userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PassedStudent [user=" + userId + ", score=" + score +
                ", email=" + email + ", username=" + username + ", createdAt=" + createdAt + "]";
    }
}