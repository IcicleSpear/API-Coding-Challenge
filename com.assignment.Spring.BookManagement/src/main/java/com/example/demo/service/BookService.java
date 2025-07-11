package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.BookDTO;

public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getBookByIsbn(String isbn);
    BookDTO addBook(BookDTO bookDto);
    BookDTO updateBook(String isbn, BookDTO bookDto);
    void deleteBook(String isbn);
}
