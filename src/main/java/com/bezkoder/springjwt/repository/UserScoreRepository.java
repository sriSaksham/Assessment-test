package com.bezkoder.springjwt.repository;


import com.bezkoder.springjwt.models.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Integer> {
    UserScore findByUserId(int userId);
}
