package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Book;
import com.example.demo.repository.BookRepository;
@Service
public class BookService {
	@Autowired
    private BookRepository bookRepository;

    // Retrieve all books
	 public List<Book> getAllBooks() {
	        Iterable<Book> booksIterable = bookRepository.findAll();
	        List<Book> books = new ArrayList<>();
	        booksIterable.forEach(books::add); // Convert Iterable to List
	        return books;
	    }

	  // Search for a book by ID
	    public Book getBookById(Long id) {
	        Optional<Book> bookOptional = bookRepository.findById(id);
	        return bookOptional.orElse(null);
	    }
	 // Add a new book
	    public void addBook(Book book) {
	        bookRepository.save(book);
	    }
	    //search book by title
	    public List<Book> searchBooksByTitle(String title) {
	        return bookRepository.findByTitleContainingIgnoreCase(title);
	    }




}
