package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.MainTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainTopicRepository extends JpaRepository<MainTopic, Long> {
}
