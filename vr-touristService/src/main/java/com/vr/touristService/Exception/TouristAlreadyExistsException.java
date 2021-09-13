package com.vr.touristService.Exception;

public class TouristAlreadyExistsException extends RuntimeException{
    public TouristAlreadyExistsException(String message){
        super(message);
    }
}
