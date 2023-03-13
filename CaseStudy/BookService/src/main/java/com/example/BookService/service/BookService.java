package com.example.BookService.service;

import java.util.List;

import com.example.BookService.entity.Book;


public interface BookService {

	
	public List<Book> getAll();
	
	public Integer getAvailableCopies(String bookId);
	
}
