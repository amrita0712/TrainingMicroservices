package com.example.BookService.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.BookService.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,String>{
	@Query(value= "SELECT * FROM BOOKS", nativeQuery = true)
	List<Book> getAll();

	@Query(value="select b.AVAILABLE_COPIES from BOOKS b", nativeQuery = true)
	Integer getAvailableCopies(String bookId);
}