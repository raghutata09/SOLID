package entities;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class User {
    private static final AtomicLong idCounter = new AtomicLong(1);
    
    private final long id;
    private final String username;
    private final String email;
    private final String passwordHash;
    
    public User(String username, String email, String passwordHash) {
        this.id = idCounter.getAndIncrement();
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }
    
    // Getters
    public long getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id;
    }
} 