package com.school.haja.endpoint.rest.controller.dto;

import java.time.Instant;
import lombok.Builder;

@Builder
public record CreateCourseRequest(String title, Instant start, Instant end) {}
