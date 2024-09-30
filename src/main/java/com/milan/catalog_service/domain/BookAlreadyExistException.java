package com.milan.catalog_service.domain;

public class BookAlreadyExistException extends RuntimeException {
    public BookAlreadyExistException(String isbn){
        super("The book with ISBN: " + isbn + " already exist.");
    }
}
