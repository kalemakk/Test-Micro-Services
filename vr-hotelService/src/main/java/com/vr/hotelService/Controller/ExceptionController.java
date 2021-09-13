package com.vr.hotelService.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vr.hotelService.Exception.AttachmentNotFoundException;
import com.vr.hotelService.Exception.CommentNotFoundException;
import com.vr.hotelService.Exception.HotelAlreadyExistsException;
import com.vr.hotelService.Exception.HotelNotFoundException;

@RestControllerAdvice
@RestController
public class ExceptionController {
	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<String> handleHotelNotFoundException(HotelNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HotelAlreadyExistsException.class)
	public ResponseEntity<String> handleHotelAlreadyExistsException(HotelAlreadyExistsException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<String> handleCommentNotFoundException(CommentNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AttachmentNotFoundException.class)
	public ResponseEntity<String> handleAttachmentNotFoundException(AttachmentNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleUnknownException(Exception ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
}
