package com.vr.hotelService.Exception;

public class HotelNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public HotelNotFoundException(String message) {
		super(message);
	}
}
