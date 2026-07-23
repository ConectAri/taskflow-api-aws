package com.ariane.taskflow.application.service.impl;

import com.ariane.taskflow.application.exception.TaskNotFoundException;
import com.ariane.taskflow.application.service.TaskService;
import com.ariane.taskflow.domain.model.Task;
import com.ariane.taskflow.domain.model.TaskStatus;
import com.ariane.taskflow.domain.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> listTasks(TaskStatus status) {
        if (status == null) {
            return taskRepository.findAll();
        }
        return taskRepository.findByStatus(status);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task existingTask = getTaskById(id);
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setDueDate(task.getDueDate());
        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }
}
