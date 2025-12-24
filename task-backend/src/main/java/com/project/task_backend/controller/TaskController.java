package com.project.task_backend.controller;

import com.project.task_backend.domain.dto.TaskDto;
import com.project.task_backend.domain.entity.Task;
import com.project.task_backend.mapper.TaskMapper;
import com.project.task_backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="api/task-lists/{task_list_id}/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID task_list_id) {
        return taskService.listTasks(task_list_id)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @GetMapping("/{task_id}")
    public Optional<TaskDto> getTask(@PathVariable("task_list_id") UUID task_list_id,
                                     @PathVariable("task_id") UUID task_id) {
        return taskService.getTaskBy(task_list_id, task_id)
                .map(taskMapper::toDto);
    }

    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto taskDto,
                              @PathVariable("task_list_id") UUID task_list_id) {
        Task createdTask = taskService.createTask(
                task_list_id,
                taskMapper.fromDto(taskDto)
        );
        return taskMapper.toDto(createdTask);
    }

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

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_list_id") UUID taskListId,
                           @PathVariable("task_id") UUID taskId) {
        taskService.deleteTask(taskListId, taskId);
    }
}
