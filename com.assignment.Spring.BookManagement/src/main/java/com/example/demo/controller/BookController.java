package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookDTO;
import com.example.demo.service.BookDetailsServiceImpl;
import com.example.demo.service.BookService;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BookDetailsServiceImpl bookDetailsService;
	

	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping
	public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO)
	{
		BookDTO addedBook=service.addBook(bookDTO);
		HttpHeaders headers = new HttpHeaders();
	    headers.add("info", "Book Added Successfully");
	    return new ResponseEntity<>(addedBook, headers, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<BookDTO>> getAllBooks()
	{
		 List<BookDTO> books = service
				 .getAllBooks();
	     HttpHeaders headers = new HttpHeaders();
	     headers.add("info", "All Books Fetched Successfully");
	     return new ResponseEntity<>(books, headers, HttpStatus.OK);
	}
	
	@GetMapping("/{isbn}")
	public ResponseEntity<BookDTO> getBooksByIsbn(@PathVariable String isbn)
	{
		BookDTO book=service.getBooksByIsbn(isbn);
		HttpHeaders headers = new HttpHeaders();
        headers.add("info", "Book Fetched by ISBN");
        return new ResponseEntity<>(book, headers, HttpStatus.OK);
	}
	
	@PutMapping("/{isbn}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable String isbn,@RequestBody BookDTO bookDTO)
	{
		 BookDTO updatedBook = service.updateBook(isbn, bookDTO);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("info", "Book Updated Successfully");
	        return new ResponseEntity<>(updatedBook, headers, HttpStatus.OK);
	}
	
	@DeleteMapping("/{isbn}")
	public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
	    String msg = service.deleteBook(isbn);
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("info", "Book Deleted Successfully");
	    return new ResponseEntity<>(msg, headers, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody BookDTO bookDTO) {
	    try {
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(bookDTO.getIsbn(), bookDTO.getPassword())
	        );

	        UserDetails userDetails = bookDetailsService.loadUserByUsername(bookDTO.getIsbn());
	        String jwt = jwtUtil.generateToken(userDetails.getUsername());

	        return ResponseEntity.ok(jwt);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
	    }
	    
	}
	
}
