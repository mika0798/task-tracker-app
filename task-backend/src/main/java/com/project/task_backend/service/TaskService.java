package com.project.task_backend.service;

import com.project.task_backend.domain.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID id);
}
