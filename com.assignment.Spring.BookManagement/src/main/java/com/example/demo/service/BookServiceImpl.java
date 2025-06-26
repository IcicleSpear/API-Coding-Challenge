package com.example.demo.service;
	
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.exception.BookAlreadyExistsException;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repository.BookRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
	
	@Service
	public class BookServiceImpl implements BookService {
		
		@Autowired
	    private BookRepository repo;
	
	    @Autowired
	    private BookMapper bookMapper;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	
		@Override
		public BookDTO addBook(BookDTO bookDTO) {
			if(repo.existsById(bookDTO.getIsbn()))
			{
				throw new BookAlreadyExistsException("Book with ISBN " + bookDTO.getIsbn() + " already exists.");
			}
			bookDTO.setPassword(passwordEncoder.encode(bookDTO.getPassword()));
			 Book book = bookMapper.toEntity(bookDTO);
		     return bookMapper.toDto(repo.save(book));
		}
	
		@Override
		public List<BookDTO> getAllBooks() {
			return repo.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
		}
	
		@Override
		public BookDTO getBooksByIsbn(String isbn) {
			Book book = repo.findById(isbn)
	                .orElseThrow(() -> new BookNotFoundException("Book not found with ISBN: " + isbn));
	        return bookMapper.toDto(book);
		}
	
		@Override
		
		public BookDTO updateBook(String isbn, BookDTO bookDTO) {
			 Book existing = repo.findById(isbn)
		                .orElseThrow(() -> new BookNotFoundException("Book not found with ISBN: " + isbn));
	
		        existing.setTitle(bookDTO.getTitle());
		        existing.setAuthor(bookDTO.getAuthor());
		        existing.setPublicationYear(bookDTO.getPublicationYear());
	
		        return bookMapper.toDto(repo.save(existing));
		}
		
		@Override
		public String deleteBook(String isbn) {
		    if (!repo.existsById(isbn)) {
		        throw new BookNotFoundException("Book not found with ISBN: " + isbn);
		    }
		    repo.deleteById(isbn);
		    return "Book with ISBN " + isbn + " deleted successfully.";
		}
	
	}
