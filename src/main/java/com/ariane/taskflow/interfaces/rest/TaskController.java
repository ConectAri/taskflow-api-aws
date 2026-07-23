package com.ariane.taskflow.interfaces.rest;

import com.ariane.taskflow.application.service.TaskService;
import com.ariane.taskflow.domain.model.Task;
import com.ariane.taskflow.domain.model.TaskStatus;
import com.ariane.taskflow.interfaces.rest.dto.TaskRequestDTO;
import com.ariane.taskflow.interfaces.rest.dto.TaskResponseDTO;
import com.ariane.taskflow.interfaces.rest.mapper.TaskMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "Operações de gerenciamento de tarefas")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @Operation(summary = "Criar tarefa", description = "Cria uma nova tarefa com título obrigatório, descrição, status, prioridade e data de vencimento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição")
    })
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO requestDTO) {
        Task createdTask = taskService.createTask(taskMapper.toEntity(requestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskMapper.toResponseDTO(createdTask));
    }

    @Operation(summary = "Listar tarefas", description = "Lista todas as tarefas, com filtro opcional pelo parâmetro status.")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listTasks(
            @RequestParam(required = false) TaskStatus status) {
        List<Task> tasks = taskService.listTasks(status);
        return ResponseEntity.ok(taskMapper.toResponseDTOList(tasks));
    }

    @Operation(summary = "Buscar tarefa por id", description = "Retorna os dados de uma tarefa específica pelo seu id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(taskMapper.toResponseDTO(task));
    }

    @Operation(summary = "Atualizar tarefa", description = "Atualiza os dados de uma tarefa existente pelo seu id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long id, @Valid @RequestBody TaskRequestDTO requestDTO) {
        Task updatedTask = taskService.updateTask(id, taskMapper.toEntity(requestDTO));
        return ResponseEntity.ok(taskMapper.toResponseDTO(updatedTask));
    }

    @Operation(summary = "Excluir tarefa", description = "Remove uma tarefa existente pelo seu id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
