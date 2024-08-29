package com.bezkoder.springjwt.models;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class UserScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long userId;
	private int score;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "score timestamp")
    private Date createdAt;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	 public LocalDateTime getLocalDateTimeCreatedAt() {
	        return LocalDateTime.ofInstant(createdAt.toInstant(), ZoneId.systemDefault());
	    }

	    public void setLocalDateTimeCreatedAt(LocalDateTime localDateTime) {
	        this.createdAt = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	    }

	
	public UserScore(int id, long userId, int score, Date createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.score = score;
		this.createdAt = createdAt;
	}

	public UserScore() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", score=" + score + ", createdAt=" + createdAt + "]";
	}

}
