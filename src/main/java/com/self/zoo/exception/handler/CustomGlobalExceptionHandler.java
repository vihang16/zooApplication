package com.self.zoo.exception.handler;

import com.self.zoo.exception.custom.InvalidRoomDetailException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(value = InvalidRoomDetailException.class)
    public ResponseEntity<String> invalidRoomHandler(InvalidRoomDetailException ex,HttpServletResponse response){
       return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<String> constrainViolationHandler(ConstraintViolationException ex, HttpServletResponse response){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
