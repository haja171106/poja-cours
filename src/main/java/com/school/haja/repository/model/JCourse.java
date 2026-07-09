package com.school.haja.repository.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JCourse {

  @Id @GeneratedValue private UUID id;

  @Column(nullable = false, unique = true)
  private String title;

  @Column(name = "start_date")
  private Instant start;

  @Column(name = "end_date")
  private Instant end;

  @OneToMany(mappedBy = "course")
  private List<JSubscription> subscriptions;
}
