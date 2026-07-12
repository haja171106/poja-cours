package com.school.haja.endpoint.rest.controller.dto;

import lombok.Builder;

@Builder
public record CreateUserRequest(String firstName, String lastName, String userName, String email) {}
