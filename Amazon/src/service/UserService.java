package service;

import model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {

    private static UserService userService;
    private Map<String, User> userMap;

    private UserService() {
        this.userMap = new ConcurrentHashMap<>();
    }

    public static synchronized UserService getInstance(){
        if(userService==null){
            userService = new UserService();
        }
        return userService;
    }


    // Method to register a new user
    public User registerUser(String username, String phone, String email) {
        User user = new User(username,phone, email);
        userMap.put(user.getId(),user);
        return user;
    }

    // Method to get user details
    public boolean userExists(String userID) {
       return userMap.containsKey(userID);
    }
}
