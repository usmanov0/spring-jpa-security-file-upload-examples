package com.example.hibernateexample.exception;

public class BadCredentials extends RuntimeException {
    public BadCredentials(String message){
        super(message);
    }
}
