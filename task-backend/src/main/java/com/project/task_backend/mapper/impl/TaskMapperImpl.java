package com.project.task_backend.mapper.impl;

import com.project.task_backend.domain.dto.TaskDto;
import com.project.task_backend.domain.entity.Task;
import com.project.task_backend.mapper.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto(TaskDto dto) {
        return new Task(
                dto.id(),
                dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.priority(),
                dto.status(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
