package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.ErrorDetail;
import com.lambdaschool.bookstore.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    BookService bookService;

    @ApiOperation(value = "Updates a current Book by BookID", response = void.class)
    @ApiResponses(value =  {
            @ApiResponse(code = 400, message = "Need Valid Book Object", response = ErrorDetail.class),
            @ApiResponse(code = 404, message = "Book Not Found", response = ErrorDetail.class)
    })
    @PutMapping(value = "/books/{id}", produces = {"application/json"})
    public ResponseEntity<?> updateBook(@Valid @RequestBody Book updateBook, @PathVariable long id) throws URISyntaxException {
        bookService.update(updateBook, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Assign Book to Author", response = void.class)
    @ApiResponses(value =  {
            @ApiResponse(code = 201, message = "Book Assigned", response = void.class),
            @ApiResponse(code = 500, message = "Error Assigning Book", response = ErrorDetail.class)
    })
    @PostMapping(value = "/books/{bookid}/authors/{authorid}", produces = {"application/json"})
    public ResponseEntity<?> assignBookToAuthor(@ApiParam(value = "Book ID/Author ID", required = true, example = "1/1") @PathVariable long bookid, @PathVariable long authorid) {
        bookService.addAuthor(bookid, authorid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes Book by BookID", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Book Not Found", response = ErrorDetail.class)
    })
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<?> deleteBook(@ApiParam(value = "Book ID", required = true, example = "1") @PathVariable long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
