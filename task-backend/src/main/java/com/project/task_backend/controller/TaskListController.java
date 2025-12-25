package com.project.task_backend.controller;

import com.project.task_backend.domain.dto.TaskListDto;
import com.project.task_backend.domain.entity.TaskList;
import com.project.task_backend.mapper.TaskListMapper;
import com.project.task_backend.service.TaskListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="api/task-lists")
@RequiredArgsConstructor
@Tag(name = "Task Lists", description = "APIs for managing task lists")
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    @Operation(
            summary = "List all task lists",
            description = "Retrieve all task lists")
    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @Operation(
            summary = "Get a task list by ID",
            description = "Retrieve a single task list")
    @GetMapping(path="/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId) {
        return taskListService.getTaskList(taskListId)
                .map(taskListMapper::toDto);
    }


    @Operation(
            summary = "Create a task list",
            description = "Create a new task list")
    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);
    }

    @Operation(
            summary = "Update a task list",
            description = "Update an existing task list")
    @PutMapping(path="/{task_list_id}")
    public TaskListDto updateTaskList(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskListDto taskListDto) {

        TaskList updatedTaskList = taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(updatedTaskList);
    }

    @Operation(
            summary = "Update a task list",
            description = "Update an existing task list")
    @DeleteMapping(path="/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
    }
}
