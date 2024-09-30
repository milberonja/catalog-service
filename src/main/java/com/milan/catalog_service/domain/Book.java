package com.milan.catalog_service.domain;

public record Book(
        String isbn,
        String title,
        String author,
        Double price
) {}
