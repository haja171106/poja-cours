package com.school.haja.endpoint.rest.controller.dto;

import java.util.UUID;
import lombok.Builder;

@Builder
public record CreateSubscriptionRequest(UUID userId, UUID courseId) {}
