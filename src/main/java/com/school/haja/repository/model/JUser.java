package com.school.haja.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "\"user\"")
@NoArgsConstructor
public class JUser {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(length = 200)
    private String firstName;
    @Column(nullable = false, length = 200)
    private String lastName;
    @Column(nullable = false, length = 60, unique = true)
    private String userName;
    @Column(nullable = false, unique = true)
    private String email;
}
