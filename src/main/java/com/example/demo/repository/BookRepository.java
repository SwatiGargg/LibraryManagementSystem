package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Book;
import com.example.repository.PsqlRepository;

@Repository
public interface BookRepository extends PsqlRepository<Book, Long>{
	@Query("SELECT b FROM Book b ORDER BY b.id") 
	List<Book> findAllBooks();

    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
     Book findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);
}