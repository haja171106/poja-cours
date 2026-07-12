package com.school.haja.endpoint.rest.controller;

import com.school.haja.endpoint.rest.controller.dto.CreateUserRequest;
import com.school.haja.repository.model.JUser;
import com.school.haja.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<JUser> createUser(@RequestBody CreateUserRequest request) {
    var created = userService.createUser(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }
}
