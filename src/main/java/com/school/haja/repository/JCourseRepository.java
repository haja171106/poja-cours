package com.school.haja.repository;

import com.school.haja.repository.model.JCourse;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JCourseRepository extends JpaRepository<JCourse, UUID> {}
