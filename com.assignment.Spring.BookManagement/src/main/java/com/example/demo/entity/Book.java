package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Book {
	
	@Id
	@NotBlank(message="ISBN is mandatory")
	@Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
	private String isbn;
	
	@NotBlank(message="Title is mandatory")
	@Size(max = 100, message = "Title can be at most 100 characters")
	private String title;
	
	@NotBlank(message = "Author is mandatory")
    @Size(max = 50, message = "Author can be at most 50 characters")
    private String author;
	
	@NotNull(message = "Publication year must not be null")
	@Positive(message = "Publication year must be a positive number")
	private int publicationYear;
	
	 @NotBlank(message = "Password is mandatory")
	    @Size(min = 8, message = "Password must be at least 8 characters")
	    private String password;

	public Book() {
	}

	public Book(String isbn, String password, String title, String author, int publicationYear) {
        this.isbn = isbn;
        this.password = password;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

	 public String getIsbn() {
		 return isbn;
	 }

	 public void setIsbn(String isbn) {
		 this.isbn = isbn;
	 }

	 public String getTitle() {
		 return title;
	 }

	 public void setTitle(String title) {
		 this.title = title;
	 }

	 public String getAuthor() {
		 return author;
	 }

	 public void setAuthor(String author) {
		 this.author = author;
	 }

	 public int getPublicationYear() {
		 return publicationYear;
	 }

	 public void setPublicationYear(int publicationYear) {
		 this.publicationYear = publicationYear;
	 }

	 @Override
	 public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
				+ "]";
	 }

	 public String getPassword() {
		 return password;
	 }

	 public void setPassword(String password) {
		 this.password = password;
	 }

	
}
