package com.sedback.SEDBack.Excepciones;


public class BadRequestException extends RuntimeException{

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
    
}
