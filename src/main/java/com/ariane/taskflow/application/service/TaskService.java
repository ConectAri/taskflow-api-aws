package com.ariane.taskflow.application.service;

import com.ariane.taskflow.domain.model.Task;
import com.ariane.taskflow.domain.model.TaskStatus;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    List<Task> listTasks(TaskStatus status);

    Task getTaskById(Long id);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);
}
