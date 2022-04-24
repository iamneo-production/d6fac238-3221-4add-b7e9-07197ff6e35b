package com.examly.springapp.exception;

public class OrderNotExistException extends IllegalArgumentException{
	  public OrderNotExistException(String msg) {
	        super(msg);
	    }
}
