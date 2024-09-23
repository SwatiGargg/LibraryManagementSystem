package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.Book;
import com.example.demo.dto.IssuedBookDTO;
import com.example.demo.dto.Users;
import com.example.demo.service.BookService;

import jakarta.servlet.http.HttpSession;

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
	    public String getAllBooks(Model model) {
	        List<Book> books = bookService.getAllBooks();
	        model.addAttribute("books", books);
	        return "books/list"; // return the books.html template
	    }	    
	    @PostMapping("/search")
	    public String searchBooks(@RequestParam("searchTerm") String searchTerm, Model model) {
	        List<Book> books = bookService.searchBooksByTitle(searchTerm);
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
	            book.setAvailable(false); 
	        }

	        try {
	            bookService.addBook(book);
	            model.addAttribute("successMessage", "Book added successfully.");
	            return "books/addBook"; 
	        } catch (Exception e) {
	            model.addAttribute("errorMessage", "Failed to add book: " + e.getMessage());
	            return "books/addBook"; 
	        }
}
	    @GetMapping("/issued")
	    public String getIssuedBooks(Model model, HttpSession session) {
	        // Retrieve the logged-in user from the session
	        Users user = (Users) session.getAttribute("user");

	        if (user != null) {
	            // Fetch the list of issued books and their issue dates for this user
	            List<IssuedBookDTO> issuedBooks = bookService.getIssuedBooksAndIssueDatesByUser(user.getId());

	            model.addAttribute("issuedBooks", issuedBooks);
	        } else {
	            model.addAttribute("error", "You need to log in to view your issued books.");
	        }

	        return "books/issued"; 
	    }
}
