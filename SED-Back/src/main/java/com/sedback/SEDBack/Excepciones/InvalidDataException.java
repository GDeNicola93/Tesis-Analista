package com.sedback.SEDBack.Excepciones;

import org.springframework.validation.BindingResult;


public class InvalidDataException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final transient BindingResult result;

    public InvalidDataException(BindingResult result) {
        this.result = result;
    }

    public InvalidDataException(BindingResult result, String message) {
        super(message);
        this.result = result;
    }

    public BindingResult getResult() {
        return result;
    }
}
