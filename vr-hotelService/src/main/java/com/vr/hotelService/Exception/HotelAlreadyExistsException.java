package com.vr.hotelService.Exception;

public class HotelAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public HotelAlreadyExistsException(String message) {
		super(message);
	}
}
