package com.nimuairy.auth.exception;

public class UsernameTakenException extends RuntimeException {

	String username;

	public UsernameTakenException(String username) {
		super("Error: Username: `" + username + "` is already taken!");
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
