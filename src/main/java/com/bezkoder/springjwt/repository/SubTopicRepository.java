package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.SubTopic;
import com.bezkoder.springjwt.models.TopicTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubTopicRepository  extends JpaRepository<SubTopic, Long> {
    Set<SubTopic> findByTopicTable_Tid(Long tid);

}
