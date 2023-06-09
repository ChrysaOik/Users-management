package com.agileproject.userscrud.rest;

import jakarta.validation.ConstraintViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class UserRestExceptionHandler { //handles errors and maps them to appropriate error responses

    @ExceptionHandler //specifies the type of the exception
    ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exc) {
        //create response

        UserErrorResponse error = new UserErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(Exception exc) { //handles generic exceptions

        // create a UserErrorResponse
        UserErrorResponse error = new UserErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class) //type of exceptions
    public ResponseEntity<Object> handleTransactionException(TransactionSystemException ex) {
        Throwable cause = ex.getRootCause(); //checks root cause of the exception
        if (cause instanceof ConstraintViolationException) { //if the cause is a constraint violation i.e. if any of the validations is being violated
            ConstraintViolationException constraintEx = (ConstraintViolationException) cause;
            List<String> errors = new ArrayList<>();
            constraintEx.getConstraintViolations().forEach(violation -> {
                errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserErrorResponse("Validation error", errors));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserErrorResponse("Server error", "Could not save user"));
        }
    }

}
