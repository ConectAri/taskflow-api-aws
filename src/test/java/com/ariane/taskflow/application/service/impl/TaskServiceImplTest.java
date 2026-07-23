package com.ariane.taskflow.application.service.impl;

import com.ariane.taskflow.application.exception.TaskNotFoundException;
import com.ariane.taskflow.domain.model.Task;
import com.ariane.taskflow.domain.model.TaskStatus;
import com.ariane.taskflow.domain.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void createTask_shouldSaveAndReturnTask() {
        Task taskToCreate = Task.builder().title("Write tests").build();
        Task savedTask = Task.builder().id(1L).title("Write tests").build();
        when(taskRepository.save(taskToCreate)).thenReturn(savedTask);

        Task result = taskService.createTask(taskToCreate);

        assertThat(result).isEqualTo(savedTask);
        verify(taskRepository).save(taskToCreate);
    }

    @Test
    void getTaskById_whenExists_shouldReturnTask() {
        Task existingTask = Task.builder().id(1L).title("Write tests").build();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));

        Task result = taskService.getTaskById(1L);

        assertThat(result).isEqualTo(existingTask);
    }

    @Test
    void getTaskById_whenNotExists_shouldThrowTaskNotFoundException() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.getTaskById(99L))
                .isInstanceOf(TaskNotFoundException.class);
    }

    @Test
    void listTasks_byStatus_shouldReturnFilteredList() {
        Task todoTask = Task.builder().id(1L).title("Write tests").status(TaskStatus.TODO).build();
        when(taskRepository.findByStatus(TaskStatus.TODO)).thenReturn(List.of(todoTask));

        List<Task> result = taskService.listTasks(TaskStatus.TODO);

        assertThat(result).containsExactly(todoTask);
        verify(taskRepository).findByStatus(TaskStatus.TODO);
        verify(taskRepository, never()).findAll();
    }
}
