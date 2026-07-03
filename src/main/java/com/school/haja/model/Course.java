package com.school.haja.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Course {
    private UUID id;
    private String title;
    private Instant startDate;
    private Instant endDate;
}
