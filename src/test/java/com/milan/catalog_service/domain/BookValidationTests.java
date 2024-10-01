package com.milan.catalog_service.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds(){
        var book = new Book("0123456789", "Naslov", "Autor", 8.96);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void AllFieldsAreNotCorrectThenValidationFails(){
        var book = new Book("012345678", "", "", 0.0);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        List<String> violationMessages = new ArrayList<>();
        var violation = violations.iterator();
        while (violation.hasNext()){
            violationMessages.add(violation.next().getMessage());
        }
        assertThat(violations).hasSize(4);
        assertThat(violationMessages).contains("The ISBN format must be valid!",
                "The book title must be defined!",
                "The book author must be defined!",
                "The book price must be greeter than zero!");
    }

}
