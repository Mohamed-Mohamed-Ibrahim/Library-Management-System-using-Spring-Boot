package org.example.controller;

import org.example.Service.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/borrow")
public class BorrowingController {

    @Autowired
    private Library library;

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<Object> addBorrow(@PathVariable int bookId, @PathVariable int patronId) {
        boolean success = library.addBorrow(bookId, patronId);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<Object> removeBorrow(@PathVariable int bookId, @PathVariable int patronId) {
        boolean success = library.removeBorrow(bookId, patronId);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
