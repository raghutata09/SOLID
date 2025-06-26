package entities;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import enums.TaskStatus;

public class Story {
    private static final AtomicLong idCounter = new AtomicLong(1);
    
    private final long id;
    private String title;
    private String description;
    private final LocalDateTime createdAt;
    private final List<Task> tasks;
    private User createdByUser;
    
    public Story(String title, String description, User user) {
        this.id = idCounter.getAndIncrement();
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.tasks = new CopyOnWriteArrayList<>();
        this.createdByUser = user;
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
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public List<Task> getTasks() {
        return new CopyOnWriteArrayList<>(tasks); // Return a copy for thread safety
    }
    
    // Task management (thread-safe)
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", tasks=" + tasks +
                ", createdByUser=" + createdByUser +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Story story = (Story) obj;
        return id == story.id;
    }
} 