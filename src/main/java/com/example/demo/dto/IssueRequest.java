package com.example.demo.dto;

public class IssueRequest {
	private Long userId;
    private Long bookId;

    // Default constructor
    public IssueRequest() {}

    // Parameterized constructor
    public IssueRequest(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
}
