package com.project.task_backend.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {}