package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Users;
import com.example.demo.dto.Book;
import com.example.demo.dto.Transactions;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
@Service

public class TransactionService {
	@Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Issue a book
    public void issueBook(Long bookId, Long userId) throws Exception {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new Exception("User not found"));
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new Exception("Book not found"));

        // Check if the book is available
        if (!book.getAvailable()) {
            throw new Exception("Book is not available for issuance.");
        }
        
        System.out.println("book"+book);

        // Create a new transaction
        Transactions transaction = new Transactions();
        transaction.setUser(user);
        transaction.setBook(book);
        transaction.setIssueDate(LocalDateTime.now());

        // Save the transaction
        transactionRepository.save(transaction);

        // Mark the book as not available
        book.setAvailable(false);
        bookRepository.save(book);
    }

    // Return a book
    public void returnBook(Long userId, Long bookId, Long transId) throws Exception {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new Exception("User not found"));
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new Exception("Book not found"));

        // Find the transaction
        Transactions transaction = transactionRepository.findById(transId)
            .orElseThrow(() -> new Exception("Transaction not found"));

        // Update the return date
        if(transaction.getReturnDate() == null) {
        	transaction.setReturnDate(LocalDateTime.now());
        	transactionRepository.save(transaction);
        }
        else
        	throw new Exception("Book is already returned");

        // Save the transaction

        // Mark the book as available
        book.setAvailable(true);
        bookRepository.save(book);
    }
    
}