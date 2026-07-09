package com.school.haja.model;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Course {
  private UUID id;
  private String title;
  private Instant startDate;
  private Instant endDate;
}
