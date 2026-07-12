package com.school.haja.service;

import com.school.haja.endpoint.rest.controller.dto.CreateUserRequest;
import com.school.haja.repository.JUserRepository;
import com.school.haja.repository.model.JUser;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final JUserRepository userRepository;

  public JUser createUser(CreateUserRequest request) {
    var user =
        JUser.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .userName(request.userName())
            .email(request.email())
            .build();
    return userRepository.save(user);
  }

  public JUser getUser(UUID id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
  }
}
