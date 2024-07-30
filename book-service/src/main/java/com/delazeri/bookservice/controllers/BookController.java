package com.delazeri.bookservice.controllers;

import com.delazeri.bookservice.models.Book;
import com.delazeri.bookservice.repositories.BookRepository;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "book-service")
public class BookController {
    private final Environment environment;
    private final BookRepository bookRepository;

    public BookController(Environment environment, BookRepository bookRepository) {
        this.environment = environment;
        this.bookRepository = bookRepository;
    }

    @GetMapping(value = "{id}/{currency}")
    public ResponseEntity<Book> findBookById(
            @PathVariable Long id,
            @PathVariable String currency
    ) {
        String port = environment.getProperty("local.server.port");

        Book book = this.bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setEnvironment(port);

        return ResponseEntity.ok(book);
    }
}
