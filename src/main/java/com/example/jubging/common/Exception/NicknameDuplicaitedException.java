package com.example.jubging.common.Exception;

public class NicknameDuplicaitedException extends RuntimeException {
    public NicknameDuplicaitedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NicknameDuplicaitedException(String message) { super(message); }

    public NicknameDuplicaitedException() { super(); }

}
