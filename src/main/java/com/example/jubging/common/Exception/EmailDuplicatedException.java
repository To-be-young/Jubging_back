package com.example.jubging.common.Exception;

public class EmailDuplicatedException extends RuntimeException{
    public EmailDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailDuplicatedException(String message) { super(message); }

    public EmailDuplicatedException() { super(); }
}
