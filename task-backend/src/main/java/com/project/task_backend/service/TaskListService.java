package com.project.task_backend.service;

import com.project.task_backend.domain.entity.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
}
