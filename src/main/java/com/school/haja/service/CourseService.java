package com.school.haja.service;

import com.school.haja.endpoint.rest.controller.dto.CreateCourseRequest;
import com.school.haja.repository.JCourseRepository;
import com.school.haja.repository.model.JCourse;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {

  private final JCourseRepository courseRepository;

  public JCourse createCourse(CreateCourseRequest request) {
    var course =
        JCourse.builder().title(request.title()).start(request.start()).end(request.end()).build();
    return courseRepository.save(course);
  }

  public JCourse getCourse(UUID id) {
    return courseRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Course not found: " + id));
  }
}
