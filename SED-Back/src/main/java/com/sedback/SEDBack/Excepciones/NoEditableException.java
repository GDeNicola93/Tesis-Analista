
package com.sedback.SEDBack.Excepciones;


public class NoEditableException extends RuntimeException {

    public NoEditableException() {
    }

    public NoEditableException(String message) {
        super(message);
    }
    
}
