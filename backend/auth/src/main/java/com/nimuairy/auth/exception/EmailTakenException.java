package com.nimuairy.auth.exception;

public class EmailTakenException extends RuntimeException {

	String email;

	public EmailTakenException(String email) {
		super("Error: Email address: `" + email + "` is already taken!");
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}
