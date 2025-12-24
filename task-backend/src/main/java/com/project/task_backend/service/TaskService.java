package com.project.task_backend.service;

import com.project.task_backend.domain.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID id);
    Optional<Task> getTaskBy(UUID taskListId, UUID taskId);
    Task createTask(UUID taskListId, Task task);
    Task updateTask(UUID taskListId, UUID taskId, Task task);
    void deleteTask(UUID taskListId, UUID taskId);
}
