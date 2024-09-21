package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Book;
import com.example.demo.dto.Transactions;
import com.example.demo.dto.Users;
import com.example.repository.PsqlRepository;

@Repository

public interface TransactionRepository extends PsqlRepository<Transactions, Long> {
    Optional<Transactions> findByUserAndBook(Users user, Book book);    
    // Custom query to fetch books and issueDate for a specific user
    @Query("SELECT b, t.issueDate, t.id FROM Book b JOIN Transactions t ON b.id = t.book.id WHERE t.user.id = :userId AND t.returnDate IS NULL")
    List<Object[]> findIssuedBooksAndIssueDateByUserId(Long userId);
}
