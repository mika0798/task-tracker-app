package com.project.task_backend.mapper;

import com.project.task_backend.domain.dto.TaskListDto;
import com.project.task_backend.domain.entity.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto dto);
    TaskListDto toDto(TaskList taskList);
}
