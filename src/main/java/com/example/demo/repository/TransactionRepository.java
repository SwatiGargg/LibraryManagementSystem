package com.example.demo.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.dto.Book;
import com.example.demo.dto.Transactions;
import com.example.demo.dto.Users;
import com.example.repository.PsqlRepository;

@Repository

public interface TransactionRepository extends PsqlRepository<Transactions, Long> {
    Optional<Transactions> findByUserAndBook(Users user, Book book);

}
