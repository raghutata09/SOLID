package services;

import entities.User;
import exceptions.AuthenticationException;
import utils.PasswordUtil;


public class AuthenticationService {
    private static AuthenticationService instance;
    private final UserService userService;
    
    private AuthenticationService() {
        this.userService = UserService.getInstance();
    }
    
    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }
    
   //Login user with username and password. Same thing can be done with EMAIL if needed in future
    public synchronized User login(String username, String password) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new AuthenticationException("User not found: " + username);
        }
        
        if (!PasswordUtil.verifyPassword(password, user.getPasswordHash())) {
            throw new AuthenticationException("Invalid password for user: " + username);
        }
        
        return user;
    }
} 