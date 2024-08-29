package com.bezkoder.springjwt.repository;



import com.bezkoder.springjwt.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Set<Question> findBySubTopic_SubTopicId(Long subTopicId);
}
