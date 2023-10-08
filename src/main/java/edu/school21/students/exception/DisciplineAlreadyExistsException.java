package edu.school21.students.exception;

public class DisciplineAlreadyExistsException extends RuntimeException {

    public DisciplineAlreadyExistsException(String message) {
        super(message);
    }
}
