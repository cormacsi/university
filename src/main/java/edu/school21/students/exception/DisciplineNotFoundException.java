package edu.school21.students.exception;

public class DisciplineNotFoundException extends RuntimeException {

    public DisciplineNotFoundException(String message) {
        super(message);
    }
}
