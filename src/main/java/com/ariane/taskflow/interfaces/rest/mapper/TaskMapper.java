package com.ariane.taskflow.interfaces.rest.mapper;

import com.ariane.taskflow.domain.model.Task;
import com.ariane.taskflow.interfaces.rest.dto.TaskRequestDTO;
import com.ariane.taskflow.interfaces.rest.dto.TaskResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequestDTO dto) {
        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .dueDate(dto.getDueDate())
                .build();
    }

    public TaskResponseDTO toResponseDTO(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .build();
    }

    public List<TaskResponseDTO> toResponseDTOList(List<Task> tasks) {
        return tasks.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
