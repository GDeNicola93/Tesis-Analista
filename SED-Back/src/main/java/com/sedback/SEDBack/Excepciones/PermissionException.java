package com.sedback.SEDBack.Excepciones;


public class PermissionException extends RuntimeException{

    public PermissionException() {
    }

    public PermissionException(String message) {
        super(message);
    }
    
}
