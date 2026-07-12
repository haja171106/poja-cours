package com.school.haja.service;

import com.school.haja.endpoint.event.EventProducer;
import com.school.haja.endpoint.event.model.SubscriptionConfirmationRequested;
import com.school.haja.endpoint.rest.controller.dto.CreateSubscriptionRequest;
import com.school.haja.model.SubscriptionStatus;
import com.school.haja.repository.JCourseRepository;
import com.school.haja.repository.JSubscriptionRepository;
import com.school.haja.repository.JUserRepository;
import com.school.haja.repository.model.JCourse;
import com.school.haja.repository.model.JSubscription;
import com.school.haja.repository.model.JUser;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscriptionService {

  private final JSubscriptionRepository subscriptionRepository;
  private final JUserRepository userRepository;
  private final JCourseRepository courseRepository;
  private final EventProducer<SubscriptionConfirmationRequested> eventProducer;

  public JSubscription createSubscription(CreateSubscriptionRequest request) {
    JUser user =
        userRepository
            .findById(request.userId())
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + request.userId()));
    JCourse course =
        courseRepository
            .findById(request.courseId())
            .orElseThrow(
                () -> new IllegalArgumentException("Course not found: " + request.courseId()));

    var subscription =
        JSubscription.builder().user(user).course(course).status(SubscriptionStatus.DONE).build();

    var saved = subscriptionRepository.save(subscription);

    var event =
        SubscriptionConfirmationRequested.builder()
            .subscriptionId(saved.getId())
            .to(user.getEmail())
            .userFirstName(user.getFirstName())
            .userLastName(user.getLastName())
            .userName(user.getUserName())
            .userEmail(user.getEmail())
            .courseTitle(course.getTitle())
            .courseStart(course.getStart())
            .courseEnd(course.getEnd())
            .build();
    eventProducer.accept(List.of(event));

    return saved;
  }
}
