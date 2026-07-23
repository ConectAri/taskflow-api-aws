package com.ariane.taskflow.infrastructure.persistence;

import com.ariane.taskflow.domain.model.Task;
import com.ariane.taskflow.domain.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskJpaRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(TaskStatus status);
}
