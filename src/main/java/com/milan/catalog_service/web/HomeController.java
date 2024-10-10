package com.milan.catalog_service.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Value("${polar.greeting}")
    private String greetingMessage;

    @GetMapping("/")
    public String greeting(){
        return greetingMessage;
    }
}
