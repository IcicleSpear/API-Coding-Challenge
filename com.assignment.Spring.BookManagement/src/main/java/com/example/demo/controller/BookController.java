package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookDTO;
import com.example.demo.service.BookService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable String isbn) {
        BookDTO bookDto = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDto) {
        BookDTO addedBook = bookService.addBook(bookDto);
        return ResponseEntity.ok(addedBook);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable String isbn, @RequestBody BookDTO bookDto) {
        BookDTO updatedBook = bookService.updateBook(isbn, bookDto);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
