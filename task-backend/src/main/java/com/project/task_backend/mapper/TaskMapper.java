package com.project.task_backend.mapper;

import com.project.task_backend.domain.dto.TaskDto;
import com.project.task_backend.domain.entity.Task;

public interface TaskMapper {

    Task fromDto(TaskDto dto);
    TaskDto toDto(Task dto);
}
