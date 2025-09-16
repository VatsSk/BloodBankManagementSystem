package com.example.bloodBank.exceptionHandlers;

public class UserDoesnotExistsException extends RuntimeException{
    public UserDoesnotExistsException(String message){
        super(message);
    }
}
