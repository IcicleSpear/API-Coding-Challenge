package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BookDTO;

public interface BookService {

	BookDTO addBook(BookDTO bookDTO);

	List<BookDTO> getAllBooks();

	BookDTO getBooksByIsbn(String isbn);

	BookDTO updateBook(String isbn, BookDTO bookDTO);

	String deleteBook(String isbn);

}
