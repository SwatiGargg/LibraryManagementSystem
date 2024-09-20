package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.IssueRequest;
import com.example.demo.dto.ReturnRequest;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
	@Autowired
    private TransactionService transactionService;
	// Issue a book
    @PostMapping("/issue")
    public ResponseEntity<?> issueBook(@RequestBody IssueRequest request) {
        try {
            transactionService.issueBook(request.getBookId(), request.getUserId());
            return ResponseEntity.ok("Book issued successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to issue the book: " + e.getMessage());
        }
    }

    // Return a book
    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody ReturnRequest request) {
        try {
            transactionService.returnBook(request.getBookId(), request.getUserId());
            return ResponseEntity.ok("Book returned successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to return the book: " + e.getMessage());
        }
    }

}
