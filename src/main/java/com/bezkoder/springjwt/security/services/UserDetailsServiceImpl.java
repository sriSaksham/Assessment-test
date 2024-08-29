package com.bezkoder.springjwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    User user;

    if (usernameOrEmail.contains("@")) {
      // Find by email
      user = userRepository.findByEmail(usernameOrEmail)
              .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + usernameOrEmail));
    } else {
      // Find by username
      user = userRepository.findByUsername(usernameOrEmail)
              .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + usernameOrEmail));
    }

    return UserDetailsImpl.build(user);
  }
}
