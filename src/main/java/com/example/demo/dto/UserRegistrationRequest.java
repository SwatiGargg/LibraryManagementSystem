package com.example.demo.dto;

public class UserRegistrationRequest {
	 private String username;
	    private String password;
	    private String confirmpassword;
	    private String email;
	    private Boolean adminUser;
	    // Default constructor
	    public UserRegistrationRequest() {}

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
		

		public String getConfirmpassword() {
			return confirmpassword;
		}

		public void setConfirmpassword(String confirmpassword) {
			this.confirmpassword = confirmpassword;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Boolean getAdminUser() {
			return adminUser;
		}

		public void setAdminUser(Boolean adminUser) {
			this.adminUser = adminUser;
		}

		
		
}
