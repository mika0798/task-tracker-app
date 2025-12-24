package com.project.task_backend.service.impl;

import com.project.task_backend.domain.entity.Task;
import com.project.task_backend.domain.entity.TaskList;
import com.project.task_backend.domain.entity.TaskPriority;
import com.project.task_backend.domain.entity.TaskStatus;
import com.project.task_backend.repository.TaskListRepository;
import com.project.task_backend.repository.TaskRepository;
import com.project.task_backend.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    @Override
    public List<Task> listTasks(UUID id) {
        return taskRepository.findByTaskListId(id);
    }

    @Override
    public Optional<Task> getTaskBy(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Task ID is already set");
        }
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task must have a title");
        }
        TaskPriority priority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskList taskList = taskListRepository
                .findById(taskListId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Could not find TaskList with id " + taskListId));

        LocalDateTime now = LocalDateTime.now();
        return taskRepository.save(new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                priority,
                TaskStatus.OPEN,
                now,
                now,
                taskList
        ));
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("Task must have an id");
        }
        if (!Objects.equals(taskId, task.getId())) {
            throw new IllegalArgumentException("Task IDs do not match");
        }
        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Task must have a valid priority!");
        }
        if(task.getStatus() == null) {
            throw new IllegalArgumentException("Task must have a valid status!");
        }

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId,taskId)
                .orElseThrow(() -> new IllegalStateException("Task not found!"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }
}
