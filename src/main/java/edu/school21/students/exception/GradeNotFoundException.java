package edu.school21.students.exception;

public class GradeNotFoundException extends RuntimeException {

    public GradeNotFoundException(String message) {
        super(message);
    }
}
