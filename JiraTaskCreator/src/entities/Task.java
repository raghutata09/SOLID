package entities;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import enums.TaskStatus;

public class Task {
    private static final AtomicLong idCounter = new AtomicLong(1);

    // Type can be added in future like EPIC, BUG
    private final long id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private User assignedUser;
    private TaskStatus status;
    private final LocalDateTime createdAt;
    private Long parentTaskId; // null if it's a top-level task
    private final List<Task> subtasks;

    public Task(String title, String description, LocalDateTime deadline, User assignedUser) {
        this.id = idCounter.getAndIncrement();
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.assignedUser = assignedUser;
        this.status = TaskStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.parentTaskId = null;
        this.subtasks = new CopyOnWriteArrayList<>();
    }
    
    // Getters
    public long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public LocalDateTime getDeadline() {
        return deadline;
    }
    
    public User getAssignedUser() {
        return assignedUser;
    }
    
    public TaskStatus getStatus() {
        return status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public Long getParentTaskId() {
        return parentTaskId;
    }
    
    public List<Task> getSubtasks() {
        return new CopyOnWriteArrayList<>(subtasks); // Return a copy for thread safety
    }
    
    // Setters (synchronized for thread safety)
    public synchronized void setTitle(String title) {
        this.title = title;
    }
    
    public synchronized void setDescription(String description) {
        this.description = description;
    }
    
    public synchronized void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    
    public synchronized void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }
    
    public synchronized void setStatus(TaskStatus status) {
        this.status = status;
    }
    
    public synchronized void setParentTaskId(Long parentTaskId) {
        this.parentTaskId = parentTaskId;
    }
    
    // Managing Subtask
    public void addSubtask(Task subtask) {
        subtask.setParentTaskId(this.id);
        subtasks.add(subtask);
    }
    
    public void removeSubtask(Task subtask) {
        subtasks.remove(subtask);
        subtask.setParentTaskId(null);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", assignedUser=" + assignedUser +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", parentTaskId=" + parentTaskId +
                ", subtasks=" + subtasks +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return id == task.id;
    }
} 