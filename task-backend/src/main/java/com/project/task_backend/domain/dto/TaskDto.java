package com.project.task_backend.domain.dto;

import com.project.task_backend.domain.entity.TaskPriority;
import com.project.task_backend.domain.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
   UUID id,
   String title,
   String description,
   LocalDateTime dueDate,
   TaskPriority priority,
   TaskStatus status
) {}
