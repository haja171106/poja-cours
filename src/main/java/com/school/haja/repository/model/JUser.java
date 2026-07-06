package com.school.haja.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
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
