package com.school.haja.model;

import java.time.Instant;
import java.util.UUID;

public class Subscription {
    private UUID id;
    private SubscriptionStatus status;
    private Instant createdAt;
    private UUID courseId;
    private UUID userId;

}
