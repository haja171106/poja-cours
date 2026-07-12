package com.school.haja.endpoint.rest.controller;

import com.school.haja.endpoint.rest.controller.dto.CreateSubscriptionRequest;
import com.school.haja.repository.model.JSubscription;
import com.school.haja.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
@AllArgsConstructor
public class SubscriptionController {

  private final SubscriptionService subscriptionService;

  @PostMapping
  public ResponseEntity<JSubscription> subscribe(@RequestBody CreateSubscriptionRequest request) {
    var created = subscriptionService.createSubscription(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }
}
