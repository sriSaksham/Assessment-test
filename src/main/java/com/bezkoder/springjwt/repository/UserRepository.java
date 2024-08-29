package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  void deleteByUsername(String username);

  Optional<User> findByEmail(String email);

  Optional<User> findById(Integer id);
  Optional<User> findById(Long id);
  List<User> findAllById(Iterable<Long> ids);

}
