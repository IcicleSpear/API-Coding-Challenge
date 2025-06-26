package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;

@Component
public class BookMapper {
	
	 private final ModelMapper modelMapper;

	    @Autowired
	    public BookMapper(ModelMapper modelMapper) {
	        this.modelMapper = modelMapper;
	    }

	    public BookDTO toDto(Book book) {
	        return modelMapper.map(book, BookDTO.class);
	    }

	    public Book toEntity(BookDTO bookDto) {
	        return modelMapper.map(bookDto, Book.class);
	    }
}
