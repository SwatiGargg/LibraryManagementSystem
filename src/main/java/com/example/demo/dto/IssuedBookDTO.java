package com.example.demo.dto;

public class IssuedBookDTO {
    private Book book;
    private String issueDate;
    private Long transactionId;

    // Constructor
    public IssuedBookDTO(Book book, String issueDate, Long transactionId) {
        this.book = book;
        this.issueDate = issueDate;
        this.transactionId = transactionId;
    }

    // Getters and Setters
    
    public Long getTransactionId() {
    	return transactionId;
    }
    
    public void setTransactionId(Long transactionId) {
    	this.transactionId = transactionId;
    }
   
    public Book getBook() {
        return book;
    }
	public void setBook(Book book) {
        this.book = book;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
}