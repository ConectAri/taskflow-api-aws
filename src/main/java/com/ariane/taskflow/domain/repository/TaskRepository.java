package com.ariane.taskflow.domain.repository;

import com.ariane.taskflow.domain.model.Task;
import com.ariane.taskflow.domain.model.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task save(Task task);

    Optional<Task> findById(Long id);

    List<Task> findAll();

    List<Task> findByStatus(TaskStatus status);

    void deleteById(Long id);
}
