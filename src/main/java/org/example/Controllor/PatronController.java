package org.example.controller;

import org.example.Entities.Patron;
import org.example.Service.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin()
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private Library library;

    @GetMapping
    public ArrayList<Patron> retrievePatrons() {
        return library.getPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatron(@PathVariable int id) {
        Patron patron = library.getPatron(id);
        if (patron != null) {
            return ResponseEntity.ok(patron);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> addPatron(@RequestBody Patron patron, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors: " + bindingResult.getAllErrors());
        }

        library.addPatron(patron);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePatron(@PathVariable int id, @RequestBody Patron patron, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors: " + bindingResult.getAllErrors());
        }

        boolean success = library.updatePatron(id, patron);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatron(@PathVariable int id) {
        boolean success = library.deletePatron(id);
        if (success) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
