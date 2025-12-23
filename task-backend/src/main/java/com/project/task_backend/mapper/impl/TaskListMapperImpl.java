package com.project.task_backend.mapper.impl;

import com.project.task_backend.domain.dto.TaskListDto;
import com.project.task_backend.domain.entity.Task;
import com.project.task_backend.domain.entity.TaskList;
import com.project.task_backend.domain.entity.TaskStatus;
import com.project.task_backend.mapper.TaskListMapper;
import com.project.task_backend.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;



@Component
@RequiredArgsConstructor
public class TaskListMapperImpl implements TaskListMapper {
    private final TaskMapper taskMapper;

    @Override
    public TaskList fromDto(TaskListDto dto) {
        return new TaskList(
                dto.id(),
                dto.title(),
                dto.description(),
                Optional.ofNullable(dto.tasks())
                        .map(tasks -> tasks.stream()
                            .map(taskMapper::fromDto)
                            .toList())
                        .orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        final List<Task> tasks = taskList.getTasks();
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(tasks)
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(tasks),
                Optional.ofNullable(tasks)
                        .map(t -> t.stream()
                                .map(taskMapper::toDto)
                                .toList()
                        ).orElse(null)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if(null == tasks) {
            return null;
        }
        long closeTaskCount = tasks.stream()
                .filter(task -> TaskStatus.CLOSED == task.getStatus())
                .count();
        return (double) closeTaskCount / tasks.size();
    }
}
