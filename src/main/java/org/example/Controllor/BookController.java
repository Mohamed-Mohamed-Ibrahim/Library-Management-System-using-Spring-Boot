package org.example.controller;

import org.example.Entities.Book;
import org.example.Service.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin()
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private Library library;

    @GetMapping
    public ArrayList<Book> retrieveBooks() {
        return library.getBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {
        Book book = library.getBook(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> addBook(@RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors: " + bindingResult.getAllErrors());
        }

        library.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors: " + bindingResult.getAllErrors());
        }

        boolean success = library.updateBook(id, book);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {
        boolean success = library.deleteBook(id);
        if (success) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
