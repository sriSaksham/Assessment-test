package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.TopicTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicTableRepository extends JpaRepository<TopicTable, Long> {
}
