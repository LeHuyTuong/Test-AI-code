package com.example.todo.dto;

import java.time.LocalDateTime;

public class TaskRequest {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
}
