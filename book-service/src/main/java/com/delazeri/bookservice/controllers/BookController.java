package com.delazeri.bookservice.controllers;

import com.delazeri.bookservice.models.Book;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "book-service")
public class BookController {
    private final Environment environment;

    public BookController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping(value = "{id}/{currency}")
    public ResponseEntity<Book> findBookById(
            @PathVariable Long id,
            @PathVariable String currency
    ) {
        String port = environment.getProperty("local.server.port");

        return ResponseEntity.ok(new Book(1L, "Anyone", "Anything", new Date(), 3.14, currency, port));
    }
}
