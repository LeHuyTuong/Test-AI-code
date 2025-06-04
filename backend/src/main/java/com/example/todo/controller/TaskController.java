package com.example.todo.controller;

import com.example.todo.dto.TaskRequest;
import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> all(@AuthenticationPrincipal UserDetails user) {
        return taskService.findByUserId(Long.valueOf(user.getUsername()));
    }

    @PostMapping
    public Task create(@RequestBody TaskRequest request, @AuthenticationPrincipal UserDetails user) {
        Task task = new Task();
        task.setUserId(Long.valueOf(user.getUsername()));
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setStatus("pending");
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return taskService.save(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable String id, @RequestBody TaskRequest request, @AuthenticationPrincipal UserDetails user) {
        return taskService.findById(id)
                .map(task -> {
                    if (!task.getUserId().equals(Long.valueOf(user.getUsername()))) {
                        return ResponseEntity.status(403).build();
                    }
                    task.setTitle(request.getTitle());
                    task.setDescription(request.getDescription());
                    task.setDueDate(request.getDueDate());
                    task.setUpdatedAt(LocalDateTime.now());
                    return ResponseEntity.ok(taskService.save(task));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id, @AuthenticationPrincipal UserDetails user) {
        return taskService.findById(id)
                .map(task -> {
                    if (!task.getUserId().equals(Long.valueOf(user.getUsername()))) {
                        return ResponseEntity.status(403).build();
                    }
                    taskService.delete(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
