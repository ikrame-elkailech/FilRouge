package com.example.filrouge.Exception;

public class EmailDuplicateRecordException extends RuntimeException{
    public EmailDuplicateRecordException(String message) {
        super(message);
    }
}
