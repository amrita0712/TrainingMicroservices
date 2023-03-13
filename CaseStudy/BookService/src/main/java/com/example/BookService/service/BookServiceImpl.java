package com.example.BookService.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.BookService.entity.Book;

import com.example.BookService.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;



	@Override
	public List<Book> getAll() {
		
		return bookRepository.getAll();
	}



	@Override
	public Integer getAvailableCopies(String bookID) {
		// TODO Auto-generated method stub
		return bookRepository.getAvailableCopies(bookID);
	}

	
}
