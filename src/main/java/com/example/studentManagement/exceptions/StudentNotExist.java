package com.example.studentManagement.exceptions;

public class StudentNotExist extends RuntimeException {
    public StudentNotExist(String message) {
        super(message);
    }
}
