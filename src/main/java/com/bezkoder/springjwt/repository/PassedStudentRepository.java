package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.PassedStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassedStudentRepository extends JpaRepository<PassedStudent, Long> {
}
