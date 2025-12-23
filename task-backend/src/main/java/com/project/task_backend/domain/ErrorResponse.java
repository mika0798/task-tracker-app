package com.project.task_backend.domain;

public record ErrorResponse(
        int status,
        String message,
        String details
) {}