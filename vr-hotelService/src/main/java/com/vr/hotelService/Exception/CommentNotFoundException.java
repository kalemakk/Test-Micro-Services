package com.vr.hotelService.Exception;

public class CommentNotFoundException extends RuntimeException {	
	private static final long serialVersionUID = 1L;
	public CommentNotFoundException(String message) {
		super(message);
	}
}
