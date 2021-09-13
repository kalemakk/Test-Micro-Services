package com.vr.touristService.Controller;

import com.vr.touristService.Exception.TouristAlreadyExistsException;
import com.vr.touristService.Exception.TouristNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(TouristNotFoundException.class)
    public ResponseEntity<String> handleTouristNotFoundException(TouristNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TouristAlreadyExistsException.class)
    public ResponseEntity<String> handleTouristAlreadyExistsException(TouristAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> UnknownException(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
