package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.Book;
import com.example.demo.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	

	    @Autowired
	    private BookService bookService;
	    
	 // Show home page
	    @GetMapping
	    public String homePage() {
	        return "home"; // return the home.html template
	    }

	    // Show list of books
	    @GetMapping("/all")
/*	    public ResponseEntity<List<Book>> getAllBooks() {
	        List<Book> books = bookService.getAllBooks();
	        return ResponseEntity.ok(books);
	    }       */
	    public String getAllBooks(Model model) {
	        List<Book> books = bookService.getAllBooks();
	        model.addAttribute("books", books);
	        return "books/list"; // return the books.html template
	    }

	    // Search for a book
//	    @GetMapping("/search/{id}")
//	    public ResponseEntity<Book> searchBookById(@PathVariable Long id) {
//	        Book book = bookService.getBookById(id);
//	        if (book != null) {
//	            return ResponseEntity.ok(book);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }
	    
	    @PostMapping("/search")
	    public String searchBooks(@RequestParam("searchTerm") String searchTerm, Model model) {
	        List<Book> books = bookService.searchBooksByTitle(searchTerm);
	        System.out.println("books"+books);
	        System.out.println("searchTerm"+searchTerm);
	        model.addAttribute("books", books);
	        model.addAttribute("searchTerm", searchTerm);
	        return "books/list";  // This will render the search result on the same list.html page
	    }
	    // Add a new book
	    @GetMapping("/addBook")
	    public String showBookForm() {
	        return "books/addBook"; // This refers to addBook.html
	    }
	    @PostMapping("/add")
	    public String addBook(@ModelAttribute Book book, Model model) {
	        // Check if the checkbox was checked
	        if (book.getAvailable() == null) {
	            book.setAvailable(false); // If not checked, set to false
	        }

	        try {
	            bookService.addBook(book);
	            model.addAttribute("successMessage", "Book added successfully.");
	            return "books/addBook"; // Return to the same page to add more books
	        } catch (Exception e) {
	            model.addAttribute("errorMessage", "Failed to add book: " + e.getMessage());
	            return "books/addBook"; // Return to the same page with error message
	        }
}
}
