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
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.UserLoginRequest;
import com.example.demo.dto.UserRegistrationRequest;
import com.example.demo.dto.Users;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationRequest()); 
        return "user/registeration"; 
    }
 // Register a new user
    @PostMapping("/registeration")
    public String registerUser(@ModelAttribute("user") UserRegistrationRequest request, Model model) {
        try {
            userService.registerUser(request);
            model.addAttribute("success", "User registered successfully.");
            return "user/login"; // Redirect to login page
        } catch (Exception e) {
            model.addAttribute("error", "Failed to register user: " + e.getMessage());
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
    public String loginUser(@ModelAttribute UserLoginRequest request, Model model, HttpSession session) {        
        Users user = userService.loginUser(request); // using this method to return User object if successful
        if (user != null) {
        	session.setAttribute("user", user);
            return "user/welcome"; // Redirect to dashboard
        } else {
            model.addAttribute("errorMessage", "Invalid username or password.");
            return "user/login"; // Return to login page with error message
        }

    }
    @GetMapping("/logout")
    public String redirectToHome(Model model, HttpSession session) {
    	if(session.getAttribute("user") != null) {
    		session.setAttribute("user", null);
    	}
    	return "home";
    }
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
