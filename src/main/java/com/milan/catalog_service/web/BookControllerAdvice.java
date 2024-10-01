package com.milan.catalog_service.web;

import com.milan.catalog_service.domain.BookAlreadyExistException;
import com.milan.catalog_service.domain.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String bookNotFoundHandler(BookNotFoundException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(BookAlreadyExistException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY )
    public String bookAlreadyExistExceptionHandler(BookAlreadyExistException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> validationExceptionsHandler(MethodArgumentNotValidException exception){
        Map<String, String> exceptions = new HashMap<>();
        exception.getBindingResult()
                .getAllErrors()
                .forEach(objectError -> {
                    String fieldName = ((FieldError) objectError).getField();
                    String errorMessage = objectError.getDefaultMessage();
                    exceptions.put(fieldName, errorMessage);
                });
        return exceptions;
    }

}
