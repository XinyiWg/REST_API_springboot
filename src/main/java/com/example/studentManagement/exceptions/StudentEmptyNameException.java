package com.example.studentManagement.exceptions;

public class StudentEmptyNameException extends RuntimeException {

    public StudentEmptyNameException(String message) {
        super(message);
    }
}
