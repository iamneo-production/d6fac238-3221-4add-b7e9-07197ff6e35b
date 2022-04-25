package com.examly.springapp.exception;

public class UserNotExistException extends IllegalArgumentException {
	public UserNotExistException(String msg) {
        super(msg);
    }
}