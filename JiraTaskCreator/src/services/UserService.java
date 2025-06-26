package services;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import entities.User;
import utils.PasswordUtil;
import utils.ValidationUtil;
import exceptions.UserException;


public class UserService {
    private static volatile UserService instance;
    private final Map<Long, User> usersById;
    private final Map<String, User> usersByUsername;
    private final Map<String, User> usersByEmail;
    
    private UserService() {
        this.usersById = new ConcurrentHashMap<>();
        this.usersByUsername = new ConcurrentHashMap<>();
        this.usersByEmail = new ConcurrentHashMap<>();
    }
    // Singleton instance
    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    //Register a new user
    public synchronized User registerUser(String username, String email, String password) {
        // Validate inputs
        if (!ValidationUtil.validateUsername(username)) {
            throw new UserException("Invalid username format");
        }
        if (!ValidationUtil.validateEmail(email)) {
            throw new UserException("Invalid email format");
        }
        if (!ValidationUtil.validatePassword(password)) {
            throw new UserException("Password does not meet strength requirements");
        }
        
        // Check if username or email already exists
        if (usersByUsername.containsKey(username)) {
            throw new UserException("Username already exists");
        }
        if (usersByEmail.containsKey(email)) {
            throw new UserException("Email already exists");
        }
        
        // Create new user
        String hashedPassword = PasswordUtil.hashPassword(password);
        User user = new User(username, email, hashedPassword);
        
        // Store user in all maps
        usersById.put(user.getId(), user);
        usersByUsername.put(username, user);
        usersByEmail.put(email, user);
        
        return user;
    }
    
    //Get user by username
    public User getUserByUsername(String username) {
        return usersByUsername.get(username);
    }
    
    //Check if user exists
    public boolean userExists(long userId) {
        return usersById.containsKey(userId);
    }
} 