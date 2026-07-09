package com.school.haja.repository.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "course")
public class JCourse {
  @Id @GeneratedValue private UUID id;

  @Column(nullable = false, unique = true)
  private String title;

  private Instant start;
  private Instant end;

  @OneToMany(mappedBy = "course")
  private List<JSubscription> subscriptions;
}
