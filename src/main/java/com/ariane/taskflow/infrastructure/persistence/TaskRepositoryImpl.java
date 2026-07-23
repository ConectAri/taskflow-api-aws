package com.ariane.taskflow.infrastructure.persistence;

import com.ariane.taskflow.domain.model.Task;
import com.ariane.taskflow.domain.model.TaskStatus;
import com.ariane.taskflow.domain.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskJpaRepository taskJpaRepository;

    public TaskRepositoryImpl(TaskJpaRepository taskJpaRepository) {
        this.taskJpaRepository = taskJpaRepository;
    }

    @Override
    public Task save(Task task) {
        return taskJpaRepository.save(task);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskJpaRepository.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return taskJpaRepository.findAll();
    }

    @Override
    public List<Task> findByStatus(TaskStatus status) {
        return taskJpaRepository.findByStatus(status);
    }

    @Override
    public void deleteById(Long id) {
        taskJpaRepository.deleteById(id);
    }
}
