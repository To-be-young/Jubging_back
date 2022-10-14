package com.example.jubging.common.Exception;

public class EmailValidCodeException  extends RuntimeException{
    public EmailValidCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailValidCodeException(String message) { super(message); }

    public EmailValidCodeException() { super(); }

}
