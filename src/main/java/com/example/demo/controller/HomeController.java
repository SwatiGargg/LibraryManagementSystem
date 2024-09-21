package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
@Controller
public class HomeController {
	 @GetMapping("/home")
	    public String home(HttpSession session) {
		 if(session.getAttribute("user") == null) {
		     	return "home";
		     }
		     else {
		     	return "user/welcome";
		     }
	    }	 
	 
}
