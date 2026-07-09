package com.school.haja.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
  private UUID id;
  private String firstName;
  private String lastName;
  private String userName;
  private String email;
}
