package com.fantasy.contestapi.validation;

import org.springframework.validation.Errors;

public class ContestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Errors errors;
    private String errorCode;
    private String errorMessage;

    public ContestException(String errorCode, String message) {
        super(errorCode);
        this.setErrorCode(errorCode);
        this.setErrorMessage(message);
        this.setErrors(errors);
    }

    public ContestException() {
        super();
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
