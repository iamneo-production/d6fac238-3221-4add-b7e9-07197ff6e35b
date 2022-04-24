package com.examly.springapp.exception;

public class CartNotExistException  extends IllegalArgumentException {
    public CartNotExistException(String msg) {
        super(msg);
    }
}
