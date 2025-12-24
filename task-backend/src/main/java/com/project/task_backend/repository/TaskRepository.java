package com.project.task_backend.repository;

import com.project.task_backend.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID taskId);
    List<Task> findByTaskListId(UUID taskListId);
    void deleteByTaskListIdAndId(UUID taskListId, UUID taskId);
}
