package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserLoginRequest;
import com.example.demo.dto.UserRegistrationRequest;
import com.example.demo.dto.Users;
import com.example.demo.repository.UserRepository;
@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;

    // Register a new user
    public void registerUser(UserRegistrationRequest request) throws Exception {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new Exception("Username already exists.");
        }
     // Validate confirm password
        if (!request.getPassword().equals(request.getConfirmpassword())) {
            throw new Exception("Passwords do not match.");
        }
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // In a real application, hash the password before saving
        user.setEmail(request.getEmail());
        user.setAdminUser(request.getAdminUser());
        userRepository.save(user);
    }

    // Log in a user
    public Users loginUser(UserLoginRequest request) {
        Users user = userRepository.findByUsername(request.getUsername());
        if (user != null && user.getPassword().equals(request.getPassword())) {
            return user; // Return the user object if login is successful
        }
        return null; // Return null if login fails
    }
    // Fetch user details by username
    public Users getUserByUsername(String username) throws Exception {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("User not found.");
        }
        return user;
    }

    // List all users
    public List<Users> getAllUsers() {
        Iterable<Users> usersIterable = userRepository.findAll();
        List<Users> users = new ArrayList<>();
        usersIterable.forEach(users::add); // Convert Iterable to List
        return users;
    }
}
