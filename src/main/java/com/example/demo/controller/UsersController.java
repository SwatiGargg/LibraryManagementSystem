package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.UserLoginRequest;
import com.example.demo.dto.UserRegistrationRequest;
import com.example.demo.dto.Users;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    // Register a new user
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
//        try {
//            userService.registerUser(request);
//            return ResponseEntity.ok("User registered successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Failed to register user: " + e.getMessage());
//        }
//    }

    @GetMapping("/register")
    public String showRegistrationForm() {
       // model.addAttribute("userRegistrationRequest", new UserRegistrationRequest());
        return "user/registeration"; // This refers to register.html
    }
 // Register a new user
    @PostMapping("/registeration")
    public String registerUser(@ModelAttribute UserRegistrationRequest request, Model model) {
        try {
            userService.registerUser(request);
            model.addAttribute("successMessage", "User registered successfully.");
            return "user/login"; // Redirect to login page or a success page
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to register user: " + e.getMessage());
            return "user/registeration"; // Return to the registration page with error message
        }
    }
    
    // Show the login form
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "user/login"; // This refers to login.html
    }
    
    // Handle login
    @PostMapping("/userlogin")
    public String loginUser(@ModelAttribute UserLoginRequest request, Model model) {
        boolean loginSuccessful = userService.loginUser(request); // Implement this method

        if (loginSuccessful) {
            return "user/welcome"; // Redirect to home or dashboard
        } else {
            model.addAttribute("errorMessage", "Invalid username or password.");
            return "user/login"; // Return to login page with error message
        }
    }
    
    
    
    // Log in a user
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest request) {
//        boolean success = userService.loginUser(request);
//        if (success) {
//            return ResponseEntity.ok("Login successful.");
//        } else {
//            return ResponseEntity.status(401).body("Invalid credentials.");
//        }
//    }

    // Fetch user details by username
    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        try {
            Users user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("User not found: " + e.getMessage());
        }
    }
    // List all users
    @GetMapping("/all")
    public ResponseEntity<List<Users>> listAllUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
