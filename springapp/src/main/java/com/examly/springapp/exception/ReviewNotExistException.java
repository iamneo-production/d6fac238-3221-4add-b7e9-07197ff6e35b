package com.examly.springapp.exception;

public class ReviewNotExistException extends IllegalArgumentException {
	public ReviewNotExistException (String msg) {
        super(msg);
    }
}
