package com.example.demo.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookDetailsServiceImpl implements UserDetailsService  {
	@Autowired
    private BookRepository bookRepository;

    @Override
    public UserDetails loadUserByUsername(String isbn) throws UsernameNotFoundException {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ISBN: " + isbn));

        return new org.springframework.security.core.userdetails.User(
                book.getIsbn(),
                book.getPassword(),
                Collections.emptyList() 
        );
    }
}
