package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.Users;
import com.example.demo.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/transactions")
public class TransactionsController {
	@Autowired
    private TransactionService transactionService;
	// Issue a book
    @PostMapping("/issue/{id}")
    public String issueBook(@PathVariable Long id, @RequestParam("userId") Long userId, RedirectAttributes redirectAttributes) {
        if (userId == 0) {
            // Redirect to login page if the user is not logged in
            return "redirect:/users/login";
        }

        // Issue the book to the logged-in user
        try {
        	transactionService.issueBook(id, userId);
            redirectAttributes.addFlashAttribute("success", "Book issued successfully.");
            return "redirect:/books/all";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while issuing the book. -"+e.getMessage());
            return "redirect:/books/all";
        }

    }    
    @GetMapping("/issueBook")
    public String issueBook(Model model) {
        return "redirect:/books/all";
    }

    // Return a book
    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable Long id, @RequestParam("userId") Long userId,@RequestParam("transId") Long transId, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        Users loggedInUser = (Users) session.getAttribute("user");

        if (loggedInUser == null) {
            model.addAttribute("error", "You need to log in to return books.");
            return "redirect:/users/login";
        }

        try {
            // Call service to return the book
        	transactionService.returnBook(userId, id, transId);
            model.addAttribute("success", "Book returned successfully.");
        } catch (Exception e) {
            model.addAttribute("error", "Error returning book.");
        }

        // Redirect to the issued books page
        return "redirect:/books/issued";
    }
    
    @GetMapping("/returnBook")
    public String returnBook(Model model) {
        return "redirect:/books/issued";
    }

}