package com.edstem.ProjectManagement.controller;

import com.edstem.ProjectManagement.contract.BookResponse;
import com.edstem.ProjectManagement.model.Book;
import com.edstem.ProjectManagement.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse>getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookResponse book) {
        BookResponse newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook,HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<BookResponse> updateBookById(@PathVariable Long id, @Valid @RequestBody BookResponse book) {
        BookResponse bookResponse = bookService.addBook(book);
        return new ResponseEntity<>(bookResponse,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookResponse updatedBook) {
        try {
            BookResponse updated = bookService.updateBook(id, updatedBook);
            return ResponseEntity.ok().body(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book with id " + id + " has been deleted");
    }
}
