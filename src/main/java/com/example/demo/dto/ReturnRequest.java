package com.example.demo.dto;

public class ReturnRequest {
	 private Long userId;
	    private Long bookId;

	    // Default constructor
	    public ReturnRequest() {}

	    // Parameterized constructor
	    public ReturnRequest(Long userId, Long bookId) {
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
