package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    BookService bookService;

    @PutMapping(value = "/books/{id}", produces = {"application/json"})
    public ResponseEntity<?> updateBook(@Valid @RequestBody Book updateBook, @PathVariable long id) {
        bookService.update(updateBook, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/books/{bookid}/authors/{authorid}", produces = {"application/json"})
    public ResponseEntity<?> assignBookToAuthor(@PathVariable long bookid, @PathVariable long authorid) {
        bookService.addAuthor(bookid, authorid);
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
