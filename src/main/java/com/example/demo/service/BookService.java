package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Book;
import com.example.demo.dto.IssuedBookDTO;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.TransactionRepository;
@Service
public class BookService {
	@Autowired
    private BookRepository bookRepository;
	@Autowired
    private TransactionRepository transactionRepository;

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
	    	Book bk = getBookByTitleAndAuthor(book.getTitle(), book.getAuthor());
	    	if(bk != null) {
	    		bk.setCount(bk.getCount()+1);
	    		bk.setAvailable(true);
	    		bookRepository.save(bk);
	    	}
	    	else {
	    		book.setAvailable(true);
	    		bookRepository.save(book);
	    	}
	    }
	    //search book by title
	    public List<Book> searchBooksByTitle(String title) {
	        return bookRepository.findByTitleContainingIgnoreCase(title);
	    }
	    public Book getBookByTitleAndAuthor(String title, String author) {
	    	return bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author);
	    }
	    
	 // Method to get issued books and their issue dates for a specific user
	    public List<IssuedBookDTO> getIssuedBooksAndIssueDatesByUser(Long userId) {
	        List<Object[]> results = transactionRepository.findIssuedBooksAndIssueDateByUserId(userId);
	        List<IssuedBookDTO> issuedBooks = new ArrayList<>();

	        // Iterate through the results and map them to a DTO
	        for (Object[] result : results) {
	            Book book = (Book) result[0];
	            LocalDateTime issueDate = (LocalDateTime) result[1];  // Adjust the type if issueDate is of a different type
	            Long tranId = (Long) result[2];
	            issuedBooks.add(new IssuedBookDTO(book, issueDate.toString(), tranId));
	        }

	        return issuedBooks;
	    }


}
