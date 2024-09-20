package com.example.demo.dto;

public class UserRegistrationRequest {
	 private String username;
	    private String password;
	    private Boolean adminUser;
	    // Default constructor
	    public UserRegistrationRequest() {}

	    // Parameterized constructor
	    public UserRegistrationRequest(String username, String password, String userType) {
	        this.username = username;
	        this.password = password;
	        this.adminUser = true;
	    }

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Boolean getAdminUser() {
			return adminUser;
		}

		public void setAdminUser(Boolean adminUser) {
			this.adminUser = adminUser;
		}

		
		
}
