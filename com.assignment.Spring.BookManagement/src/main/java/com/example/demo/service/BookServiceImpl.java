package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.exception.BookAlreadyExistsException;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
        return bookMapper.toDto(book);
    }

    @Override
    public BookDTO addBook(BookDTO bookDto) {
        if (bookRepository.existsById(bookDto.getIsbn())) {
            throw new BookAlreadyExistsException(bookDto.getIsbn());
        }
        Book book = bookMapper.toEntity(bookDto);
        Book saved = bookRepository.save(book);
        return bookMapper.toDto(saved);
    }

    @Override
    public BookDTO updateBook(String isbn, BookDTO bookDto) {
        Book existingBook = bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
        existingBook.setTitle(bookDto.getTitle());
        existingBook.setAuthor(bookDto.getAuthor());
        existingBook.setPublicationYear(bookDto.getPublicationYear());
        Book updated = bookRepository.save(existingBook);
        return bookMapper.toDto(updated);
    }

    @Override
    public void deleteBook(String isbn) {
        if (!bookRepository.existsById(isbn)) {
            throw new BookNotFoundException(isbn);
        }
        bookRepository.deleteById(isbn);
    }
}
