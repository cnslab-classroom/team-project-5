package com.springboot.harubi.Exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) { super(message); }
}
