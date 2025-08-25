package com.company.books.backend.controller;

import com.company.books.backend.model.Book;
import com.company.books.backend.response.BookResponseRest;
import com.company.books.backend.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final IBookService service;

    public BookRestController(IBookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public ResponseEntity<BookResponseRest> getBooks() {
        BookResponseRest response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponseRest> getBook(@PathVariable Long id) {
        BookResponseRest response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponseRest> createBook(@RequestBody Book book) {
        BookResponseRest response = service.create(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponseRest> updateBook(@PathVariable Long id, @RequestBody Book book) {
        BookResponseRest response = service.update(book, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<BookResponseRest> deleteBook(@PathVariable Long id) {
        BookResponseRest response = service.delete(id);
        return ResponseEntity.ok(response);
    }
}
