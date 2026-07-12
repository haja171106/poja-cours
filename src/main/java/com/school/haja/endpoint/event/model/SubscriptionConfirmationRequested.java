package com.school.haja.endpoint.event.model;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class SubscriptionConfirmationRequested extends PojaEvent {
  private UUID subscriptionId;
  private String to;

  private String userFirstName;
  private String userLastName;
  private String userName;
  private String userEmail;

  private String courseTitle;
  private Instant courseStart;
  private Instant courseEnd;

  @Override
  public Duration maxConsumerDuration() {
    return Duration.ofSeconds(45);
  }

  @Override
  public Duration maxConsumerBackoffBetweenRetries() {
    return Duration.ofSeconds(30);
  }
}
