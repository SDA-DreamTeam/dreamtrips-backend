package com.github.dreamteam.controller;

import com.github.dreamteam.exception.BadRequestException;
import com.github.dreamteam.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Advice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleNotFound(BadRequestException e) {
        return ResponseEntity.badRequest().build();
    }

}
