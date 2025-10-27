package com.nikam.patient_service.exceptions;

public class NoPatientFoundException extends RuntimeException {
    public NoPatientFoundException(String message) {
        super(message);
    }
}
