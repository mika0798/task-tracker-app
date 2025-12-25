package com.project.task_backend.controller;

import com.project.task_backend.domain.dto.TaskDto;
import com.project.task_backend.domain.entity.Task;
import com.project.task_backend.mapper.TaskMapper;
import com.project.task_backend.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="api/task-lists/{task_list_id}/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "APIs for managing tasks inside a task list")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Operation(
            summary = "List tasks in a task list",
            description = "Retrieve all tasks belonging to a specific task list")
    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID task_list_id) {
        return taskService.listTasks(task_list_id)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Operation(
            summary = "Get a task by ID",
            description = "Retrieve a single task inside a task list")
    @GetMapping("/{task_id}")
    public Optional<TaskDto> getTask(@PathVariable("task_list_id") UUID task_list_id,
                                     @PathVariable("task_id") UUID task_id) {
        return taskService.getTaskBy(task_list_id, task_id)
                .map(taskMapper::toDto);
    }

    @Operation(
            summary = "Create a new task",
            description = "Create a new task inside a specific task list")
    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto taskDto,
                              @PathVariable("task_list_id") UUID task_list_id) {
        Task createdTask = taskService.createTask(
                task_list_id,
                taskMapper.fromDto(taskDto)
        );
        return taskMapper.toDto(createdTask);
    }

    @Operation(
            summary = "Update a task",
            description = "Update an existing task inside a task list")
    @PutMapping("/{task_id}")
    public TaskDto updateTask(@PathVariable("task_list_id") UUID taskListId,
                              @PathVariable("task_id") UUID taskId,
                              @RequestBody TaskDto taskDto) {
        Task updatedTask = taskService.updateTask(
                taskListId,
                taskId,
                taskMapper.fromDto(taskDto)
        );
        return taskMapper.toDto(updatedTask);
    }

    @Operation(
            summary = "Delete a task",
            description = "Delete a task from a task list")
    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_list_id") UUID taskListId,
                           @PathVariable("task_id") UUID taskId) {
        taskService.deleteTask(taskListId, taskId);
    }
}
